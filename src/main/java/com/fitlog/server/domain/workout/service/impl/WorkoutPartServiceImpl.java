package com.fitlog.server.domain.workout.service.impl;

import com.fitlog.server.domain.workout.dto.WorkoutPartDto;
import com.fitlog.server.domain.workout.dto.WorkoutPartItemDto;
import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import com.fitlog.server.domain.workout.repository.WorkoutPartRepository;
import com.fitlog.server.domain.workout.service.WorkoutPartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WorkoutPartServiceImpl implements WorkoutPartService {

    WorkoutPartRepository partRepository;

    public WorkoutPartServiceImpl(WorkoutPartRepository partRepository) {
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
    public void modifyWorkoutPart(WorkoutPartDto workoutPartDto) {
        WorkoutPart workoutPart = partRepository.findById(workoutPartDto.getId()).orElseThrow();
        workoutPart.modify(workoutPartDto);
        partRepository.save(workoutPart);
    }

    @Override
    public void deleteWorkoutPart(Long workoutPartId) {
        partRepository.deleteById(workoutPartId);
    }

    @Override
    public WorkoutPartDto getWorkoutPartWithItems(Long workoutPartId) {
        WorkoutPart workoutPart = partRepository.findById(workoutPartId).orElseThrow();
        WorkoutPartDto dto = WorkoutPartDto.toDto(workoutPart);
        dto.setItems(workoutPart.getWorkoutPartItems().stream().map(item -> WorkoutPartItemDto.toDto(item)).collect(Collectors.toList()));
        return dto;
    }
}
