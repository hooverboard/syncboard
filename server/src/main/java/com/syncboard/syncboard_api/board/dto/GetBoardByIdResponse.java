package com.syncboard.syncboard_api.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardByIdResponse {
    private Long id;
    private String name;
    private UUID ownerId;
}
