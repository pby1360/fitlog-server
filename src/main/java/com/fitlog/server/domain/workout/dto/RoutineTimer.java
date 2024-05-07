package com.fitlog.server.domain.workout.dto;

import java.time.LocalDateTime;

public record RoutineTimer (
        LocalDateTime programStartedAt
        , LocalDateTime itemStartedAt
        , int restTime
)
{
}
