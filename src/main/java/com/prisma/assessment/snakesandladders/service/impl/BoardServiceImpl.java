package com.prisma.assessment.snakesandladders.service.impl;

import com.prisma.assessment.snakesandladders.entity.Board;
import com.prisma.assessment.snakesandladders.repository.BoardRepository;
import com.prisma.assessment.snakesandladders.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> findBoard() {
        return boardRepository.findAll();
    }
}
