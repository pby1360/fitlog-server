package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.dto.*;
import com.fitlog.server.domain.workout.entity.WorkoutRoutine;
import com.fitlog.server.domain.workout.entity.WorkoutRoutineItem;
import com.fitlog.server.domain.workout.entity.WorkoutRoutinePart;
import com.fitlog.server.domain.workout.entity.WorkoutRoutineSet;
import com.fitlog.server.domain.workout.repository.WorkoutRoutineRepository;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class WorkoutRoutineService {

    private WorkoutRoutineRepository repository;
    private WorkoutRoutinePartService routinePartService;
    private WorkoutRoutineItemService routineItemService;
    private WorkoutRoutineSetService routineSetService;
    private WorkoutProgramPartItemSetService programPartItemSetService;

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

    public WorkoutRoutineDto getRoutine (Long id) {
        return repository.findById(id).map(entity -> WorkoutRoutineDto.toDto(entity)).orElseThrow();
    }

    public Routine getCurrentRoutine (Long id) {

        WorkoutRoutine routine = repository.findById(id).orElseThrow();

        WorkoutRoutinePart currentPart = null;

        if (routine.getWorkoutRoutineParts().stream().anyMatch(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.중지)) {
            currentPart = routine.getWorkoutRoutineParts().stream().filter(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.중지).findFirst().get();
        } else if (routine.getWorkoutRoutineParts().stream().anyMatch(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.진행중)) {
            currentPart = routine.getWorkoutRoutineParts().stream().filter(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.진행중).findFirst().get();
        } else if (routine.getWorkoutRoutineParts().stream().anyMatch(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.대기)) {
            currentPart = routine.getWorkoutRoutineParts().stream().filter(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.대기).findFirst().get();
        } else if (routine.getWorkoutRoutineParts().stream().allMatch(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.완료)) {
            currentPart = routine.getWorkoutRoutineParts().stream().reduce((first, second) -> second).get();
        } else {
            currentPart = new WorkoutRoutinePart();
        }

        WorkoutRoutineItem currentItem = null;

        if (currentPart.getWorkoutRoutineItems().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.중지)) {
            currentItem = currentPart.getWorkoutRoutineItems().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.중지).findFirst().get();
        } else if (currentPart.getWorkoutRoutineItems().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.진행중)) {
            currentItem = currentPart.getWorkoutRoutineItems().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.진행중).findFirst().get();
        } else if (currentPart.getWorkoutRoutineItems().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.대기)) {
            currentItem = currentPart.getWorkoutRoutineItems().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.대기).findFirst().get();
        } else if (currentPart.getWorkoutRoutineItems().stream().allMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.완료)) {
            currentItem = currentPart.getWorkoutRoutineItems().stream().reduce((first, second) -> second).get();
        } else {
            currentItem = new WorkoutRoutineItem();
        }

        /*현재 운동 완료한세트/목표세트*/
        int currentSetCount = currentItem.getWorkoutRoutineSets().stream().filter(set -> ProgressStatus.of(set.getStatus()) == ProgressStatus.완료).collect(Collectors.toList()).size();
        int targetSetCount = currentItem.getWorkoutRoutineSets().size();

        /*현재 운동 전체 완료한 횟수/전체 목표 횟수*/
        int  currentTotalCount = currentItem.getWorkoutRoutineSets().stream().filter(set -> ProgressStatus.of(set.getStatus()) == ProgressStatus.완료).map(WorkoutRoutineSet::getCount).mapToInt(Integer::intValue).sum();
        int  targetTotalCount = currentItem.getWorkoutRoutineSets().stream().map(WorkoutRoutineSet::getTargetCount).mapToInt(Integer::intValue).sum();


        WorkoutRoutineSet currentSet = null;

        if (currentItem.getWorkoutRoutineSets().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.중지)) {
            currentSet = currentItem.getWorkoutRoutineSets().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.중지).findFirst().get();
        } else if (currentItem.getWorkoutRoutineSets().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.진행중)) {
            currentSet = currentItem.getWorkoutRoutineSets().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.진행중).findFirst().get();
        } else if (currentItem.getWorkoutRoutineSets().stream().anyMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.대기)) {
            currentSet = currentItem.getWorkoutRoutineSets().stream().filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.대기).findFirst().get();
        } else if (currentItem.getWorkoutRoutineSets().stream().allMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.완료)) {
            currentSet = currentItem.getWorkoutRoutineSets().stream().reduce((first, second) -> second).get();
        } else {
            currentSet = new WorkoutRoutineSet();
        }

        int currentCount = currentSet.getCount() == null ? 0 : currentSet.getCount();
        int targetCount = currentSet.getTargetCount();
        int partCount = routine.getWorkoutRoutineParts().stream().filter(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.완료).collect(Collectors.toList()).size();
        int totalPartCount = routine.getWorkoutRoutineParts().size();
        int itemCount =
                routine.getWorkoutRoutineParts().stream()
                        .map(part ->
                                part.getWorkoutRoutineItems().stream()
                                        .filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.완료)
                                        .collect(Collectors.toList()).size())
                        .mapToInt(Integer::intValue).sum();
        int totalItemCount = routine.getWorkoutRoutineParts().stream().map(part -> part.getWorkoutRoutineItems().size()).mapToInt(Integer::intValue).sum();

        int setCount = routine.getWorkoutRoutineParts().stream()
                .map(part ->
                        part.getWorkoutRoutineItems().stream()
                                .filter(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.완료 || ProgressStatus.of(item.getStatus()) == ProgressStatus.진행중)
                                .map(item ->
                                        item.getWorkoutRoutineSets().stream()
                                                .filter(set ->
                                                        ProgressStatus.of(set.getStatus()) == ProgressStatus.완료)
                                                .collect(Collectors.toList()).size()
                                ).mapToInt(Integer::intValue).sum()
                ).mapToInt(Integer::intValue).sum();

        int totalSetCount = routine.getWorkoutRoutineParts().stream()
                .map(part ->
                        part.getWorkoutRoutineItems().stream()
                                .map(item ->
                                        item.getWorkoutRoutineSets().size()
                                ).mapToInt(Integer::intValue).sum()
                ).mapToInt(Integer::intValue).sum();

        RoutineCounter counter = new RoutineCounter(currentSetCount, targetSetCount, currentTotalCount, targetTotalCount, currentCount, targetCount);
        RoutineTimer timer = new RoutineTimer(routine.getStartedAt(), currentItem.getStartedAt(), currentSet.getTargetRestTime());
        RoutineChart chart = new RoutineChart(partCount, totalPartCount, itemCount, totalItemCount, setCount, totalSetCount);
        RoutineInfo routineInfo = new RoutineInfo(
                routine.getId(),
                routine.getName(),
                Optional.ofNullable(routine.getStartedAt()).map(time -> time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).orElse(null),
                Optional.ofNullable(routine.getFinishedAt()).map(time -> time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).orElse(null),
                routine.getDescription(),
                routine.getStatus(),
                ProgressStatus.of(routine.getStatus()),
                currentPart.getId(),
                currentPart.getName(),
                currentPart.getStatus(),
                ProgressStatus.of(currentPart.getStatus()),
                currentItem.getId(),
                currentItem.getName(),
                currentItem.getStatus(),
                ProgressStatus.of(currentItem.getStatus())
        );

        Routine currentRoutine  = new Routine(routineInfo, counter, timer, chart);



        return currentRoutine;
    }

    @Transactional(rollbackFor = Exception.class)
    public void startRoutine (Long routineId) {
        WorkoutRoutine routine = repository.findById(routineId).orElseThrow();
        routine.start();
        repository.save(routine);

        routine.getWorkoutRoutineParts()
                .stream()
                .sorted(Comparator.comparing(WorkoutRoutinePart::getOrder))
                .findFirst()
                .ifPresent(part -> routinePartService.startPart(part.getId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void finishRoutine (Long routineId) {
        WorkoutRoutine routine = repository.findById(routineId).orElseThrow();
        routine.finish();
        repository.save(routine);
        routine.getWorkoutRoutineParts()
                .stream()
                .filter(part -> part.getStatus().equals(ProgressStatus.진행중.getCode()))
                .findFirst()
                .ifPresent(part -> {
                    routinePartService.finishPart(part.getId());
                    part.getWorkoutRoutineItems()
                            .stream()
                            .filter(item -> item.getStatus().equals(ProgressStatus.진행중.getCode()))
                            .findFirst()
                            .ifPresent(item -> {
                                routineItemService.finishItem(item.getId());
                            });
                });
    }

    @Transactional(rollbackFor = Exception.class)
    public void clearRoutine (Long routineId) {
        WorkoutRoutine routine = repository.findById(routineId).orElseThrow();
        routine.clear(); /* routine clear */
        routine.getWorkoutRoutineParts().stream().forEach(part -> {
           routinePartService.clearPart(part.getId()); /* part clear */
           part.getWorkoutRoutineItems().stream().forEach(item -> {
               routineItemService.clearItem(item.getId()); /* item clear */
               routineSetService.clear(item.getId());
           });
        });
        repository.save(routine);
    }

    public void startItem (Long routineItemId) {
        routineItemService.startItem(routineItemId);
    }

    @Transactional
    public void setRoutineItem(Long routineItemId, int count) {
        routineItemService.startItem(routineItemId);
        routineSetService.set(routineItemId, count);
    }
    @Transactional
    public void finishRoutineItem(Long routinePartId, Long routineItemId) {
        routineItemService.finishItem(routineItemId);
        routinePartService.nextPart(routinePartId);
    }

    public void clearRoutineItem(Long routineItemId) {
        routineSetService.clear(routineItemId);
    }
}
