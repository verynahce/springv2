package com.metacoding.spirngv1.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({BoardRepository.class})
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test() {
        //given
        int id = 5;
        //when
        Board board = boardRepository.findById(id);

        //eye
        if (board == null) {
            System.out.println("조회가 안됬어!! 번호가 없나봐");

        } else {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
        }


    }

    @Test
    public void findById_test2() {
        //given
        int id = 4;
        //when
        Board board = boardRepository.findByIdV2(id);

        //eye
        if (board == null) {
            System.out.println("조회가 안됬어!! 번호가 없나봐2");

        } else {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
        }

    }

    @Test
    public void findAll_test() {
        // given
        // when
        List<Board> boardList = boardRepository.findAll();
        // eye
        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println("1-----------------");
        }
    }

    @Test
    public void findAll_test2() {
        List<Board> boardList = boardRepository.findAllV2();

        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println("2----------------2");
        }

    }

    @Test
    public void save_test() {
        //given
        String title = "제목6";
        String content = "내용6";
        //when
        boardRepository.save(title, content);

        //eye
        Board findBoard = boardRepository.findById(6);
        System.out.println(findBoard.getTitle());
        System.out.println(findBoard.getContent());

    }

    @Test
    public void updateById_test() {

        //given
        int id = 5;
        String title = "제목변경5";
        String content = "내용변경5";

        Board findBoard2 = boardRepository.findById(id);
        System.out.println(findBoard2.getTitle());
        System.out.println(findBoard2.getContent());


        //when
        boardRepository.updateById(id, title, content);

        //eye
        Board findBoard = boardRepository.findById(id);
        System.out.println("----변경후-----");
        System.out.println(findBoard.getTitle());
        System.out.println(findBoard.getContent());

    }
    @Test
    public void deleteById_test() {

        //given ( 없는 ID를 넣어서 터지는지도 확인해야합니다)
        int id = 8;
        //when
        boardRepository.deleteById(id);
        //eye
        List<Board> boardList = boardRepository.findAllV2();

        System.out.println(boardList.size());

    }


}
