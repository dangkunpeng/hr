package com.magpie.devOps.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.magpie.devOps.ops.MagpieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncMail {

	@Autowired
	private MagpieService magpieService;
	@Async
    public void sendMail() {
        log.info("调用异步发送邮件++++++++++开始");
        this.magpieService.sendMail();
        log.info("调用异步发送邮件++++++++++完成");
    }
}
