package be.kdg.event.viewmodels;

import lombok.Data;

@Data
public class RoomViewModel {
    private Long roomID;
    private String roomNumber;
    private int capacity;
    private String type;
}
