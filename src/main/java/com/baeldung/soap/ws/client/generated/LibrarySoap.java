
package com.baeldung.soap.ws.client.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LibrarySoap", targetNamespace = "http://Web_Service/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LibrarySoap {


    /**
     * 
     * @param input
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "strLowCase", targetNamespace = "http://Web_Service/", className = "com.baeldung.soap.ws.client.generated.StrLowCase")
    @ResponseWrapper(localName = "strLowCaseResponse", targetNamespace = "http://Web_Service/", className = "com.baeldung.soap.ws.client.generated.StrLowCaseResponse")
    @Action(input = "http://Web_Service/LibrarySoap/strLowCaseRequest", output = "http://Web_Service/LibrarySoap/strLowCaseResponse")
    public String strLowCase(
        @WebParam(name = "input", targetNamespace = "")
        String input);

}
