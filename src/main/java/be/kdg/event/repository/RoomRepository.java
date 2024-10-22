package be.kdg.event.repository;


import be.kdg.event.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT COUNT(R) FROM Room R")
    long countRoom();
}
