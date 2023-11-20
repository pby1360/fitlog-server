package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.WorkoutProgramDto;
import com.fitlog.server.domain.workout.service.WorkoutProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/workout-programs")
public class WorkoutProgramController {

    private WorkoutProgramService service;

    public WorkoutProgramController(WorkoutProgramService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getProgramList(@RequestParam Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.list(userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProgramDetail (@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.detail(id));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity addProgram(@RequestBody WorkoutProgramDto newProgram) {
        try {
            service.add(newProgram);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyProgram(@PathVariable Long id, @RequestBody WorkoutProgramDto program) {
        try {
            service.modify(program);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity deleteProgram(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
