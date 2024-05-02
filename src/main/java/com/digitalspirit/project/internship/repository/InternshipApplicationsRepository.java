package com.digitalspirit.project.internship.repository;

import com.digitalspirit.project.internship.entity.InternshipApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InternshipApplicationsRepository extends JpaRepository<InternshipApplications, Long> {
    @Query("FROM InternshipApplications WHERE internship_id = ?1 AND intern_id = ?2")
    Optional<InternshipApplications> findByInternshipIdAndInternId(Long internshipId, Long internId);

    @Query("FROM InternshipApplications WHERE internship_id = ?1")
    Optional<List<InternshipApplications>> findByInternshipId(Long internshipId);
}
