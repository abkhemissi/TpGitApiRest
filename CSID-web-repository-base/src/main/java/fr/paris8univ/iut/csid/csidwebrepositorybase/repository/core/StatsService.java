package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core;

import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.git.StatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Stats> getStats(String name,String entry_type){
        return statsRepository.getStats(name, entry_type);
    }
}
