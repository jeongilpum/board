package org.example.dto;

import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    private Long id;
    private String content;
    private Long boardId;
    private Long parentId;
    private Long userId;
    private String userName;
    private List<CommentDto> childComments;


    // Comment 엔티티를 기반으로 DTO 객체를 생성
    public CommentDto(org.example.model.Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.boardId = comment.getBoard() != null ? comment.getBoard().getId() : null;      // getId가 없으면 null
        this.parentId = comment.getParentComment() != null ? comment.getParentComment().getId() : null;
        this.userId = comment.getUser() != null ? comment.getUser().getId() : null;
        this.userName = comment.getUser() != null ? comment.getUser().getName() : null;
        this.childComments = comment.getChildComments().stream()                  // 댓글의 자식 댓글들을 DTO로 변환하여 childComments 리스트에 저장
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CommentDto> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<CommentDto> childComments) {
        this.childComments = childComments;
    }
}