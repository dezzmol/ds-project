package com.digitalspirit.project.internship.repository;

import com.digitalspirit.project.internship.entity.InternshipApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InternshipApplicationsRepository extends JpaRepository<InternshipApplications, Long> {
    @Query("FROM InternshipApplications WHERE intern_id = ?1")
    Optional<InternshipApplications> findByInternId(Long internId);
}
