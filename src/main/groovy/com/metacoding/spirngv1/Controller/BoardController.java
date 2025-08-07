package com.metacoding.spirngv1.Controller;

import com.metacoding.spirngv1.DTO.BoardDetailResponseDTO;
import com.metacoding.spirngv1.DTO.BoardResponseDTO;
import com.metacoding.spirngv1.DTO.BoardSaveRequestDTO;
import com.metacoding.spirngv1.DTO.BoardUpdateRequestDTO;
import com.metacoding.spirngv1.domain.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller //DS가 endPonit로 찾을 수 있고 ,파일을 찾아서 응답
public class BoardController {

    private final BoardService BoardService;
    private final BoardService boardService;

    //localhost/board/1 PathValue
    //localhost/board/title = 쿼리 스트링
    @PostMapping("/board/save")
    public String save(BoardSaveRequestDTO reqDTO){
        BoardService.게시글쓰기(reqDTO);
        return "redirect:/board";
    }

    @PostMapping("/board/{id}/delete")
    public String DeleteById(@PathVariable("id") int id){
        BoardService.게시글삭제(id);
        System.out.println("삭제 id 값" + id);
        return "redirect:/board";
    }
    @PostMapping("/board/{id}/update")
    public String update(BoardUpdateRequestDTO reqDTO, @PathVariable("id") int id ){

        //조회하기
        BoardService.게시글수정(id, reqDTO);

        return "redirect:/board/"+id;
    }

    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<BoardResponseDTO> reDT0s =BoardService.게시글목록();
        request.setAttribute("models", reDT0s);

        return "board/list"; //확장자 생략가능  text/html
    }


    @GetMapping("/board/save-form")
    public String saveForm(){
        return "board/save-form"; //확장자 생략가능
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id , HttpServletRequest request){
        BoardDetailResponseDTO resDTO = boardService.게시글상세(id);
        request.setAttribute("model",resDTO);
        return "board/update-form"; //확장자 생략가능
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id , HttpServletRequest request){
        BoardDetailResponseDTO resDTO = boardService.게시글상세(id);

        request.setAttribute("model",resDTO);
        return "board/detail";
    }

}