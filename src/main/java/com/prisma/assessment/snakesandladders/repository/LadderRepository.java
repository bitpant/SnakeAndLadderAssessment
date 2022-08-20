package com.prisma.assessment.snakesandladders.repository;

import com.prisma.assessment.snakesandladders.entity.Ladder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LadderRepository extends JpaRepository<Ladder,Long> {
}
