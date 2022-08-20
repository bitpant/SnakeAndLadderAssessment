package com.prisma.assessment.snakesandladders.repository;

import com.prisma.assessment.snakesandladders.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
