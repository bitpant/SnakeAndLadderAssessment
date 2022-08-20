package com.prisma.assessment.snakesandladders.controller;

import com.prisma.assessment.snakesandladders.dto.PlayerPositionDto;
import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.service.SnakeAndLadderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SnakeAndLadderService snakeAndLadderService;


    @Test
    public void getPlayerByIdTest() throws Exception {
        PlayerPositionDto positionDto=new PlayerPositionDto();
        positionDto.setPosition(10);
        positionDto.setPlayerId(1);
        positionDto.setGameComplete(false);
        positionDto.setMoveDetail("Brijesh Moved to 10");
        Mockito.when(snakeAndLadderService.rollDiceAndPlayMove(Mockito.any(Long.class))).thenReturn(positionDto);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/game/rollandplay?id=1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(HttpStatus.OK.value(), status);
        Mockito.verify(snakeAndLadderService).rollDiceAndPlayMove(Mockito.any(Long.class));
        String resultPlayer = result.getResponse().getContentAsString();
        Assert.assertNotNull(resultPlayer);
        Assert.assertTrue(resultPlayer.contains("Brijesh"));
    }
}
