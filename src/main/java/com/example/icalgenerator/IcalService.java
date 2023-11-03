package com.example.icalgenerator;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IcalService {
    private final UidGenerator ug = new RandomUidGenerator();
    public List<LocalDate> getLocalDates(String dates){
        return Arrays.stream(dates.split(","))
                .map(d->LocalDate.parse(d))
                .collect(Collectors.toList());
    }

    public Calendar generate(DateFormSubmission s) {
        Calendar cal = new Calendar().withDefaults()
                .withProdId("prodId").getFluentTarget();
        List<LocalDate> localDates = getLocalDates(s.getDates());
        localDates.stream()
                .map(d -> {
                    LocalDateTime ldt = LocalDateTime.of(d, s.getTime());
                    VEvent event = new VEvent(ldt, Duration.ofHours(1), s.getDescription());
                    event.add(ug.generateUid());
                    event.add(new VAlarm(Duration.ofMinutes(-30)));
                    return event;
                })
                .forEach(event -> cal.add(event));
        return cal;
    }

}
