package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.dto.QuantityResponses;
import com.yudiol.JobSearchPlatformBack.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseTableRepository extends JpaRepository<Response, Long> {
    @Query(value = "SELECT count(*) " +
            " FROM Response r " +
            " WHERE r.userId = ?1 " +
            " AND EXTRACT ( month FROM r.responseDate ) = ?2 " +
            " AND EXTRACT ( year FROM r.responseDate ) = ?3 ")
    Long findTotalQuantityByUserId(String id, Integer month, Integer year);

    @Query(value = "SELECT new com.yudiol.JobSearchPlatformBack.dto.QuantityResponses(r.responseDate AS date, count(*) AS count )" +
            " FROM Response r " +
            " WHERE r.userId = ?1 " +
            " AND EXTRACT ( month FROM r.responseDate ) = ?2 " +
            " AND EXTRACT ( year FROM r.responseDate ) = ?3 " +
            " GROUP BY r.responseDate ")
    List<QuantityResponses> findAllByMonthAndYear(String id, Integer month, Integer year);

    Optional<List<Response>> findAllByUserId(String userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Response r where r.id=:id and r.userId=:userId")
    void deleteByIdAndByUserId(String userId, Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Response AS r " +
            " SET r.name=:name, " +
            " r.link=:link, " +
            " r.position=:position, " +
            " r.contact=:contact, " +
            " r.status=:status, " +
            " r.comments=:comment " +
            " where r.id=:id and r.userId=:userId ")
    void update(String userId, Long id, String name, String link, String position, String contact, Integer status, String comment);
}
