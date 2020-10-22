package Web_Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class LibrarySoap {
    @WebMethod
    public String strLowCase(@WebParam(name = "input")String input){
        return input.toLowerCase();
    }
}
