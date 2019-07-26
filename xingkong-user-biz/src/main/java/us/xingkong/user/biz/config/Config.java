package us.xingkong.user.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: Icharle
 * @Date: 2019-07-26 11:19
 */
@Configuration
@ImportResource(locations = {"classpath:config/spring/*.xml"})
public class Config {
}
