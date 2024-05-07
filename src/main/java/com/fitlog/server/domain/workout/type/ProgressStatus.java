package com.fitlog.server.domain.workout.type;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProgressStatus {

    대기("10"),
    진행중("20"),
    중지("30"),
    완료("40");

    private String code;

    ProgressStatus(String code) {
        this.code = code;
    }

    private static final Map<String, String> value = Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(ProgressStatus::getCode, ProgressStatus::name)));

    public static ProgressStatus of (String code) {
        return ProgressStatus.valueOf(value.get(code));
    }

    public String getCode () {
        return code;
    }

}
