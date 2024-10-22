package be.kdg.event.service.Impl;

import be.kdg.event.repository.BuildingRepository;
import be.kdg.event.repository.EventRepository;
import be.kdg.event.repository.RoomRepository;
import be.kdg.event.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final EventRepository eventRepository;
    private final RoomRepository roomRepository;
    private final BuildingRepository buildingRepository;


    public StatisticsServiceImpl(EventRepository eventRepository, RoomRepository roomRepository, BuildingRepository buildingRepository) {
        this.eventRepository = eventRepository;
        this.roomRepository = roomRepository;
        this.buildingRepository = buildingRepository;
    }

    public List<Long> getTotalCounts() {
        Long events= eventRepository.countEvents();
        Long rooms = roomRepository.countRoom();
        Long buildigs= buildingRepository.countBuilding();

        return Arrays.asList(events,rooms,buildigs);
    }
}
