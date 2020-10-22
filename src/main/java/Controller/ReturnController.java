package Controller;

import Entity_Bean.BookEntity;
import Entity_Bean.UserTbEntity;
import Session_Bean.FindBooksBean;
import Session_Bean.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ReturnController {
    @RequestMapping(value = {"/userinfo"},method = RequestMethod.POST)
    public String Return( @RequestParam(value = "book",required = false) int BookId,
                            HttpSession session)
    {

        //Object cur_user= session.getAttribute("Cur_user");

        if(session.getAttribute("Cur_User")==null)
            return "login";
        else{
            UserTbEntity cur =((UserTbEntity)session.getAttribute("Cur_User"));
            FindBooksBean booksBean= lookupfindbooksession();
            UserDAO userBean= lookupUserDAO();
            BookEntity book = booksBean.clearstatus(BookId);
            session.setAttribute("Cur_User",userBean.user_return(cur,book));
            return "userinfo";
        }
    }

    //lookup function
    private FindBooksBean lookupfindbooksession() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/FindBooksEJB");
            return  (FindBooksBean) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    private UserDAO lookupUserDAO() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/UserDAOEJB");
            return  (UserDAO) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
