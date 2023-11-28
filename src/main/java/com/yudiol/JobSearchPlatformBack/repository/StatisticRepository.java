package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

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

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.hrInterview=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setHrInterview(Long companyId, String userId, LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.techInterview=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setTechInterview(Long companyId, String userId, LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.caseInterview=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setCaseInterview(Long companyId, String userId, LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.managerInterview=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setManagerInterview(Long companyId, String userId, LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.offer=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setOffer(Long companyId, String userId, LocalDate date);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic AS st " +
            " SET st.tests=:date " +
            " where st.companyId=:companyId and st.userId=:userId")
    void setTests(Long companyId, String userId, LocalDate date);
}
