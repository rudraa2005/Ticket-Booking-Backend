package org.TicketBooking.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.TicketBooking.entities.Train;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainService {
    private List<Train> trainList;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "/Users/rudranilbhattacharya/Documents/IRCTC_TicketBooking/app/src/main/java/org/TicketBooking/LocalDB/trains.json";

    public TrainService() throws IOException {
        File trains = new File(TRAIN_DB_PATH);
        trainList = mapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream().filter(train -> validtrain(train, source, destination)).collect(Collectors.toList());
    }

    public void addTrain(Train newTrain) {
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrainID().equalsIgnoreCase(newTrain.getTrainID())).findFirst();
        if (existingTrain.isPresent()) {
            updateTrain(newTrain);
        } else {
            trainList.add(newTrain);
            saveTrainlistToFile();
        }
    }

    public void updateTrain(Train updatedTrain) {
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainID().equalsIgnoreCase(updatedTrain.getTrainID()))
                .findFirst();
        if (index.isPresent()) {
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainlistToFile();
        } else {
            addTrain(updatedTrain);
        }
    }

    private void saveTrainlistToFile() {
        try {
            mapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean validtrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }

}
