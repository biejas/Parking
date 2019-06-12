
package spot.soa.services.soap.interfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parkingSpotDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parkingSpotDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="available" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="parkingSpotId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parkingSpotDTO", propOrder = {
    "available",
    "parkingSpotId"
})
public class ParkingSpotDTO {

    protected boolean available;
    protected Integer parkingSpotId;

    /**
     * Gets the value of the available property.
     * 
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the value of the available property.
     * 
     */
    public void setAvailable(boolean value) {
        this.available = value;
    }

    /**
     * Gets the value of the parkingSpotId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParkingSpotId() {
        return parkingSpotId;
    }

    /**
     * Sets the value of the parkingSpotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParkingSpotId(Integer value) {
        this.parkingSpotId = value;
    }

}
