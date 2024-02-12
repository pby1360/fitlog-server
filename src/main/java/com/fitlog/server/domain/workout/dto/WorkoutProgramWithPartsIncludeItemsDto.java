package com.fitlog.server.domain.workout.dto;

import java.util.List;

public record WorkoutProgramWithPartsIncludeItemsDto(Long id, Long userId, String name, String description, String createdDate, String modifiedDate, String memo, List<WorkoutProgramPartWithItemsDto> workoutProgramPartList) {
    public static WorkoutProgramWithPartsIncludeItemsDto of (WorkoutProgramDto programDto, List<WorkoutProgramPartWithItemsDto> partDtoList) {
        return new WorkoutProgramWithPartsIncludeItemsDto(programDto.id(), programDto.userId(), programDto.name(), programDto.description(), programDto.createdDate(), programDto.modifiedDate(), programDto.memo(), partDtoList);
    }
}
