package com.staxter.demo;

import com.staxter.demo.service.RegistrationService;
import com.staxter.demo.userrepository.UserMemoryRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashMap;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        classes = DemoApplication.class)
public abstract class AbstractTest {

    @Resource
    private TestRestTemplate restTemplate;

    @Resource
    private RegistrationService registrationService;

    @Resource
    private UserMemoryRepository userMemoryRepository;

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public UserMemoryRepository getUserMemoryRepository() {
        return userMemoryRepository;
    }


    @After
    public void tearDown() {
        userMemoryRepository.setUsers(new HashMap<>());
    }
}
