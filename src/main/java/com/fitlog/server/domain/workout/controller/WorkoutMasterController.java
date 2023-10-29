package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.service.WorkoutPartItemService;
import com.fitlog.server.domain.workout.service.WorkoutPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/workout-master")
public class WorkoutMasterController {

    private WorkoutPartService service;
    private WorkoutPartItemService itemService;

    public WorkoutMasterController(WorkoutPartService service, WorkoutPartItemService itemService) {
        this.service = service;
        this.itemService = itemService;
    }

    @GetMapping("/{userId}/parts")
    public ResponseEntity getWorkoutPartList(@PathVariable Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPartList(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}/parts/{partId}")
    public ResponseEntity getWorkoutPart(@PathVariable Long userId, @PathVariable Long partId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPart(partId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{userId}/parts")
    public ResponseEntity addWorkoutPart (@PathVariable Long userId, @RequestBody WorkoutPartDto workoutPart) {
        try {
            service.addWorkoutPart(workoutPart);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{userId}/parts")
    public ResponseEntity modifyWorkoutPart (@PathVariable Long userId, @RequestBody WorkoutPartDto workoutPart) {
        try {
            service.modifyWorkoutPart(workoutPart);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{userId}/parts/{partId}")
    public ResponseEntity deleteWorkoutPart (@PathVariable Long userId, @PathVariable Long partId) {
        try {
            service.deleteWorkoutPart(partId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}/parts/{partId}/items")
    public ResponseEntity getWorkoutPartWithItems(@PathVariable Long userId, @PathVariable Long partId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getWorkoutPartWithItems(partId));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{userId}/parts/{partId}/items/{itemId}")
    public ResponseEntity getWorkoutPartItem(@PathVariable Long userId, @PathVariable Long partId, @PathVariable Long itemId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getWorkoutPartItemDetail(itemId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{userId}/parts/{partId}/items")
    public ResponseEntity addWorkoutPartItem (@PathVariable Long userId, @PathVariable Long partId, @RequestBody WorkoutPartItemDto workoutPartItemDto) {
        try {
            itemService.addWorkoutPartItem(workoutPartItemDto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{userId}/parts/{partId}/items/{itemId}")
    public ResponseEntity modifyWorkoutPartItem (@PathVariable Long userId, @PathVariable Long partId, @PathVariable Long itemId, @RequestBody WorkoutPartItemDto workoutPartItem) {
        try {
            itemService.modifyWorkoutPartItem(workoutPartItem);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{userId}/parts/{partId}/items/{itemId}")
    public ResponseEntity deleteWorkoutPartItem (@PathVariable Long userId, @PathVariable Long partId, @PathVariable Long itemId) {
        try {
            itemService.deleteWorkoutPartItem(itemId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
