package org.TicketBooking.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    @JsonProperty("user_id")
    private String userID;
    private String trainID;
    @JsonProperty("ticket_id")
    private String ticketID;
    private String source;
    private String destination;
    private Train train;
    private static final String USERS_PATH = "/Users/rudranilbhattacharya/Documents/IRCTC_TicketBooking/app/src/main/java/org/TicketBooking/LocalDB/users.json";
    public Ticket(String trainID, String source, String destination, Train train,String userID) {
        this.train = train;
        this.source = source;
        this.destination = destination;
        this.trainID = trainID;
        this.userID = userID;
    }
    public Ticket() {};
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getTrainID() {
        return trainID;
    }
    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }
    public String getTicketID() {
        return ticketID;
    }
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }
    public String getTicketInfo() {
        return String.format("Ticket ID: %s | Train: %s",
                ticketID,
                train.getTrainInfo()
        );
    }
}
