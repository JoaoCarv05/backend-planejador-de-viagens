package com.johndev.planner.activity;
import java.time.ZonedDateTime;
import java.util.List;

public record ActivityData(ZonedDateTime date, List<Activity> activities) {
}
