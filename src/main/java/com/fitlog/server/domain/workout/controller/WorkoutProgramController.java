package com.fitlog.server.domain.workout.controller;

import com.fitlog.server.domain.workout.dto.*;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import com.fitlog.server.domain.workout.entity.WorkoutProgramPartItemSet;
import com.fitlog.server.domain.workout.service.WorkoutProgramPartItemService;
import com.fitlog.server.domain.workout.service.WorkoutProgramPartItemSetService;
import com.fitlog.server.domain.workout.service.WorkoutProgramPartService;
import com.fitlog.server.domain.workout.service.WorkoutProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/workout-programs")
public class WorkoutProgramController {

    private WorkoutProgramService service;
    private WorkoutProgramPartService partService;
    private WorkoutProgramPartItemService partItemService;
    private WorkoutProgramPartItemSetService partItemSetService;

    public WorkoutProgramController(WorkoutProgramService service, WorkoutProgramPartService partService, WorkoutProgramPartItemService partItemService, WorkoutProgramPartItemSetService partItemSetService) {
        this.service = service;
        this.partService = partService;
        this.partItemService = partItemService;
        this.partItemSetService = partItemSetService;
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
            partService.deleteAll(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts")
    public ResponseEntity addProgramPart(@RequestBody WorkoutProgramPartDto newPart) {
        log.info(newPart.toString());

        try {
            partService.add(newPart);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/parts/{partId}")
    public ResponseEntity getProgramPartDetail(@PathVariable Long id, @PathVariable Long partId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(partService.detail(partId));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/parts")
    public ResponseEntity getProgramWithPartList(@PathVariable Long id) {
        try {
            WorkoutProgramDto program= service.detail(id);
            List<WorkoutProgramPartDto> programPartList = partService.list(id);
            return ResponseEntity.status(HttpStatus.OK).body(WorkoutProgramWithPartsDto.of(program, programPartList));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/{id}/parts")
//    public ResponseEntity getProgramPartList(@PathVariable Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(partService.list(id));
//        } catch (NoSuchElementException e) {
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @PutMapping ("/{id}/parts/{partId}")
    public ResponseEntity modifyProgramPart(@PathVariable Long id, @PathVariable Long partId, @RequestBody WorkoutProgramPartDto dto) {
        try {
            partService.modify(dto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping ("/{id}/parts/{partId}")
    public ResponseEntity deleteProgramPart(@PathVariable Long id, @PathVariable Long partId) {
        try {
            partService.delete(partId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/parts/{partId}/items/{itemId}")
    public ResponseEntity getProgramPartItemDetail(@PathVariable Long id, @PathVariable Long partId, @PathVariable Long itemId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(partItemService.detail(itemId));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/parts/{partId}/items")
    public ResponseEntity getProgramPartItemList(@PathVariable Long id, @PathVariable Long partId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(partItemService.list(partId));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{partId}/items")
    public ResponseEntity addProgramPartItem(@RequestBody WorkoutProgramPartItemDto newPartItem) {
        try {
            partItemService.add(newPartItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/parts/{partId}/items")
    public ResponseEntity modifyProgramPartItemOrder(@RequestBody List<WorkoutProgramPartItemDto> itemList) {
        try {
            partItemService.modifyOrder(itemList);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/parts/{partId}/items/{itemId}")
    public ResponseEntity modifyProgramPartItem(@RequestBody WorkoutProgramPartItemDto item) {
        try {
            partItemService.modify(item);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/parts/{partId}/items/{itemId}")
    public ResponseEntity deleteProgramPartItem(@PathVariable Long itemId) {
        try {
            partItemService.delete(itemId);
            partItemSetService.deleteByWorkoutProgramPartItemId(itemId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/parts/{partId}/items/{itemId}/sets/{setId}")
    public ResponseEntity getProgramPartItemSetDetail(@PathVariable Long setId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(partItemSetService.detail(setId));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/{id}/parts/{partId}/items/{itemId}/sets")
//    public ResponseEntity getProgramPartItemSetList(@PathVariable Long id, @PathVariable Long partId) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(partItemSetService.list(partId));
//        } catch (NoSuchElementException e) {
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    @GetMapping("/{id}/parts/{partId}/items/{itemId}/sets")
    public ResponseEntity getProgramPartItemSetList(@PathVariable Long itemId) {
        try {
            WorkoutProgramPartItemDto item = partItemService.detail(itemId);
            List<WorkoutProgramPartItemSetDto> setList = partItemSetService.list(itemId);
            return ResponseEntity.status(HttpStatus.OK).body(WorkoutProgramPartItemWithSetsDto.of(item, setList));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/parts/{partId}/items/{itemId}/sets")
    public ResponseEntity addProgramPartItemSet(@RequestBody WorkoutProgramPartItemSetDto newPartItemSet) {
        try {
            partItemSetService.add(newPartItemSet);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/parts/{partId}/items/{itemId}/sets")
    public ResponseEntity modifyProgramPartItemSetOrder(@RequestBody List<WorkoutProgramPartItemSetDto> setList) {
        try {
            partItemSetService.modifyOrder(setList);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/parts/{partId}/items/{itemId}/sets/{setId}")
    public ResponseEntity modifyProgramPartItemSet(@RequestBody WorkoutProgramPartItemSetDto itemSet) {
        try {
            partItemSetService.modify(itemSet);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/parts/{partId}/items/{itemId}/sets/{setId}")
    public ResponseEntity deleteProgramPartItemSet(@PathVariable Long setId) {
        try {
            partItemSetService.delete(setId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
