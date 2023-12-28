package com.fitlog.server.domain.workout.dto;

import java.util.List;

public record WorkoutProgramWithPartsDto(Long id, Long userId, String name, String description, String createdDate, String modifiedDate, String memo, List<WorkoutProgramPartDto> workoutProgramPartList) {
    public static WorkoutProgramWithPartsDto of (WorkoutProgramDto programDto, List<WorkoutProgramPartDto> partDtoList) {
        return new WorkoutProgramWithPartsDto(programDto.id(), programDto.userId(), programDto.name(), programDto.description(), programDto.createdDate(), programDto.modifiedDate(), programDto.memo(), partDtoList);
    }
}
