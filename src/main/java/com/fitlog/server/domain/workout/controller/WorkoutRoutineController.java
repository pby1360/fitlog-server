package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.WorkoutRoutineDto;
import com.fitlog.server.domain.workout.entity.WorkoutRoutine;
import com.fitlog.server.domain.workout.service.WorkoutRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workout-routines")
@Slf4j
public class WorkoutRoutineController {

    private WorkoutRoutineService service;

    public WorkoutRoutineController(WorkoutRoutineService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity createRoutine (@RequestBody WorkoutRoutineDto workoutRoutine) {
        try {
            WorkoutRoutineDto dto = service.create(workoutRoutine);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.createRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }
}
