package com.fitlog.server.domain.workout.entity;

import com.fitlog.server.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WorkoutPartItem extends BaseEntity {

    @Id
    private Long id;
    private Long workoutPartId;
    private String name;
    private String description;

}
