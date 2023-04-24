package com.swm47.swminder.Board.controller;

import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Board.service.BoardService;
import com.swm47.swminder.Comment.entity.CommentDTO;
import com.swm47.swminder.Comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("id") Long id) {
        BoardDTO boardDTO = boardService.getBoard(id);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<BoardDTO>> getBoards() {
        List<BoardDTO> boardDTOList = boardService.getBoardList();
        return new ResponseEntity<>(boardDTOList, HttpStatus.OK);
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveBoard() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/{id}/save")
    public ResponseEntity<String> saveComment() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveBoard(@RequestBody BoardDTO boardDTO) {
        Long id = boardService.saveBoard(boardDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<Long> saveComment(@PathVariable("id") Long id,
                                            @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.saveComment(id, commentDTO),
                HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") Long id,
                                         @RequestBody BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @PatchMapping("/{boardId}/edit/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long id,
                                                @RequestBody CommentDTO commentDTO) {
        commentService.updateComment(id, commentDTO);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("boardId") Long boardId,
                                                @PathVariable("id") Long id) {
        commentService.deleteComment(boardId, id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
