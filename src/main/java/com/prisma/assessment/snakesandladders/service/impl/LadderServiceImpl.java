package com.prisma.assessment.snakesandladders.service.impl;

import com.prisma.assessment.snakesandladders.entity.Ladder;
import com.prisma.assessment.snakesandladders.repository.LadderRepository;
import com.prisma.assessment.snakesandladders.service.LadderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LadderServiceImpl implements LadderService {

    @Autowired
    private LadderRepository ladderRepository;

    @Override
    public List<Ladder> getAllLadders() {
        return ladderRepository.findAll();
    }
}
