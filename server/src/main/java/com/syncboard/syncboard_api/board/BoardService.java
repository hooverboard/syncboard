package com.syncboard.syncboard_api.board;

import com.syncboard.syncboard_api.board.dto.GetBoardByIdResponse;
import com.syncboard.syncboard_api.board.dto.GetBoardStateResponse;
import com.syncboard.syncboard_api.board.dto.GetBoardsResponse;
import com.syncboard.syncboard_api.board.dto.CreateBoardResponse;
import com.syncboard.syncboard_api.user.User;
import com.syncboard.syncboard_api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    public CreateBoardResponse createBoard(String name) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Board newBoard = new Board();
        newBoard.setName(name);

        newBoard.setOwnerId(user.getId());
        Board savedBoard = boardRepository.save(newBoard);

        CreateBoardResponse res = new CreateBoardResponse();
        res.setId(savedBoard.getId());
        res.setName(savedBoard.getName());
        res.setOwnerId(savedBoard.getOwnerId());

        return res;
    }

    public List<GetBoardsResponse> getAllBoards() {

        return boardRepository.findAll()
                .stream()
                .map(board -> new GetBoardsResponse(
                        board.getId(),
                        board.getName(),
                        board.getOwnerId()
                ))
                .collect(Collectors.toList());
    }

    public GetBoardByIdResponse getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        GetBoardByIdResponse res = new GetBoardByIdResponse();
        res.setId(board.getId());
        res.setName(board.getName());
        res.setOwnerId(board.getOwnerId());
        return res;
    }

    public GetBoardStateResponse getBoardState(Long id) {

    }
}
