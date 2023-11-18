package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLinkRepository extends JpaRepository<Link, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "SELECT * FROM user_links ul " +
            "WHERE ul.user_id = ?1", nativeQuery = true)
    Optional<List<Link>> findAllByUserId(String id);
}
