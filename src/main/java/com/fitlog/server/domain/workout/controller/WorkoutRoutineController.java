package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.WorkoutRoutineDto;
import com.fitlog.server.domain.workout.service.WorkoutRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

//    @GetMapping("/{id}")
//    public ResponseEntity getRoutine (@PathVariable Long id) {
//        try {
//            WorkoutRoutineDto dto = service.getRoutine(id);
//            return ResponseEntity.status(HttpStatus.OK).body(dto);
//        } catch (Exception e) {
//            log.error("[WorkoutRoutineController.createRoutine] Unknown Exception", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity getRoutine (@PathVariable Long id) {
        try {
//            WorkoutRoutineDto dto = service.getRoutine(id);
            return ResponseEntity.status(HttpStatus.OK).body(service.getCurrentRoutine(id));
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.createRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/start")
    public ResponseEntity startRoutine (@PathVariable Long id) {
        try {
            service.startRoutine(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity finishRoutine (@PathVariable Long id) {
        try {
            service.finishRoutine(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/clear")
    public ResponseEntity clearRoutine (@PathVariable Long id) {
        try {
            service.clearRoutine(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{routinePartId}/items/{routineItemId}/set")
    public ResponseEntity setRoutineItem (@PathVariable Long id, @PathVariable Long routinePartId, @PathVariable Long routineItemId, @RequestBody int count) {
        try {
            service.setRoutineItem(routineItemId, count);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{routinePartId}/items/{routineItemId}/clear")
    public ResponseEntity resetRoutineItem (@PathVariable Long id, @PathVariable Long routinePartId, @PathVariable Long routineItemId) {
        try {
            service.clearRoutineItem(routineItemId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{routinePartId}/items/{routineItemId}/start")
    public ResponseEntity startRoutineItem (@PathVariable Long id, @PathVariable Long routinePartId, @PathVariable Long routineItemId) {
        try {
            service.startItem(routineItemId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{routinePartId}/items/{routineItemId}/finish")
    public ResponseEntity finishRoutineItem (@PathVariable Long id, @PathVariable Long routinePartId, @PathVariable Long routineItemId) {
        try {
            service.finishRoutineItem(routinePartId, routineItemId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException | IllegalStateException e)  {
            log.error("[WorkoutRoutineController.startRoutine] Bad Request", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error("[WorkoutRoutineController.startRoutine] Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
