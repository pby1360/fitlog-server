package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutPart;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class WorkoutPartDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String createdDate;
    private String modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String memo;

    public WorkoutPart toEntity() {
        WorkoutPart entity = WorkoutPart.create(this);
        return entity;
    }

    public static WorkoutPartDto toDto(WorkoutPart entity) {
        WorkoutPartDto dto = new WorkoutPartDto();
        dto.id = entity.getId();
        dto.userId = entity.getUserId();
        dto.name = entity.getName();
        dto.description = entity.getDescription();
        dto.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dto.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dto.memo = entity.getMemo();
        return dto;
    }
}
