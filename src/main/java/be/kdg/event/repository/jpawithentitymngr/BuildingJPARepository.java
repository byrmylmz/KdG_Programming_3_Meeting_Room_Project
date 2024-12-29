package be.kdg.event.repository.jpawithentitymngr;


import be.kdg.event.model.Building;
import be.kdg.event.repository.BuildingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile({"dev", "prod"})
@Transactional
public class BuildingJPARepository implements BuildingRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Building> findAll() {
        return entityManager.createQuery("SELECT b FROM Building b", Building.class).getResultList();
    }

    @Override
    public Building findById(Long id) {
        return entityManager.find(Building.class, id);
    }

    @Override
    public long countBuildings() {
        return entityManager.createQuery("SELECT COUNT(b) FROM Building b", Long.class).getSingleResult();
    }

    @Override
    public void save(Building building) {
        entityManager.persist(building);
    }

    @Override
    public void update(Building building) {
        entityManager.merge(building);
    }

    @Override
    public void delete(Long id) {
        Building building = findById(id);
        if (building != null) {
            entityManager.remove(building);
        }
    }
}
