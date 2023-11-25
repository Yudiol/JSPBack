package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.dto.QuantityResponses;
import com.yudiol.JobSearchPlatformBack.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
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
}
