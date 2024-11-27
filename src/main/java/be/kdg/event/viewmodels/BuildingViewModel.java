package be.kdg.event.viewmodels;


import lombok.Data;

@Data
public class BuildingViewModel {
    private Long buildingID;
    private String name;
    private String address;
    private int numberOfFloors;
}
