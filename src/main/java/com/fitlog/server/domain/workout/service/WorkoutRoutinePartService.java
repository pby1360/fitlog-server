package com.fitlog.server.domain.workout.service;

import com.fitlog.server.domain.workout.entity.WorkoutRoutinePart;
import com.fitlog.server.domain.workout.repository.WorkoutRoutinePartRepository;
import com.fitlog.server.domain.workout.type.ProgressStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutRoutinePartService {
    private final WorkoutRoutinePartRepository repository;

    public void startPart (Long id) {
        WorkoutRoutinePart part = repository.findById(id).orElseThrow();
        part.start();
        repository.save(part);
    }

    public void finishPart (Long id) {
        WorkoutRoutinePart part = repository.findById(id).orElseThrow();
        part.finish();
        repository.save(part);
    }

    public void clearPart(Long id) {
        WorkoutRoutinePart part = repository.findById(id).orElseThrow();
        if (!part.getStatus().equals(ProgressStatus.대기.getCode())) {
            part.clear();
            repository.save(part);
        }
    }

    public void nextPart (Long id) {
        WorkoutRoutinePart currentPart = repository.findById(id).orElseThrow();
        if (currentPart.getWorkoutRoutineItems()
                .stream()
                .allMatch(item -> ProgressStatus.of(item.getStatus()) == ProgressStatus.완료)) {

            currentPart.finish();
            repository.save(currentPart);

            currentPart.getWorkoutRoutine().getWorkoutRoutineParts()
                    .stream()
                    .filter(part -> ProgressStatus.of(part.getStatus()) == ProgressStatus.대기)
                    .sorted(Comparator.comparing(WorkoutRoutinePart::getOrder))
                    .findFirst()
                    .ifPresent(part -> {
                        part.start();
                        repository.save(part);
                    });

        }
    }
}
