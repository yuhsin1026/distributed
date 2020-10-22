package TimerTask;
import Session_Bean.FreshBook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component

public class RefreshData {
   FreshBook fresh=lookupRefreshEJB();
    @Scheduled(cron = "0/10 * * * * ? ") // run every 30 seconds.
    public void taskCycle1() {
      //  System.out.println("test the dayleft-fresh every 24h");
        fresh.freshbook();
    }
    @Scheduled(cron = "0 0/2 * * * ? ") // run every 2 minutes.
    public void taskCycle2() {
     //   System.out.println("test the monthly freq-fresh every month");
        fresh.freshfreq();

    }
    private FreshBook lookupRefreshEJB() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/RefreshEJB");
            if (obj==null)
                return null;
            return  (FreshBook) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

