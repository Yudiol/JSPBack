package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Query(value = "SELECT count(*) " +
            " FROM Statistic st " +
            " WHERE st.userId = ?1 " +
            " AND EXTRACT ( month FROM st.hrInterview ) = ?2 " +
            " AND EXTRACT ( year FROM st.hrInterview ) = ?3 ")
    Long findTotalQuantityHrInterviewByUserId(String id, Integer month, Integer year);

    @Query(value = "SELECT count(*) " +
            " FROM Statistic st " +
            " WHERE st.userId = ?1 " +
            " AND EXTRACT ( month FROM st.tests ) = ?2 " +
            " AND EXTRACT ( year FROM st.tests ) = ?3 ")
    Long findTotalQuantityTestByUserId(String id, Integer month, Integer year);

}
