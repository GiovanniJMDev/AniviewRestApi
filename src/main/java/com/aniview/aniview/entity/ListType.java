package com.aniview.aniview.entity;

public enum ListType {
    PLAN_TO_WATCH("plan-to-watch"),
    WATCHING("watching"),
    DROPPED("dropped"),
    COMPLETED("completed");

    private final String value;

    ListType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 