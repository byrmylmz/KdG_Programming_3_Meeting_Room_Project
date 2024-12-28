package be.kdg.event.repository.JdbcTemplateRepository;

import be.kdg.event.model.Event;
import be.kdg.event.repository.EventRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class EventJdbcTemplateRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> findAll() {
        String sql = "SELECT * FROM EVENTS";
        return jdbcTemplate.query(sql, eventRowMapper());
    }

    @Override
    public Optional<Event> findById(Long id) {
        String sql = "SELECT * FROM EVENTS WHERE EVENT_ID = ?";
        List<Event> events = jdbcTemplate.query(sql, eventRowMapper(), id);
        return events.isEmpty() ? Optional.empty() : Optional.of(events.get(0));
    }

    @Override
    public long countEvents() {
        String sql = "SELECT COUNT(*) FROM EVENTS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void save(Event event) {
        String sql = "INSERT INTO EVENTS (EVENT_NAME, START_DATE_TIME, END_DATE_TIME) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                event.getName(),
                event.getStartDateTime(),
                event.getEndDateTime()
        );

        String eventIdSql = "SELECT EVENT_ID FROM EVENTS ORDER BY EVENT_ID DESC LIMIT 1";
        Long eventId = jdbcTemplate.queryForObject(eventIdSql, Long.class);

        if (event.getRoomIdList() != null) {
            for (Long roomId : event.getRoomIdList()) {
                String eventRoomSql = "INSERT INTO EVENT_ROOMS (EVENT_ID, ROOM_ID) VALUES (?, ?)";
                jdbcTemplate.update(eventRoomSql, eventId, roomId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        String deleteEventRoomsSql = "DELETE FROM EVENT_ROOMS WHERE EVENT_ID = ?";
        jdbcTemplate.update(deleteEventRoomsSql, id);

        String deleteEventSql = "DELETE FROM EVENTS WHERE EVENT_ID = ?";
        jdbcTemplate.update(deleteEventSql, id);
    }

    public void assignRoomToEvent(Long eventId, Long roomId) {
        String sql = "INSERT INTO EVENT_ROOMS (EVENT_ID, ROOM_ID) VALUES (?, ?)";
        jdbcTemplate.update(sql, eventId, roomId);
    }

    public void removeRoomFromEvent(Long eventId, Long roomId) {
        String sql = "DELETE FROM EVENT_ROOMS WHERE EVENT_ID = ? AND ROOM_ID = ?";
        jdbcTemplate.update(sql, eventId, roomId);
    }

    public List<Long> findRoomIdsByEvent(Long eventId) {
        String sql = "SELECT ROOM_ID FROM EVENT_ROOMS WHERE EVENT_ID = ?";
        return jdbcTemplate.queryForList(sql, Long.class, eventId);
    }
    
    

    @Override
    public void update(Event event) {
        String updateEventSql = "UPDATE EVENTS SET EVENT_NAME = ?, START_DATE_TIME = ?, END_DATE_TIME = ? WHERE EVENT_ID = ?";
        jdbcTemplate.update(updateEventSql,
                event.getName(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                event.getId()
        );

        String deleteEventRoomsSql = "DELETE FROM EVENT_ROOMS WHERE EVENT_ID = ?";
        jdbcTemplate.update(deleteEventRoomsSql, event.getId());

        if (event.getRoomIdList() != null) {
            for (Long roomId : event.getRoomIdList()) {
                String eventRoomSql = "INSERT INTO EVENT_ROOMS (EVENT_ID, ROOM_ID) VALUES (?, ?)";
                jdbcTemplate.update(eventRoomSql, event.getId(), roomId);
            }
        }
    }

    private RowMapper<Event> eventRowMapper() {
        return (rs, rowNum) -> Event.builder()
                .id(rs.getLong("EVENT_ID"))
                .name(rs.getString("EVENT_NAME"))
                .startDateTime(rs.getTimestamp("START_DATE_TIME").toLocalDateTime()) // Updated column names
                .endDateTime(rs.getTimestamp("END_DATE_TIME").toLocalDateTime())     // Updated column names
                .build();
    }
}