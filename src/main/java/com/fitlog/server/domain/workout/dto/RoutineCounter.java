package com.fitlog.server.domain.workout.dto;

public record RoutineCounter(
        int currentSet
        , int targetSet
        , int currentTotalCount
        , int targetTotalCount
        , int currentCount
        , int targetCount
) {
}
