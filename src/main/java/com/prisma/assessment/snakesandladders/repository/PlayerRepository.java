package com.prisma.assessment.snakesandladders.repository;

import com.prisma.assessment.snakesandladders.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
