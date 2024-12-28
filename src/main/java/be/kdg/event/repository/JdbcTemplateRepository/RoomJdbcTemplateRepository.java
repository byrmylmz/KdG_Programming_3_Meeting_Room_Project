package be.kdg.event.repository.JdbcTemplateRepository;

import be.kdg.event.model.Building;
import be.kdg.event.model.Event;
import be.kdg.event.model.Room;
import be.kdg.event.repository.RoomRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String sql = "SELECT * FROM ROOMS WHERE ROOM_ID = ?";
        return jdbcTemplate.queryForObject(sql, roomRowMapper(), id);
    }

    public long countRoom() {
        String sql = "SELECT COUNT(*) FROM ROOMS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    @Override
    public void save(Room room) {
        String sql = "INSERT INTO ROOMS (ROOM_NAME,ROOM_NUMBER, ROOM_CAPACITY,BUILDING_ID) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(
                sql,
                room.getName(),
                room.getNumber(),
                room.getCapacity(),
                room.getBuilding().getId() != null ? room.getBuilding().getId() : 0
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ROOMS WHERE ROOM_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Room room) {
        String sql = "UPDATE ROOMS SET ROOM_NAME = ?, ROOM_NUMBER = ?, ROOM_CAPACITY = ?, BUILDING_ID = ? WHERE ROOM_ID = ?";
        jdbcTemplate.update(
                sql,
                room.getName(),
                room.getNumber(),
                room.getCapacity(),
                room.getBuilding() != null && room.getBuilding().getId() != null ? room.getBuilding().getId() : 0,
                room.getId()
        );
    }

    @Override
    public Room findByRoomIdWithEvents(Long roomId) {
        String roomSql = "SELECT * FROM ROOMS WHERE ROOM_ID = ?";
        Room room = jdbcTemplate.queryForObject(roomSql, roomRowMapper(), roomId);

        if (room != null) {
            String eventSql = """
            SELECT e.* 
            FROM EVENTS e
            INNER JOIN EVENT_ROOMS er ON e.EVENT_ID = er.EVENT_ID
            WHERE er.ROOM_ID = ?
            """;
            List<Event> events = jdbcTemplate.query(eventSql, eventRowMapper(), roomId);

            room.setEvents(events);
        }

        return room;
    }

    private RowMapper<Room> roomRowMapper() {
        return (rs, rowNum) -> Room.builder()
                .id(rs.getLong("ROOM_ID"))
                .name(rs.getString("ROOM_NAME"))
                .number(rs.getString("ROOM_NUMBER"))
                .capacity(rs.getInt("ROOM_CAPACITY"))
                .building(rs.getLong("BUILDING_ID") != 0 ? Building.builder().id(rs.getLong("BUILDING_ID")).build() : null)
                .build();
    }
    private RowMapper<Event> eventRowMapper() {
        return (rs, rowNum) -> Event.builder()
                .id(rs.getLong("EVENT_ID"))
                .name(rs.getString("EVENT_NAME"))
                .startDateTime(rs.getTimestamp("START_DATE_TIME").toLocalDateTime()) // Map startDateTime
                .endDateTime(rs.getTimestamp("END_DATE_TIME").toLocalDateTime())     // Map endDateTime
                .build();
    }
}
