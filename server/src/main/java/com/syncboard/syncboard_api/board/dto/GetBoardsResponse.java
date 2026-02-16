package com.syncboard.syncboard_api.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBoardsResponse {
    private Long id;
    private String name;
    private UUID ownerId;
}
