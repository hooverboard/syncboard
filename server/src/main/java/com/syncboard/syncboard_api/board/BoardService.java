package com.syncboard.syncboard_api.board;

import com.syncboard.syncboard_api.user.User;
import com.syncboard.syncboard_api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    public void createBoard(String name) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Board newBoard = new Board();
        newBoard.setName(name);

        newBoard.setOwnerId(user.getId());
        boardRepository.save(newBoard);
    }
}
