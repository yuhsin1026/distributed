package Controller;

import Entity_Bean.BookEntity;
import Entity_Bean.LibraryEntity;
import RestfulClient.RestClient;
import Session_Bean.FindBooksBean;
import Session_Bean.RankBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class LibraryService {
    private RestClient client = new RestClient();


    @RequestMapping(value = {"/library"}, method = RequestMethod.GET)
    public String Lib_info(HttpServletRequest request,
                           @RequestParam(value = "Location", required = false) String location) throws IOException {
        RankBean all_libs = lookuprankbean();
        List<LibraryEntity> temp = all_libs.getLibraries();
        List<LibraryEntity> libs=null;
        request.setAttribute("librarys", temp);
        if (!StringUtils.isEmpty(location)) {
            if (location.equals("default")) {
                request.setAttribute("libinfo", null);
                return "libraryInfo";
            }
            if (location.equals("all"))
                libs = client.getLibrariesinfo();
            else
                libs = client.getalibraryinfo(location);
        }
        request.setAttribute("libinfo", libs);
        return "libraryInfo";
    }

    @RequestMapping(value = {"/library"}, method = RequestMethod.POST)
    public ModelAndView Donate(HttpServletRequest request, HttpServletResponse res,
                               @RequestParam(value = "bookname", required = false) String bookname,
                               @RequestParam(value = "libname", required = false) String libname) throws IOException {
        if (StringUtils.isEmpty(bookname) && StringUtils.isEmpty(libname))
            request.setAttribute("status", "empty");
        else if (StringUtils.isEmpty(bookname) || StringUtils.isEmpty(libname))
            request.setAttribute("status", "incomplete");
        else {
            RankBean all_libs = lookuprankbean();
            if (all_libs.getLibrarybyname(libname) == null)
                request.setAttribute("status", "invalid");
            else {
                BookEntity newbook = new BookEntity();
                newbook.setBookName(bookname);
                newbook.setLocation(all_libs.getLibrarybyname(libname));
                Response str=client.donate(newbook);
                request.setAttribute("status", "success");
                request.setAttribute("json", str);

            }
        }
        ModelAndView model = new ModelAndView("donate");


        return model;
    }

    private RankBean lookuprankbean() {
        try {
            Context ic = new InitialContext();
            Object obj = ic.lookup("java:module/RankEJB");
            return (RankBean) obj;
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

