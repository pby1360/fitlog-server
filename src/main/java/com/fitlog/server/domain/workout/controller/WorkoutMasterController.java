package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.service.WorkoutMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/workout-master")
public class WorkoutMasterController {

    private WorkoutMasterService service;

    public WorkoutMasterController(WorkoutMasterService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/parts")
    public ResponseEntity getWorkoutPartList(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPartList(userId));
    }

    @GetMapping("/{userId}/parts/{partId}")
    public ResponseEntity getWorkoutPart(@PathVariable Long userId, @PathVariable Long partId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPart(partId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/{userId}/parts")
    public ResponseEntity addWorkoutPart (@PathVariable Long userId, @RequestBody WorkoutPartDto workoutPart) {
        service.addWorkoutPart(workoutPart);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
