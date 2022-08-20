package com.prisma.assessment.snakesandladders.repository;

import com.prisma.assessment.snakesandladders.entity.Snake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnakeRepository extends JpaRepository<Snake,Long> {
}
