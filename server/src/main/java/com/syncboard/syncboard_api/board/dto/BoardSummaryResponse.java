package com.syncboard.syncboard_api.board.dto;

import java.util.UUID;

public record BoardSummaryResponse(
        Long id,
        String name,
        UUID ownerId
) {}
