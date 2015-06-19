package me.joseph.web.app.controller;

import junit.framework.TestCase;
import me.joseph.MainApplication;
import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.entity.User;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserCreateControllerTest extends TestCase {

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
    public void getCreateUserPageTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/user_create.html")).andReturn();
        LogUtils.debugLog.info("GET /user_create.html : " + result.getResponse().getContentAsString());
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void postCreateUserPageTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/user_create.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1111")
                .param("password1", "p1")
                .param("password2", "p1")).andReturn();
        LogUtils.debugLog.info("POST /user_create.html : " + result.getResponse().getContentAsString());
        List<User> users = userService.getList();
        assertEquals(5, users.size());
    }

    @Test
    public void postCreateUserPageButNoChangeTest() throws Exception {

        mockMvc.perform(post("/user_create.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1111")
                .param("password1", "p1")
                .param("password2", "p1")).andReturn();

        MvcResult result = mockMvc.perform(post("/user_create.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1111")
                .param("password1", "p1")
                .param("password2", "p1")).andReturn();

//                .content("{\"password\" : \"pass1\",\"id\" : \"333333\"}")).andReturn();
        LogUtils.debugLog.info("POST /user_create.html : " + result.getResponse().getContentAsString());
        List<User> users = userService.getList();
        assertEquals(5, users.size());
    }

}