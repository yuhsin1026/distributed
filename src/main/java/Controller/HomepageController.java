package Controller;

import Entity_Bean.BookEntity;
import Entity_Bean.LibraryEntity;
import Entity_Bean.UserTbEntity;
import Session_Bean.FindBooksBean;
import Session_Bean.RankBean;
import Session_Bean.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Controller
public class HomepageController {

    private FindBooksBean booksbean = lookupfindbooksession();

    //Home page
    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String home(HttpServletRequest req)
    {
        return "index";
    }

    //info(books, rank, user info )
    @RequestMapping(value = {"/search"})
    public ModelAndView searchbooks(@RequestParam(value = "bookname",required = false) String BookName,
                                    @RequestParam(value = "authorlast",required = false) String AuthorLast,
                                    @RequestParam(value = "authorfirst",required = false) String AuthorFirst,
                                    @RequestParam(value = "library",required = false) String Library)
    {
        String smallBookName;
        com.baeldung.soap.ws.client.generated.LibrarySoapService soapService = new com.baeldung.soap.ws.client.generated.LibrarySoapService();
        com.baeldung.soap.ws.client.generated.LibrarySoap port = soapService.getLibrarySoapPort();
        if(BookName!=null)
        {  smallBookName = port.strLowCase(BookName); }
        else
        {  smallBookName = BookName; }
        List<Object[]> Books=booksbean.searchform(smallBookName,AuthorLast,AuthorFirst,Library);
        return new ModelAndView("search","books",Books);
    }

    //leaderboard
    @RequestMapping(value = {"rank"},method = RequestMethod.GET)
    public String ranking(@RequestParam(value = "Location",required = false) String location,
                          HttpServletRequest req)
    {
        RankBean rankbean=lookuprankbean();
        List<Object[]> rankings;
        req.setAttribute("librarys",(List<LibraryEntity>)rankbean.getLibraries());
        if(!StringUtils.isEmpty(location)) {
            if(location.equals("default")){
                return "rank";
            }
            if (location.equals("all"))
                rankings=rankbean.getTotalrank();
            else
                rankings=rankbean.rank_a_library(rankbean.getLibrarybyname(location));
            req.setAttribute("rankings",rankings);
        }
        return "rank";
    }


    @RequestMapping(value = {"/userinfo"},method = RequestMethod.GET)
    public String Userinfo(HttpSession session, HttpServletRequest request)
    {
      //  if(session.getAttribute("Cur_User")==null)
         //   return "login";
       // else {
            UserDAO userbean=lookupUserDAO();
        session.setAttribute("Cur_User",userbean.manageUser(((UserTbEntity)session.getAttribute("Cur_User"))));
            UserTbEntity sessionuser=(UserTbEntity)session.getAttribute("Cur_User");
            UserTbEntity dbuser=userbean.manageUser(sessionuser);
            if(sessionuser.getBorrowbooks().size()!=dbuser.getBorrowbooks().size()) {
                request.setAttribute("now",dbuser.getBorrowbooks().size());
                return "hello";
            }
            return "userinfo";
     //   }
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
    private RankBean lookuprankbean() {
        try {
            Context ic = new InitialContext();
            Object obj=ic.lookup("java:module/RankEJB");
            return  (RankBean) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
