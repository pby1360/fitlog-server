package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.type.ProgressStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record RoutineInfo (
        Long routineId,
        String programName,
        String programStartedAt,
        String programDescription,
        ProgressStatus statusName,
        String status,
        Long routinePartId,
        String partName,
        Long routineItemId,
        String itemName,
        ProgressStatus itemStatusName,
        String itemStatus
){









}
