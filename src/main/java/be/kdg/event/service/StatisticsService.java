package be.kdg.event.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StatisticsService {
     List<Long> getTotalCounts();
}
