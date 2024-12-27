package com.example.React_back.Controller;

import com.example.React_back.Models.Presence;
import com.example.React_back.Services.Impl.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presences")
public class PresenceController {

    private final PresenceService presenceService;

    @Autowired
    public PresenceController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    // Endpoint to get all presences
    @GetMapping
    public ResponseEntity<List<Presence>> getAllPresences() {
        List<Presence> presences = presenceService.getAllPresences();
        return new ResponseEntity<>(presences, HttpStatus.OK);
    }

    // Endpoint to get presences by employeeId
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Presence>> getPresencesByEmployeeId(@PathVariable Long employeeId) {
        List<Presence> presences = presenceService.getPresencesByEmployeeId(employeeId);
        return new ResponseEntity<>(presences, HttpStatus.OK);
    }
}
