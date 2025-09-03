package org.TicketBooking.entities;
import java.sql.Time;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.TicketBooking.services.UserBookingService;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    @JsonProperty("train_id")
    private String trainID;
    @JsonProperty("trainName")
    private String trainName;
    @JsonProperty("train_no")
    private String trainNumber;
    private List<List<Integer>> seats;
    @JsonProperty("station_times")
    private Map<String, Time> stationsTimes;
    private List<String> stations;
    private String source;
    private String destination;
    UserBookingService userBookingService;

    public Train(String trainID, String trainName, String trainNumber, Map<String, Time> stationsTimes,List<String> stations, List<List<Integer>> seats, String source, String destination) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.stationsTimes = stationsTimes;
        this.stations = stations;
        this.seats = seats;
    }
    public Train() {};
    public String getTrainID() {

        return trainID;
    }
    public void setTrainID(String trainID) {

        this.trainID = trainID;
    }
    public String getTrainName() {
        return trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    public String getTrainNumber() {
        return trainNumber;
    }
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    public void setSource(String source){
        this.source = source;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public String getSource(){
        return source;
    }
    public String getDestination(){
        return destination;
    }
    public Map<String, Time> getStationsTimes() {
        return stationsTimes;
    }
    public void setStationsTimes(Map<String, Time> stationsTimes) {
        this.stationsTimes = stationsTimes;
    }
    public List<String> getStations() {
        return stations;
    }
    public void setStations(List<String> stations) {
        this.stations = stations;
    }
    public List<List<Integer>> getSeats() {
        return seats;
    }
    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }
    @JsonProperty("train_info")
    public String getTrainInfo(){
        return String.format("Train ID:%s | Train No:%s | From:%s | To:%s", trainID, trainNumber,source,destination);
    }
}
