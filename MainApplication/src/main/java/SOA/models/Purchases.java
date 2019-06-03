package SOA.models;

import javax.persistence.*;

@Entity
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer purchasesId;

    @OneToOne
    @JoinColumn(name = "parkingSpotId")
    private ParkingSpot parkingSpot;

    @OneToOne
    @JoinColumn(name = "ticketsId")
    private Tickets tickets;

    public Integer getPurchasesId() {
        return purchasesId;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }
}
