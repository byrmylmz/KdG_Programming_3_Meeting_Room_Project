package be.kdg.event.repository.JdbcTemplateRepository;

import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String sql = "SELECT * FROM BUILDINGS WHERE BUILDING_ID = ?"; // Corrected column name
        return jdbcTemplate.queryForObject(sql, buildingRowMapper(), id);
    }

    @Override
    public long countBuildings() {
        String sql = "SELECT COUNT(*) FROM BUILDINGS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void save(Building building) {
        String sql = "INSERT INTO BUILDINGS (NAME, ADDRESS, NUMBER_OF_FLOORS) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, building.getName(), building.getAddress(), building.getNumberOfFloors());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM BUILDINGS WHERE BUILDING_ID = ?"; // Corrected column name
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Building building) {
        String sql = "UPDATE BUILDINGS SET NAME = ?, ADDRESS = ?, NUMBER_OF_FLOORS = ? WHERE BUILDING_ID = ?"; // Corrected column name
        jdbcTemplate.update(
                sql,
                building.getName(),
                building.getAddress(),
                building.getNumberOfFloors(),
                building.getBuildingID()
        );
    }

    private RowMapper<Building> buildingRowMapper() {
        return (rs, rowNum) -> Building.builder()
                .buildingID(rs.getLong("BUILDING_ID")) // Corrected column name
                .name(rs.getString("NAME"))
                .address(rs.getString("ADDRESS"))
                .numberOfFloors(rs.getInt("NUMBER_OF_FLOORS"))
                .build();
    }
}