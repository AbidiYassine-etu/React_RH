package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Presence;
import com.example.React_back.Repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService {

    private final PresenceRepository presenceRepository;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    // Method to get all presences
    public List<Presence> getAllPresences() {
        return presenceRepository.findAll();
    }

    // Method to get presences by employeeId
    public List<Presence> getPresencesByEmployeeId(Long employeeId) {
        return presenceRepository.findByEmployeeId(employeeId);
    }
}
