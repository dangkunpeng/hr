package com.magpie.devOps.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class Task {

	@Autowired
	private AsyncMail asyncMail;

	@Scheduled(cron = "0 0/30 * * * ?")
	private void run() {
		log.info("sending mail");
		this.asyncMail.sendMail();
		log.info("sended mail");
	}
}
