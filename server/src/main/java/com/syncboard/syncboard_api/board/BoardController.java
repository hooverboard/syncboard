package com.syncboard.syncboard_api.board;

import com.syncboard.syncboard_api.board.dto.BoardSummaryResponse;
import com.syncboard.syncboard_api.board.dto.CreateBoardRequest;
import com.syncboard.syncboard_api.board.dto.CreateBoardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@RequestBody CreateBoardRequest request) {
        CreateBoardResponse res = boardService.createBoard(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<List<BoardSummaryResponse>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }
}
