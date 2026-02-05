package com.syncboard.syncboard_api.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public void createBoard(String name, Long ownerId) {

        Board newBoard = new Board();
        newBoard.setName(name);
        newBoard.setOwnerId(ownerId);

        boardRepository.save(newBoard);

    }
}
