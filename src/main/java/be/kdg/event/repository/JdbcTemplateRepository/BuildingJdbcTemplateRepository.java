package be.kdg.event.repository.JdbcTemplateRepository;

import be.kdg.event.model.Building;
import be.kdg.event.model.Room;
import be.kdg.event.repository.BuildingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class BuildingJdbcTemplateRepository implements BuildingRepository {

    private final JdbcTemplate jdbcTemplate;

    public BuildingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Building> findAll() {
        String sql = "SELECT * FROM BUILDINGS";
        return jdbcTemplate.query(sql, buildingRowMapper());
    }

    @Override
    public Building findById(Long id) {
        String sql = """
    SELECT 
        b.BUILDING_ID, b.NAME AS BUILDING_NAME, b.ADDRESS,
        r.ROOM_ID, r.ROOM_NUMBER, r.ROOM_NAME, r.ROOM_CAPACITY
    FROM 
        BUILDINGS b
    LEFT JOIN 
        ROOMS r ON b.BUILDING_ID = r.BUILDING_ID
    WHERE 
        b.BUILDING_ID = ?
    """;

        return jdbcTemplate.query(sql, rs -> {
            Map<Long, Building> buildingMap = new HashMap<>();

            while (rs.next()) {
                Long buildingId = rs.getLong("BUILDING_ID");

                Building building = buildingMap.computeIfAbsent(buildingId, idValue -> {
                    Building b = new Building();
                    b.setId(idValue);
                    b.setName(getStringSafely(rs, "BUILDING_NAME"));
                    b.setAddress(getStringSafely(rs, "ADDRESS"));
                    b.setRooms(new ArrayList<>());
                    return b;
                });

                Long roomId = rs.getLong("ROOM_ID");
                if (!rs.wasNull()) {
                    Room room = new Room();
                    room.setId(roomId);
                    room.setNumber(getStringSafely(rs, "ROOM_NUMBER"));
                    room.setName(getStringSafely(rs, "ROOM_NAME"));
                    room.setCapacity(rs.getInt("ROOM_CAPACITY"));
                    building.getRooms().add(room);
                }
            }

            if (buildingMap.isEmpty()) {
                return null;
            }

            return buildingMap.values().iterator().next();
        }, id);
    }

    private String getStringSafely(ResultSet rs, String columnName) {
        try {
            String value = rs.getString(columnName);
            return value != null ? value : "";
        } catch (SQLException e) {
            System.err.println("Failed to retrieve column: " + columnName + ". Error: " + e.getMessage());
            return "";
        }
    }


    @Override
    public long countBuildings() {
        String sql = "SELECT COUNT(*) FROM BUILDINGS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void save(Building building) {
        String sql = "INSERT INTO BUILDINGS (NAME, ADDRESS) VALUES (?,?)";
        jdbcTemplate.update(sql, building.getName(), building.getAddress());
    }



    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM BUILDINGS WHERE BUILDING_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Building building) {
        String sql = "UPDATE BUILDINGS SET NAME = ?, ADDRESS = ?  WHERE BUILDING_ID = ?";
        jdbcTemplate.update(
                sql,
                building.getName(),
                building.getAddress(),
                building.getId()
        );
    }

    private RowMapper<Building> buildingRowMapper() {
        return (rs, rowNum) -> Building.builder()
                .id(rs.getLong("BUILDING_ID"))
                .name(rs.getString("NAME"))
                .address(rs.getString("ADDRESS"))
                .build();
    }
}