package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.entity.WorkoutPart;
import com.fitlog.server.domain.workout.entity.WorkoutPartItem;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class WorkoutPartItemDto {
    private Long id;
    private Long workoutPartId;
    private String name;
    private String description;
    private String createdDate;
    private String modifiedDate;
    private Long createBy;
    private Long modifiedBy;
    private String memo;

    public static WorkoutPartItemDto toDto(WorkoutPartItem entity) {
        WorkoutPartItemDto dto = new WorkoutPartItemDto();
        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.description = entity.getDescription();
        dto.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dto.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dto.memo = entity.getMemo();
        return dto;
    }
}
