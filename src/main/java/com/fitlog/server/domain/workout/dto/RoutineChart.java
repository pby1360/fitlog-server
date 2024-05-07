package com.fitlog.server.domain.workout.dto;

public record RoutineChart(
        int partCount
        , int totalPartCount
        , int itemCount
        , int totalItemCount
        , int setCount
        , int totalSetCount
) {
}
