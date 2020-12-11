package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.git;

import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.core.Stats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao.StatsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao.StatsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsRepository {

    private final StatsDao statsDao;

    @Autowired
    public StatsRepository(StatsDao statsDao) {
        this.statsDao = statsDao;
    }


    public List<Stats> getStats(String name,String entry_type){
//      List<StatsEntity> statsEntities = statsDao.findAll();

        List <StatsEntity> statsEntities = statsDao.findAllOrdeById(name, entry_type);

        return statsEntities.stream()
                .map(x-> new Stats(x.getId(),x.getEntry_type(),x.getDatetime(),x.getValue(),x.getName()))
                .collect(Collectors.toList());
    }


}
