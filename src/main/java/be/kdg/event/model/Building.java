package be.kdg.event.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingID;

    private String name;
    private String address;
    private int numberOfFloors;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();


    @Override
    public String toString() {
        return "Building{" +
                "buildingID=" + buildingID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberOfFloors=" + numberOfFloors +
//                ", rooms=" + rooms +
                '}';
    }
}
