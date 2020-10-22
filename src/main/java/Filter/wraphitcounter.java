package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "wraphitcounter")
public class wraphitcounter implements Filter {
    private FilterConfig filterConfig=null;
    public void destroy() {
        this.filterConfig=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(filterConfig==null){
            return;
        }
        HttpServletRequest request =(HttpServletRequest) req;

        if(request.getRequestURI().startsWith("/final/webresources/")
            ||request.getRequestURI().startsWith("/final/services/")) {
            chain.doFilter(req, resp);
            return;
        }
        PrintWriter out = resp.getWriter();
        CharResponseWrapper wrapper = new CharResponseWrapper(
                (HttpServletResponse)resp);
        chain.doFilter(req, wrapper);
        Counter counter=(Counter) filterConfig.getServletContext().getAttribute("hitCounter");
        CharArrayWriter caw=new CharArrayWriter();
        int index=wrapper.toString().indexOf("</body>");
        if(index != -1){

            caw.write(wrapper.toString().substring(0, wrapper.toString().indexOf("</body>")-1));
            caw.write("<p style=\"background-color:rgba(255,255,255,0.85);\">\nView count: <font color='red'>" + counter.getcounter() + "</font>");
            caw.write("\n</body></html>");
            resp.setContentLength(caw.toString().length());
            out.write(caw.toString());
        }
        else{
            out.flush();
        }

    }

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig=config;
    }

}
