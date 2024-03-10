package org.example.controller;

import org.example.dto.CommentDto;
import org.example.model.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addCommentToBoard(@PathVariable Long boardId, @Valid @RequestBody Comment comment, @AuthenticationPrincipal User currentUser) {

        Comment addedComment = commentService.addCommentToBoard(boardId, comment,currentUser);
        return ResponseEntity.ok(addedComment);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllCommentsByBoardId(@PathVariable Long boardId) {
        List<CommentDto> commentDtos = commentService.findAllCommentsByBoardId(boardId);
        return ResponseEntity.ok(commentDtos);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/reply/{parentId}")
    public ResponseEntity<Comment> addReplyToComment(@PathVariable Long userId,@PathVariable Long boardId, @PathVariable Long parentId, @Valid @RequestBody Comment reply) {
        Comment addedReply = commentService.addReplyToComment(userId,boardId, parentId, reply);
        return ResponseEntity.ok(addedReply);
    }




}
