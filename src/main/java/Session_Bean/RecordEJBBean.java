package Session_Bean;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entity_Bean.BookEntity;
import Entity_Bean.RecordEntity;
import Entity_Bean.UserTbEntity;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

@Stateless(name = "RecordEJBEJB")
public class RecordEJBBean {
    //create resource annotation to map my queue with the dest assigned in JMS service
    @Resource(mappedName = "jms/javaee7/Topic")
    private Destination demoPublishDest;
    //provide connection factory obj for the particular queue
    @Resource(mappedName = "jms/javaee7/ConnectionFactory")
    private ConnectionFactory queue;
    @Resource
    TimerService timerService;
    @PersistenceContext(unitName = "final")
    private EntityManager em;
    public RecordEJBBean() {
    }
    public String AddRecord(RecordEntity newRecord){


        this.em.persist(newRecord);
        String msg = "The book  "+newRecord.getBookId()+"  borrowed by  "+newRecord.getUserId()+" need to be return.";
        //  timerService.createIntervalTimer(newRecord.getTime(),10*1000,new TimerConfig(msg,true));
        timerService.createSingleActionTimer(60*1000,new TimerConfig(msg,true));

       // System.out.println("The timer is on");
        return "success";
    }
    public int getindex(){
        int max=this.em.createQuery("select max(b.indexid) from RecordEntity b", Integer.class).getSingleResult()+1;
        return max;
    }
    @Timeout
    public void sendlastdayemail(Timer timer) {

        sendJMSMessageToPublishDest(timer.getInfo().toString());
    }

    private void sendJMSMessageToPublishDest(String messageData) {
        try {
            System.out.println("Trying to send the message");
            //after create obj of connection factory, create obj of connection
            Connection con = queue.createConnection();
            //create obj of session
            Session s = con.createSession(false,AUTO_ACKNOWLEDGE);
            MessageProducer mp = s.createProducer(demoPublishDest);//any msg send to dest will go to the qeue
            TextMessage tm = s.createTextMessage();
            tm.setText(messageData);
            mp.send(tm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }




}







