package Controller;


import Entity_Bean.*;
import Session_Bean.CartBean;
import Session_Bean.FindBooksBean;
import Session_Bean.RecordEJBBean;
import Session_Bean.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class BorrowController {

    @RequestMapping(value = {"cart"})
    public String cart_operation(@RequestParam(value = "bookname",required = false) String BookName,
                                 @RequestParam(value = "location",required = false) String Loc,
                                 @RequestParam(value = "status",required = false) String Status,
                                 HttpSession session, Model model, HttpServletResponse res) throws IOException {

        // Obtain the stateful session bean
        CartBean BorrowCartBean;

        // Obtain the stateless session bean
        RecordEJBBean record;
        //get user
        UserTbEntity user = ((UserTbEntity)session.getAttribute("Cur_User"));
        if(session.getAttribute("BorrowCart") == null){
            BorrowCartBean= lookupCartBean();
            // put EJB in HTTP session for future servlet calls
            session.setAttribute("BorrowCart",BorrowCartBean);
        }
        else
            BorrowCartBean=(CartBean)session.getAttribute("BorrowCart");
        if(StringUtils.isEmpty(Status))
            return "cart";
        if(user==null){
            model.addAttribute("alert","needlogin");
            return "login";
        }
        if(Status.equals("add")) {
            Pair<String,String> temp=new Pair<>(BookName,Loc);
            BorrowCartBean.addBook(temp,user.getUserId());
            return "cart";
        }
        if(Status.equals("remove")) {
            Pair<String,String> temp=new Pair<>(BookName,Loc);
            BorrowCartBean.removeBook(temp);
            return "cart";
        }
        if(Status.equals("finish")) {
            if( BorrowCartBean.getBooks().size()==0) {
                model.addAttribute("alert", "nobook");
                return "cart";
            }
            if(session.getAttribute("recordlist") == null){
                record= lookupRecordEJB();
                // put EJB in HTTP session for future servlet calls
                session.setAttribute("recordlist",record);
            }
            else
            { record=(RecordEJBBean) session.getAttribute("recordlist");}
            int i=0;
            for(Pair<String, String> book : BorrowCartBean.getBooks()) {

                RecordEntity re = new RecordEntity();
                BookEntity cur_book= BorrowCartBean.findfirst(book.first(),book.second()).get(0);
                Timestamp d = new Timestamp(System.currentTimeMillis());
         //       long randomNum = System.currentTimeMillis()+i;

              //  int ran3 = (int) (randomNum%(100-1)+1);
                re.setIndex(record.getindex());
                re.setTime(d);
                re.setBookId(cur_book.getBookId());
                re.setUserId(user.getUserId());

                record.AddRecord(re);
              //   i++;
            }
            session.setAttribute("Cur_User",BorrowCartBean.complete(user));
            return "index";
        }
        return "cart";

    }



    //lookup function
    private CartBean lookupCartBean() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/CartEJB");
            if (obj==null)
                return null;
            return  (CartBean) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


    private RecordEJBBean lookupRecordEJB() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/RecordEJBEJB");
            if (obj==null)
                return null;
            return  (RecordEJBBean) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
