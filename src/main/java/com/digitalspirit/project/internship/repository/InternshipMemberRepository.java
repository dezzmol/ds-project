package com.digitalspirit.project.internship.repository;

import com.digitalspirit.project.internship.entity.InternshipMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InternshipMemberRepository extends JpaRepository<InternshipMember, Long> {
    boolean existsByInternshipIdAndInternId(Long internshipId, Long internId);

    Optional<InternshipMember> findByInternshipIdAndInternId(Long internshipId, Long internId);

    Optional<List<InternshipMember>> findByInternshipId(Long internshipId);
}
