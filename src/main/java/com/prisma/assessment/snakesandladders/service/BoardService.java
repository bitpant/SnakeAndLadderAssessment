package com.prisma.assessment.snakesandladders.service;

import com.prisma.assessment.snakesandladders.entity.Board;

import java.util.List;

public interface BoardService {
    Board save(Board board);
    List<Board> findBoard();
}
