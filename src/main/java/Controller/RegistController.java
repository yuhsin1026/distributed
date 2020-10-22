package Controller;

import Entity_Bean.UserTbEntity;
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

import static Entity_Bean.UserType.STUDENT;
import static Entity_Bean.UserType.TEACHER;

@Controller
public class RegistController {
    private UserDAO userdao = lookupUserDAO();
    @RequestMapping(value = {"/regist"},method = RequestMethod.POST)
    public String Regist(@RequestParam(value = "userId",required = false) String userId,
                         @RequestParam(value = "lastName",required = false) String lastName,
                         @RequestParam(value = "firstName",required = false) String firstName,
                         @RequestParam(value = "phoneNum",required = false) String phoneNum,
                         @RequestParam(value = "email",required = false) String email,
                         @RequestParam(value = "userpassword",required = false) String userpassword,
                         HttpSession session
                         )
    {
        UserTbEntity newuser= new UserTbEntity();
        newuser.setUserId(userId);
        newuser.setUserPassword(userpassword);
        newuser.setEmail(email);
        newuser.setLastName(lastName);
        newuser.setFirstName(firstName);
        newuser.setPhoneNum(phoneNum);
        String state =userdao.regist(newuser);
        if(state.equals("success")) {
            session.setAttribute("Cur_User", newuser);
            return state;
        }
        else {
            return "regist";
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
