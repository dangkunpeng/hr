package com.magpie.devOps.ops;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.magpie.devOps.task.AsyncMail;
import com.magpie.devOps.utils.PageUtils;
import com.magpie.devOps.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MagpieServiceImpl implements MagpieService {
    // 日期格式
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd hhmm");
    // 每天的毫秒数
    public static final BigDecimal ONE_DAY = new BigDecimal(3600 * 1000 * 24);
    private static final String[] MAILS = {"dangkp@icloud.com", "dangkp@yeah.net", "tianyang68811@126.com", "ada.lin.zhu@outlook.com"};
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MagpieRepo magpieRepo;
    @Value("${spring.mail.username}")
    private String user;
    @Autowired
    private AsyncMail asyncMail;

    @Override
    public void update(Magpie magpie) {
        this.magpieRepo.save(magpie);
        log.info("保存记录后发邮件++++++++开始");
        this.asyncMail.sendMail();
        log.info("保存记录后发邮件++++++++结束");

    }

    @Override
    public String queryByDay(MagpieForm param) {
        // 查询条件
        Magpie magpie = new Magpie();
        BeanUtils.copyProperties(param, magpie);
        Example<Magpie> example = Example.of(magpie);
        // 排序
        Sort sort = Sort.by(Sort.Order.desc("uuid"), Sort.Order.desc("day"));
        // 分页
        Pageable pageable = PageUtils.newPageQuery(param, sort);
        // 查询
        Page<Magpie> page = this.magpieRepo.findAll(example, pageable);

        return PageUtils.newPage(page);
    }

    @Override
    public String getDescs() {

        List<Magpie> list = this.magpieRepo.findAll();
        Collections.sort(list);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Magpie magpie = list.get(i);
            result.append("<hr>[");
            result.append(magpie.getDay());
            result.append("]");
            result.append("[");
            result.append(i + 1);
            result.append("/");
            result.append(list.size());
            result.append("]");
            result.append(magpie.getDesc());
        }
        return result.toString();
    }

    @Override
    public void sendMail() {

        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

            StringBuilder sb = new StringBuilder();
            sb.append("<h3 style='color:green'>成长记录</h3>");
            sb.append("<h6 style='color:red'>" + getMsg() + "</h6>");
//			log.info("邮件文本内容是{}", sb.toString());
            for (String receiver : MAILS) {
                log.info("正在发邮件给{}", receiver);
                mimeMessageHelper.setFrom(user);
                mimeMessageHelper.setTo(receiver);
                mimeMessageHelper.setSubject("大喜鹊近期情况通报");

                mimeMessageHelper.setText(sb.toString(), true);
                javaMailSender.send(mimeMailMessage);
            }


        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
        }
    }

    private String getMsg() {
        try {
            // 生日
            Date birth = SDF.parse("20190616 0951");
            // 当前时间
            Date now = new Date();
            // 出生毫秒
            Long last = now.getTime() - birth.getTime();
            // 换算成天
            BigDecimal days = new BigDecimal(last).divide(ONE_DAY, 1, RoundingMode.HALF_UP);

            StringBuilder msg = new StringBuilder();
            msg.append("党家杰已出生");
            msg.append(days);
            msg.append("天</br>");
            if (StringUtils.endsWith(Utils.getDay(), "16")) {
                msg.append("</br>");
                msg.append("又过了一个月了");
                msg.append("</br><hr>");
            }
            msg.append(this.getDescs());
            return msg.toString();
        } catch (ParseException e) {
            log.info("发信失败{}", e);
            return "";
        }
    }
}
