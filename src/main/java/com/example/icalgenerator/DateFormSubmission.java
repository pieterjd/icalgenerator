package com.example.icalgenerator;

import java.time.LocalTime;

public class DateFormSubmission {
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private String dates;
    private LocalTime time = LocalTime.NOON;

    private String description;

    private int duration = 60;
}
