package be.kdg.event.repository;


import be.kdg.event.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    @Query("SELECT COUNT(B) FROM Building B")
    long countBuilding();
}
