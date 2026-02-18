package com.syncboard.syncboard_api.realtime;

import com.syncboard.syncboard_api.board.BoardService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;

@Controller
public class BoardRealtimeController {

    private final SimpMessagingTemplate messagingTemplate;
    private final BoardService boardService;

    public BoardRealtimeController (SimpMessagingTemplate messagingTemplate, BoardService boardService) {
        this.messagingTemplate = messagingTemplate;
        this.boardService = boardService;
    }

    @MessageMapping("/boards.join")
    public void joinBoard(JoinBoardMessage msg, Principal principal) {
        Map<String, Object> payload = Map.of(
                "event", "USERS_JOINED",
                "boardId", msg.getBoardId(),
                "user", principal.getName()
        );

        messagingTemplate.convertAndSend(
                "/topic/boards/" + msg.getBoardId() + "/presence",
                (Object) payload
        );
    }
}
