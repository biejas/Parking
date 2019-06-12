package SOA.DTO;

public class TicketsDTO {
    private Integer ticketsId;
    private Long endTime;
    private Integer duration;
    private Integer parkingMeterId;
    private Integer parkingSpotId;

    public Integer getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(Integer ticketsId) {
        this.ticketsId = ticketsId;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getParkingMeterId() {
        return parkingMeterId;
    }

    public void setParkingMeterId(Integer parkingMeterId) {
        this.parkingMeterId = parkingMeterId;
    }

    public Integer getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Integer parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }
}
