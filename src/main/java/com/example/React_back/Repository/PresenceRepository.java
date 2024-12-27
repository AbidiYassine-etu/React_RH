package com.example.React_back.Repository;

import com.example.React_back.Models.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByEmployeeId(Long employeeId);
}
