package org.example.service;

import org.example.model.Board;
import org.example.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }


    public Board updateBoard(Long id, Board board) {
        return boardRepository.findById(id)
                .map(existingBoard -> {
                    existingBoard.setTitle(board.getTitle());
                    existingBoard.setContent(board.getContent());
                    return boardRepository.save(existingBoard);
                }).orElseThrow(() -> new RuntimeException("Board not found with id " + id));
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
