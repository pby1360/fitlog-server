package com.fitlog.server.domain.workout.service.impl;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import com.fitlog.server.domain.workout.repository.WorkoutPartRepository;
import com.fitlog.server.domain.workout.service.WorkoutMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkoutMasterServiceImpl implements WorkoutMasterService {

    WorkoutPartRepository partRepository;

    public WorkoutMasterServiceImpl(WorkoutPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public void addWorkoutPart(WorkoutPartDto workoutPartDto) {
        WorkoutPart workoutPart = workoutPartDto.toEntity();
        partRepository.save(workoutPart);
    }

    @Override
    public List<WorkoutPartDto> getWorkoutPartList(Long userId) {
        return partRepository.findAll().stream().map(entity -> WorkoutPartDto.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public WorkoutPartDto getWorkoutPart(Long partId) {
        return partRepository.findById(partId).map(entity -> WorkoutPartDto.toDto(entity)).orElseThrow();
    }

    @Override
    public List<WorkoutPartItem> getWorkoutPartItemList(Long partId) {
        return null;
    }
}
