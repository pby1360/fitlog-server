package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.WorkoutProgramPartItemSetDto;
import com.fitlog.server.domain.workout.dto.WorkoutRoutineDto;
import com.fitlog.server.domain.workout.entity.WorkoutRoutine;
import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;
import com.fitlog.server.domain.workout.entity.WorkoutRoutinePart;
import com.fitlog.server.domain.workout.entity.WorkoutRoutineSet;
import com.fitlog.server.domain.workout.repository.WorkoutRoutineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkoutRoutineService {

    private WorkoutRoutineRepository repository;
    private WorkoutProgramPartItemSetService programPartItemSetService;

    public WorkoutRoutineService(WorkoutRoutineRepository repository, WorkoutProgramPartItemSetService programPartItemSetService) {
        this.repository = repository;
        this.programPartItemSetService = programPartItemSetService;
    }

    public WorkoutRoutineDto create (WorkoutRoutineDto routineDto) {
        WorkoutRoutine routine = WorkoutRoutine.create(routineDto.userId(), routineDto.workoutProgramId(), routineDto.name(), routineDto.description());

        List<WorkoutRoutinePart> parts =
                routineDto.workoutRoutinePartList()
                        .stream()
                        .map(partDto -> {
                            WorkoutRoutinePart part = WorkoutRoutinePart.create(routine, partDto.workoutPartId(), partDto.name(), partDto.description(), partDto.order());
                            partDto.workoutRoutineItemList().stream().map(itemDto -> {
                                WorkoutRoutineItem item = WorkoutRoutineItem.create(
                                        part,
                                        itemDto.workoutPartItemId(),
                                        itemDto.name(),
                                        itemDto.description(),
                                        itemDto.order());

                                List<WorkoutProgramPartItemSetDto> programPartItemSetDtoList = programPartItemSetService.list(itemDto.workoutProgramPartItemId());
                                programPartItemSetDtoList.stream().map(dto -> WorkoutRoutineSet.create(item, dto.description(), dto.order(),dto.count(),dto.restTime())).forEach(set -> item.getWorkoutRoutineSets().add(set));
                                return item;
                            }).forEach(item -> part.getWorkoutRoutineItems().add(item));
                            return part;
                        }).toList();

        parts.forEach(part -> routine.getWorkoutRoutineParts().add(part));
        repository.save(routine);
        return WorkoutRoutineDto.toDto(routine);
    }
}
