package me.joseph.web.app.controller;

import junit.framework.TestCase;
import me.joseph.MainApplication;
import me.joseph.common.util.LogUtils;
import me.joseph.web.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserRestControllerTest extends TestCase {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void createUserTest() throws Exception{

        int count = userService.getList().size();
        MvcResult result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\" : \"pass1\",\"id\" : \"333333\"}")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals(count+1, userService.getList().size());

        LogUtils.debugLog.info("prev : {}, now : {}", count, userService.getList().size());
    }

    @Test
    public void createUserWithErrorTest() throws Exception{


        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\" : \"pass1\",\"id\" : \"333333\"}")).andReturn();
        int count = userService.getList().size();
        MvcResult result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"password\" : \"pass1\",\"id\" : \"333333\"}")).andReturn();
        assertEquals(409, result.getResponse().getStatus());
        assertEquals(count, userService.getList().size());
    }

    @Test
    public void listUsersTest() throws Exception{
        int count = userService.getList().size();
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(count)));
        LogUtils.debugLog.info("user list count is {}", count);

    }

}