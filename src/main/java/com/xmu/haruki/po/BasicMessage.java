package com.xmu.haruki.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BasicMessage {
    protected Integer id;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected String target;
    protected String property;
}
