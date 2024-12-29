package be.kdg.event.service.jpa;


import be.kdg.event.repository.BuildingJpaRepository;
import be.kdg.event.repository.EventJpaRepository;
import be.kdg.event.repository.RoomJpaRepository;
import be.kdg.event.service.StatisticsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("spring-data-jpa")
public class StatisticsJpaServiceImpl implements StatisticsService {
    private final EventJpaRepository eventJpaRepository;
    private final RoomJpaRepository roomJpaRepository;
    private final BuildingJpaRepository buildingJpaRepository;

    public StatisticsJpaServiceImpl(EventJpaRepository eventJpaRepository, RoomJpaRepository roomJpaRepository, BuildingJpaRepository buildingJpaRepository) {
        this.eventJpaRepository = eventJpaRepository;
        this.roomJpaRepository = roomJpaRepository;
        this.buildingJpaRepository = buildingJpaRepository;
    }

    @Override
    public List<Long> getTotalCounts() {
        long events = eventJpaRepository.count();
        long rooms = roomJpaRepository.count();
        long buildings = buildingJpaRepository.count();

        return Arrays.asList(events, rooms, buildings);
    }
}
