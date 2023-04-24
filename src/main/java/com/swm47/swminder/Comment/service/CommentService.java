package com.swm47.swminder.Comment.service;

import com.swm47.swminder.Board.BoardRepository;
import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Comment.CommentRepository;
import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.Comment.entity.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveComment(Long boardId, CommentDTO commentDTO) {
        Comment savedComment = commentRepository.save(commentDTO.toEntity());
        Optional<Board> res = boardRepository.findById(boardId);
        Board board = res.get();
        savedComment.addBoard(board);
        return savedComment.getCommentId();
    }

    @Transactional
    public void updateComment(Long Id, CommentDTO commentDTO) {
        Optional<Comment> res = commentRepository.findById(Id);
        Comment comment = res.get();
        comment.updateComment(commentDTO.getContent());
    }

    @Transactional
    public void deleteComment(Long boardId, Long id) {
        Optional<Board> resBoard = boardRepository.findById(boardId);
        Board board = resBoard.get();
        Optional<Comment> resComment = commentRepository.findById(id);
        Comment comment = resComment.get();
        board.deleteComment(comment);

        commentRepository.deleteById(id);
    }
}
