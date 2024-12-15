package be.kdg.event.repository;

import be.kdg.event.model.Building;

import java.util.List;

public interface BuildingRepository {
    List<Building> findAll();

    Building findById(Long id);

    long countBuildings();

    void save(Building building);

    void delete(Long id);

    void update(Building building);
}