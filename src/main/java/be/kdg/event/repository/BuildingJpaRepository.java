package be.kdg.event.repository;

import be.kdg.event.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingJpaRepository extends JpaRepository<Building, Long> {
}
