package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.service.WorkoutMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/workout-master")
public class WorkoutMasterController {

    private WorkoutMasterService service;

    public WorkoutMasterController(WorkoutMasterService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/part")
    public ResponseEntity getWorkoutPartList(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPartList(userId));
    }
    @PostMapping("/{userId}/part")
    public ResponseEntity addWorkoutPart (@PathVariable Long userId, @RequestBody WorkoutPart part) {
        log.info(part.toString());
        service.addWorkoutPart(part);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
