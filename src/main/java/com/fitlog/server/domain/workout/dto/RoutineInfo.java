package com.fitlog.server.domain.workout.dto;

import com.fitlog.server.domain.workout.type.ProgressStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record RoutineInfo (
        Long routineId,
        String programName,
        String programStartedAt,
        String programFinishedAt,
        String programDescription,
        String status,
        ProgressStatus statusName,
        Long routinePartId,
        String partName,
        String partStatus,
        ProgressStatus partStatusName,
        Long routineItemId,
        String itemName,
        String itemStatus,
        ProgressStatus itemStatusName
){









}
