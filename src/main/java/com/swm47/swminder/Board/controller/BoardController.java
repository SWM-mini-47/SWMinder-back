package com.swm47.swminder.Board.controller;

import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Board.service.BoardService;
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

    @GetMapping("/list/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("id") Long id) {
        BoardDTO boardDTO = boardService.getBoard(id);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<BoardDTO>> getBoards() {
        List<BoardDTO> boardList = boardService.getBoardList();
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveBoard() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveBoard(@RequestBody BoardDTO boardDTO) {
        Long id = boardService.saveBoard(boardDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") Long id,
                                         @RequestBody BoardDTO boardDTO) {
        boardService.update(id, boardDTO);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
