package SOA.utils;

import SOA.models.ParkingSpot;

import java.util.Objects;

public class ParkingSpotUtils {

    public static boolean hasNoTicket(ParkingSpot parkingSpot) {
        return Objects.isNull(parkingSpot.getTicket());
    }

    public static boolean hasExpiredTicket(ParkingSpot parkingSpot) {
        return TicketUtils.isExpired(parkingSpot.getTicket());
    }

    public static boolean isNotAvailable(ParkingSpot parkingSpot){
        return !parkingSpot.isAvailable();
    }

}
