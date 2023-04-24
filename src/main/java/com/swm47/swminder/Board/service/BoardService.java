package com.swm47.swminder.Board.service;

import com.swm47.swminder.Board.BoardRepository;
import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Member.entity.Member;
import com.swm47.swminder.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveBoard(BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Member> result = memberRepository.findByLoginId(username);
        Member member = result.get();
        Board savedBoard = boardRepository.save(boardDTO.ToEntity());
        savedBoard.addMember(member);
        return savedBoard.getBoardId();
    }
    public List<BoardDTO> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDTO> boardDTOs = new ArrayList<>();
        boards.stream().forEach(board -> {
            BoardDTO boardDTO = BoardDTO.builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .author(board.getAuthor())
                    .createdDate(board.getCreatedDate())
                    .build();

            boardDTOs.add(boardDTO);
        });

        return boardDTOs;
    }
    public BoardDTO getBoard(Long id) {
        Optional<Board> res = boardRepository.findById(id);
        Board board = res.get();

        return BoardDTO.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .createdDate(board.getCreatedDate())
                .build();
    }
    @Transactional
    public void deleteBoard(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Member> result = memberRepository.findByLoginId(username);
        Member member = result.get();

        Optional<Board> res = boardRepository.findById(id);
        Board board = res.get();

        member.deleteBoard(board);

        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, BoardDTO boardDTO) {
        Optional<Board> res = boardRepository.findById(id);
        Board board = res.get();
        board.updateBoard(boardDTO.getTitle(), board.getContent());
    }
}
