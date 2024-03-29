
package spot.soa.services.soap.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ParkingSpotSOAPService", targetNamespace = "http://Implementations.SOAP.services.SOA/", wsdlLocation = "http://localhost:8080/MainApplication/ParkingSpotSOAPService?wsdl")
public class ParkingSpotSOAPService
    extends Service
{

    private final static URL PARKINGSPOTSOAPSERVICE_WSDL_LOCATION;
    private final static WebServiceException PARKINGSPOTSOAPSERVICE_EXCEPTION;
    private final static QName PARKINGSPOTSOAPSERVICE_QNAME = new QName("http://Implementations.SOAP.services.SOA/", "ParkingSpotSOAPService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/MainApplication/ParkingSpotSOAPService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PARKINGSPOTSOAPSERVICE_WSDL_LOCATION = url;
        PARKINGSPOTSOAPSERVICE_EXCEPTION = e;
    }

    public ParkingSpotSOAPService() {
        super(__getWsdlLocation(), PARKINGSPOTSOAPSERVICE_QNAME);
    }

    public ParkingSpotSOAPService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PARKINGSPOTSOAPSERVICE_QNAME, features);
    }

    public ParkingSpotSOAPService(URL wsdlLocation) {
        super(wsdlLocation, PARKINGSPOTSOAPSERVICE_QNAME);
    }

    public ParkingSpotSOAPService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PARKINGSPOTSOAPSERVICE_QNAME, features);
    }

    public ParkingSpotSOAPService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ParkingSpotSOAPService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ParkingSpotSOAPInterface
     */
    @WebEndpoint(name = "ParkingSpotSOAPServicePort")
    public ParkingSpotSOAPInterface getParkingSpotSOAPServicePort() {
        return super.getPort(new QName("http://Implementations.SOAP.services.SOA/", "ParkingSpotSOAPServicePort"), ParkingSpotSOAPInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ParkingSpotSOAPInterface
     */
    @WebEndpoint(name = "ParkingSpotSOAPServicePort")
    public ParkingSpotSOAPInterface getParkingSpotSOAPServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Implementations.SOAP.services.SOA/", "ParkingSpotSOAPServicePort"), ParkingSpotSOAPInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PARKINGSPOTSOAPSERVICE_EXCEPTION != null) {
            throw PARKINGSPOTSOAPSERVICE_EXCEPTION;
        }
        return PARKINGSPOTSOAPSERVICE_WSDL_LOCATION;
    }

}
