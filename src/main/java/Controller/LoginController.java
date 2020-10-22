package Controller;

import Entity_Bean.UserTbEntity;
import Session_Bean.CartBean;
import Session_Bean.Messagesender;
import Session_Bean.UserDAO;
import Session_Bean.Messagesender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class LoginController {


    private UserDAO userdao = lookupUserDAO();


    private Messagesender messa=lookupMessagesender();


    @RequestMapping(value = {"/home"},method = RequestMethod.POST)
    public String logout(HttpSession session)
    {
        session.setAttribute("Cur_User", null);
        if(session.getAttribute("BorrowCart")!=null)
            ((CartBean)session.getAttribute("BorrowCart")).remove();
        session.setAttribute("BorrowCart",null);
        return "index";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public String loginpage(@RequestParam(value = "username",required = false) String username,
                            @RequestParam(value = "password",required = false) String password,
                            HttpSession session)
    {
        messa.ableToSend();
        UserTbEntity user =userdao.login(username,password);
        if(user==null)
            return "login";
        else {
            session.setAttribute("Cur_User", user);
            return "success";
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

    private Messagesender lookupMessagesender() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/MessagesenderEJB");
            return  (Messagesender) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
