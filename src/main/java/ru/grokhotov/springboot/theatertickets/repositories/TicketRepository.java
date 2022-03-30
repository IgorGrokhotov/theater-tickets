package ru.grokhotov.springboot.theatertickets.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grokhotov.springboot.theatertickets.repositories.entities.TicketEntity;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, String> {

    @Transactional
    @Modifying
    @Query("delete from TicketEntity t where t.perfomance = :name")
    void deleteByPerfomance(@Param("name") String name);

    Integer countByPerfomanceAndStatus(@Param("perfomance") String perfomance, @Param("status") String status);

    @Query(value = "select * from ticket t where t.perfomance = :perfomance order by place", nativeQuery = true)
    List<TicketEntity> findAllByPerfomance(@Param("perfomance") String perfomance);

    @Query(value = "select * from ticket t where t.perfomance = :perfomance and t.place = :place and t.status = 'доступен'", nativeQuery = true)
    TicketEntity findFreePlaceTicketByPerfomance(@Param("perfomance") String perfomance, @Param("place") Integer place);

    @Query(value = "select * from ticket t where t.perfomance = :perfomance and t.place = :place and t.status = 'продан'", nativeQuery = true)
    TicketEntity findSoldPlaceTicketByPerfomance(@Param("perfomance") String perfomance, @Param("place") Integer place);

}
