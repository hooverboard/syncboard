package com.syncboard.syncboard_api.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false)
    UUID ownerId;

    @Column(nullable = false, columnDefinition = "TEXT")
    String state = "{\"version\":1,\"elements\":[]}";

    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt = LocalDateTime.now();

}
