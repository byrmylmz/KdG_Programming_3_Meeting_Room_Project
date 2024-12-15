package be.kdg.event.repository.JdbcTemplateRepository;

import be.kdg.event.model.Building;
import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Profile("jdbc")
public class RoomJdbcTemplateRepository implements RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoomJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Room> findAll() {
        String sql = "SELECT * FROM ROOMS";
        return jdbcTemplate.query(sql, roomRowMapper());
    }

    public Room findById(Long id) {
        String sql = "SELECT * FROM ROOMS WHERE ROOMID = ?";
        return jdbcTemplate.queryForObject(sql, roomRowMapper(), id);
    }

    public long countRoom() {
        String sql = "SELECT COUNT(*) FROM ROOMS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    @Override
    public void save(Room room) {
        String sql = "INSERT INTO ROOMS (ROOM_NUMBER, CAPACITY, TYPE) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                room.getRoomNumber(),
                room.getCapacity(),
                room.getType()

        );
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ROOMS WHERE ROOMID = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Room> roomRowMapper() {
        return (rs, rowNum) -> Room.builder()
                .roomID(rs.getLong("ROOMID"))
                .roomNumber(rs.getString("ROOM_NUMBER"))
                .capacity(rs.getInt("CAPACITY"))
                .type(rs.getString("TYPE"))
                // Ensure building is set conditionally
                .building(rs.getLong("BUILDING_ID") != 0 ? Building.builder().buildingID(rs.getLong("BUILDING_ID")).build() : null)
                .build();
    }
}
