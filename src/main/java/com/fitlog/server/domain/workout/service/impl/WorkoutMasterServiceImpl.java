package com.fitlog.server.domain.workout.service.impl;

import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.repository.WorkoutPartRepository;
import com.fitlog.server.domain.workout.service.WorkoutMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkoutMasterServiceImpl implements WorkoutMasterService {

    WorkoutPartRepository partRepository;

    public WorkoutMasterServiceImpl(WorkoutPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public void addWorkoutPart(WorkoutPart workoutPart) {
        log.info(workoutPart.toString());
        partRepository.save(workoutPart);
    }

    @Override
    public List<WorkoutPart> getWorkoutPartList(Long userId) {
        log.info(":: userId ? {}", userId);
        return partRepository.findAll();
    }
}
