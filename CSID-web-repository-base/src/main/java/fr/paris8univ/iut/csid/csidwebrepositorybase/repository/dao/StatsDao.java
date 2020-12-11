package fr.paris8univ.iut.csid.csidwebrepositorybase.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StatsDao extends JpaRepository<StatsEntity,Integer>{

    @Query(value = "select * from stats where name= ?1 and entry_type= ?2 order by id asc ",nativeQuery=true)
    List<StatsEntity> findAllOrdeById(String name, String statType);

}