package com.metacoding.spirngv1.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }



    //update
    public void updateById(int id, String title, String content) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate(); //insert update delete
        //em
    }
    //insert
    public void save(String title, String content) {
        Query query = em.createNativeQuery("INSERT INTO board_tb (title, content) VALUES (?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.executeUpdate(); //insert update delete
    //error 는 1
    }


    //
    public List<Board> findAll(){
        Query query = em.createNativeQuery("select id, title ,content from board_tb order by id desc");
        List<Object[]> Oblist = query.getResultList();

        List<Board> boardList = new ArrayList<>();
        for (Object[] obs : Oblist) {
            Integer v1 =(Integer) obs[0];
            String v2 =(String) obs[1];
            String v3 =(String) obs[2];
            Board board = new Board(v1,v2,v3);
            boardList.add(board);
        }
        return boardList;
    };
    public List<Board> findAllV2(){
        Query query = em.createNativeQuery("select id, title ,content from board_tb order by id desc",Board.class);
        return query.getResultList();
    }


    //DB에서 ID를 조회해서 BOARD로 RETURN 하기
    public Board findById(int id) {
        Query query = em.createNativeQuery("select id, title ,content from board_tb where id = ?");
        query.setParameter(1, id);

        try {
            Object[] obs = (Object[]) query.getSingleResult();

            Integer v1 =(Integer) obs[0];
            String v2 =(String) obs[1];
            String v3 =(String) obs[2];

            //System.out.println("ID = " + v1 + " title = " + v2 + " content =" + v3);

            //엔티티 오브젝트 매핑
            Board board = new Board(v1,v2,v3);
            return board;
        }catch (RuntimeException ex){
            return null;
        }

    }
    public Board findByIdV2(int id) {
        //키값 동일시 - 쿼리
        Query query = em.createNativeQuery("select id, title ,content from board_tb where id = ?",Board.class);
        query.setParameter(1, id);

        try {
            return (Board) query.getSingleResult();

        }catch (RuntimeException ex){
            return null;
        }

    }




}
