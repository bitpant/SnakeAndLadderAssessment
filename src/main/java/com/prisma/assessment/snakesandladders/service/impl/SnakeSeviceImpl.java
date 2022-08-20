package com.prisma.assessment.snakesandladders.service.impl;

import com.prisma.assessment.snakesandladders.entity.Snake;
import com.prisma.assessment.snakesandladders.repository.SnakeRepository;
import com.prisma.assessment.snakesandladders.service.SnakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnakeSeviceImpl implements SnakeService {

    @Autowired
    private SnakeRepository snakeRepository;

    @Override
    public List<Snake> getAllSnakes() {
        return snakeRepository.findAll();
    }
}
