package com.basic.mykomework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long age;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime entryDate = LocalDateTime.now();
}
