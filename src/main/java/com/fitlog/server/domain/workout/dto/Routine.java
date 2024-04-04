package com.fitlog.server.domain.workout.dto;

public record Routine(
        RoutineInfo routineInfo,
        RoutineCounter counter,
        RoutineTimer timer,
        RoutineChart chart
)
{
}
