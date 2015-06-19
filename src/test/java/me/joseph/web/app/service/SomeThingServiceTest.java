package me.joseph.web.app.service;

import junit.framework.TestCase;
import me.joseph.MainApplication;
import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.entity.SomeThing;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class SomeThingServiceTest extends TestCase {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SomeThingService someThingService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testSave() throws Exception {
        SomeThing s = new SomeThing("test");
        someThingService.save(s);

        LogUtils.debugLog.info("SomeThing id {}", s.getId());

        assertThat(s.getId(), is(greaterThan(0L)));

        SomeThing ns = someThingService.find(s.getId());

        assertEquals(s, ns);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            SomeThing s = new SomeThing("test_" + i);
            someThingService.save(s);
        }

        List<SomeThing> list1 = someThingService.findAll();
        assertThat(list1.size(), is(greaterThanOrEqualTo(10)));

        LogUtils.debugLog.info("size of list<something> {}", list1.size());
    }

    @Test
    public void testMyFindAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            SomeThing s = new SomeThing(("test1_" + i));
            someThingService.save(s);
        }

        Page<SomeThing> p = someThingService.myFindAll(new PageRequest(0, 1));

        LogUtils.debugLog.info("total elem {}, total page {} content size {}", p.getTotalElements(), p.getTotalPages(), p.getContent().size());
        assertThat(p.getTotalElements(), is(greaterThanOrEqualTo(10L)));
        assertThat(p.getTotalPages(), is(greaterThanOrEqualTo(10)));
        assertThat(p.getContent().size(), is(equalTo(1)));

    }
}