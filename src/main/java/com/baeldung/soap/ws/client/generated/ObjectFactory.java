
package com.baeldung.soap.ws.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.baeldung.soap.ws.client.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StrLowCase_QNAME = new QName("http://Web_Service/", "strLowCase");
    private final static QName _StrLowCaseResponse_QNAME = new QName("http://Web_Service/", "strLowCaseResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.baeldung.soap.ws.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StrLowCase }
     * 
     */
    public StrLowCase createStrLowCase() {
        return new StrLowCase();
    }

    /**
     * Create an instance of {@link StrLowCaseResponse }
     * 
     */
    public StrLowCaseResponse createStrLowCaseResponse() {
        return new StrLowCaseResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrLowCase }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrLowCase }{@code >}
     */
    @XmlElementDecl(namespace = "http://Web_Service/", name = "strLowCase")
    public JAXBElement<StrLowCase> createStrLowCase(StrLowCase value) {
        return new JAXBElement<StrLowCase>(_StrLowCase_QNAME, StrLowCase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrLowCaseResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrLowCaseResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://Web_Service/", name = "strLowCaseResponse")
    public JAXBElement<StrLowCaseResponse> createStrLowCaseResponse(StrLowCaseResponse value) {
        return new JAXBElement<StrLowCaseResponse>(_StrLowCaseResponse_QNAME, StrLowCaseResponse.class, null, value);
    }

}
