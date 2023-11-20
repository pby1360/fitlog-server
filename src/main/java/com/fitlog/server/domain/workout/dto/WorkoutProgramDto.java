package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutProgram;

import java.time.format.DateTimeFormatter;

public record WorkoutProgramDto(Long id, Long userId, String name, String description, String createdDate, String modifiedDate, String memo) {
    public static WorkoutProgramDto toDto (WorkoutProgram entity) {
        WorkoutProgramDto dto = new WorkoutProgramDto(entity.getId(), entity.getUserId(), entity.getName(), entity.getDescription(), entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), entity.getMemo());
        return dto;
    }
}
