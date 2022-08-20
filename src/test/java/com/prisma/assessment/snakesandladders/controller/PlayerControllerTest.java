package com.prisma.assessment.snakesandladders.controller;

import com.prisma.assessment.snakesandladders.entity.Player;
import com.prisma.assessment.snakesandladders.service.PlayerService;
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
@WebMvcTest(value = PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    public Player getPlayerForTest(){
        Player player=new Player(1l,"Test Name",0);
        return  player;
    }

    @Test
    public void getPlayerByIdTest() throws Exception {
        Player player = getPlayerForTest();
        Mockito.when(playerService.getPlayerById(Mockito.any(Long.class))).thenReturn(player);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/player/getplayerbyid?id=1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(HttpStatus.OK.value(), status);
        Mockito.verify(playerService).getPlayerById(Mockito.any(Long.class));
        String resultPlayer = result.getResponse().getContentAsString();
        Assert.assertNotNull(resultPlayer);
        Assert.assertTrue(resultPlayer.contains("Test Name"));
    }

}
