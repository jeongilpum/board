package org.example.service;

import org.example.dto.CommentDto;
import org.example.model.Board;
import org.example.model.Comment;
import org.example.model.User;
import org.example.repository.BoardRepository;
import org.example.repository.CommentRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository,UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;

    }

    public Comment addCommentToBoard(Long boardId, Comment comment, @AuthenticationPrincipal org.springframework.security.core.userdetails.User currentUser) {

        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found with id " + boardId));
        comment.setBoard(board);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<CommentDto> findAllCommentsByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findAllByBoardId(boardId);
        return comments.stream()
                .map(CommentDto::new) // Comment 엔티티를 CommentDto로 변환
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId, Authentication authentication) {
        String currentUsername = authentication.getName(); // 현재 인증된 사용자의 이름을 가져옵니다.
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 사용자 이름 비교
        if (!comment.getUser().getName().equals(currentUsername)) {
            throw new RuntimeException("Not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }



    @Transactional
    public Comment addReplyToComment(Long boardId, Long parentId, Long userId, Comment reply) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with id " + boardId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));
        Comment parentComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + parentId));

        reply.setBoard(board);
        reply.setUser(user);
        reply.setParentComment(parentComment);

        return commentRepository.save(reply);
    }

    public Comment updateComment(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(updatedComment.getContent());
        return commentRepository.save(comment);
    }


}
