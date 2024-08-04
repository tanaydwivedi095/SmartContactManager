package com.scm.SmartContactManager;

import com.scm.SmartContactManager.service.interfaces.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartContactManagerApplicationTests {

    @Autowired
    private ServerProperties serverProperties;

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	void sendEmailTest() {
		emailService.sendEmail("tanaydwivedi095@gmail.com", "Just testing email service", "This is scm project working on email service");
	}

}
