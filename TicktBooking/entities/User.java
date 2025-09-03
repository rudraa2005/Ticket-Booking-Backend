package org.TicketBooking.entities;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String name;
    private String password;
    @JsonProperty("hashed_password")
    private String hashPassword;
    @JsonProperty("tickets_booked")
    private List<Ticket> ticketsBooked;
    @JsonProperty("user_id")
    private String userID;


    public User(String name, String password, String hashPassword, List<Ticket> ticketsBooked, String userID) {
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userID = userID;
    }
    public User(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHashPassword() {
        return hashPassword;
    }
    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }
    public void printTicketsBooked() {
        for (Ticket ticket : ticketsBooked) {
            System.out.println(ticket);
        }
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
    public boolean cancelTicket(String ticketId){
        return this.ticketsBooked.removeIf(ticket->ticket.getTicketID().equals(ticketId));
    }
    public void getTicketInfo(){
        if(ticketsBooked.isEmpty()){
            System.out.println("No tickets booked");
        } else {
            for(Ticket ticket : ticketsBooked){
                ticket.getTicketInfo(); // Show each booked ticket
            }
        }
    }
    }

