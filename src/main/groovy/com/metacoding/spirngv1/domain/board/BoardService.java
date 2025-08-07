package com.metacoding.spirngv1.domain.board;

import com.metacoding.spirngv1.DTO.BoardDetailResponseDTO;
import com.metacoding.spirngv1.DTO.BoardResponseDTO;
import com.metacoding.spirngv1.DTO.BoardSaveRequestDTO;
import com.metacoding.spirngv1.DTO.BoardUpdateRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//비지니스로직, 트랜잭션관리. 응답dto 생성
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //List
    public List<BoardResponseDTO> 게시글목록(){
        List<Board> boardList = boardRepository.findAll();

        List<BoardResponseDTO> resDT0s =  new ArrayList<>();
        for(Board board : boardList){
            BoardResponseDTO  boardResponseDTO = new BoardResponseDTO();
            boardResponseDTO.setId(board.getId());
            boardResponseDTO.setTitle(board.getTitle());
            resDT0s.add(boardResponseDTO);
        }
        return resDT0s;
    }

    //Detail
    public BoardDetailResponseDTO 게시글상세(int id){

        Board board = boardRepository.findById(id);
        //dto 전환
        BoardDetailResponseDTO resDTO = new BoardDetailResponseDTO();
        resDTO.setId(board.getId());
        resDTO.setTitle(board.getTitle());
        resDTO.setContent(board.getContent());
        return resDTO;

    }

    @Transactional
    public void 게시글쓰기(BoardSaveRequestDTO boardSaveRequestDTO){
        //독립적인게 좋다. 그래서 꺼내서 뿌린다. 재사용성을 위해서
        boardRepository.save(boardSaveRequestDTO.getTitle(),boardSaveRequestDTO.getContent(),1);
    }

    @Transactional
    public void 게시글삭제(int id){
    boardRepository.deleteById(id);

    }
    @Transactional
    public void 게시글수정(int id, BoardUpdateRequestDTO boardUpdateRequestDTO){
    boardRepository.updateById(id,boardUpdateRequestDTO.getTitle(),boardUpdateRequestDTO.getContent());
    }
}
