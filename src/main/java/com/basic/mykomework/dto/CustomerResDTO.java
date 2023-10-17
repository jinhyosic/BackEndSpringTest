package com.basic.mykomework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResDTO {
    private String id;
    private int age;
    private String email;
    private LocalDateTime entryDate;
}
