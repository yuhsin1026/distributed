package Filter;



import javax.ejb.Singleton;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Singleton
public class hitcounter implements Filter {
    private  FilterConfig filterConfig=null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          if(filterConfig==null){
              return;
          }

        HttpServletRequest request =(HttpServletRequest) servletRequest;
        if(request.getRequestURI().startsWith("/final/webresources/")
                ||request.getRequestURI().startsWith("/final/services/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        StringWriter sw=new StringWriter();
          PrintWriter writer=new PrintWriter(sw);
          Counter counter=(Counter) filterConfig.getServletContext().getAttribute("hitCounter");
        if(counter==null){
            counter = new Counter();
            filterConfig.getServletContext().setAttribute("hitCounter",counter);
        }
        writer.println();
        writer.println("=======");
        writer.println("Number of hits: "+counter.inccounter());
        writer.println("=======");
        writer.flush();
        System.out.println(sw.getBuffer().toString());

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
         this.filterConfig=null;
    }
}
