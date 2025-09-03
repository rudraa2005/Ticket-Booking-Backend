package org.TicketBooking.services;
import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.TicketBooking.entities.Train;
import org.TicketBooking.entities.User;
import org.TicketBooking.entities.Ticket;

import org.TicketBooking.util.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBookingService {
    Ticket ticket = new Ticket();
    private final User user;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userlist;
    private static final String USERS_PATH = "/Users/rudranilbhattacharya/Documents/IRCTC_TicketBooking/app/src/main/java/org/TicketBooking/LocalDB/users.json";
    private User currentUser;

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        userlist = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }
    public UserBookingService() throws IOException {
        loadUserListFromFile();
        user = new User();
    }
    private void loadUserListFromFile() throws IOException {
        userlist = objectMapper.readValue(
                new File(USERS_PATH),
                new TypeReference<List<User>>() {}
        );
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public Boolean loginUser() {
        if (currentUser == null) {
            System.out.println("No user set for login attempt");
            return false;
        }

        System.out.println("Attempting login for: " + currentUser.getName());
        System.out.println("Password provided: " + currentUser.getPassword());

        Optional<User> foundUser = userlist.stream().filter(user1 -> {
            System.out.println("Checking against user: " + user1.getName());
            System.out.println("Stored hash: " + user1.getHashPassword());

            // Use currentUser instead of user
            boolean nameMatch = user1.getName().equals(currentUser.getName());
            boolean passwordMatch = UserServiceUtil.checkPassword(currentUser.getPassword(), user1.getHashPassword());

            System.out.println("Name match: " + nameMatch + ", Password match: " + passwordMatch);
            return nameMatch && passwordMatch;
        }).findFirst();

        if (foundUser.isPresent()) {
            this.currentUser = foundUser.get(); // Set the actual user with all data
            return true;
        }
        return false;
    }

    public boolean signUp(User user1) {
        try {
            userlist.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userlist);

    }

    public void fetchBooking() {
        if (currentUser != null) {
            List<Ticket> tickets = userlist.stream()
                    .filter(u->u.getUserID().equals(currentUser.getUserID()))
                            .flatMap((u->u.getTicketsBooked().stream()))
                    .toList();
            for (Ticket ticket1 : tickets) {
                System.out.println("Ticket ID: " + ticket1.getTicketID());
                System.out.println("Source: " + ticket1.getSource());
                System.out.println("Destination: " + ticket1.getDestination());
            }
        } else {
            System.out.println("Please login first");
        }
    }

    public boolean cancelBooking(String ticketId) {
        Optional<User> foundUser = userlist.stream()
                .filter(u -> u.getTicketsBooked().stream()
                        .anyMatch(t -> t.getTicketID().equals(ticketId)))
                .findFirst();

        if (foundUser.isPresent()) {
            User currentUser = foundUser.get();
            return currentUser.cancelTicket(ticketId);
        }
        return false;
    }

    public List<Train> getTrains(String sourceStation, String destinationStation) throws IOException {

        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(sourceStation,destinationStation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train trainSelectedForBooking, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = trainSelectedForBooking.getSeats();
            if(row>=0 && row<seats.size() &&  seat>=0 && seat < seats.get(row).size()){
                if(seats.get(row).get(seat)==0){
                    seats.get(row).set(seat,1);
                    trainSelectedForBooking.setSeats(seats);
                    trainService.addTrain(trainSelectedForBooking);

                    Ticket ticket = new Ticket();
                    ticket.setTrain(trainSelectedForBooking);
                    ticket.setTicketID(UUID.randomUUID().toString());
                    ticket.setUserID(currentUser.getUserID());
                    currentUser.getTicketsBooked().add(ticket);
                    updateUserInList();
                    saveUserListToFile();

                    System.out.println("Ticket created: " + ticket.getTicketInfo());
                    return true;

                } else{
                    return false;
                }
            } else{
                return false;
            }

        } catch(IOException e){
            System.out.println("❌ Error during booking: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch(Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void updateUserInList() {
        for (int i = 0; i < userlist.size(); i++) {
            if (userlist.get(i).getUserID().equals(currentUser.getUserID())) {
                userlist.set(i, currentUser);
                System.out.println("User updated in userlist with " + currentUser.getTicketsBooked().size() + " tickets");
                return;
            }
        }
        System.out.println("⚠️ Warning: User not found in userlist");
    }
}

