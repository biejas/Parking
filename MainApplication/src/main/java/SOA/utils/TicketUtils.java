package SOA.utils;

import SOA.models.Tickets;

public class TicketUtils {

    public static boolean isExpired(Tickets t) {
        return t.getEndTime() < System.currentTimeMillis();
    }

    public static boolean isNotExpired(Tickets t) {
        return !isExpired(t);
    }
}
