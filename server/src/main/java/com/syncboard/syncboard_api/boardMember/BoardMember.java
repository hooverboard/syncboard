package com.syncboard.syncboard_api.boardMember;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardMember {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long boardId;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false, length = 50)
    String role;

    @Column(nullable = false, updatable = false)
    LocalDateTime joinedAt = LocalDateTime.now();
}
