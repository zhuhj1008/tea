package com.joe.common;

import com.joe.api.po.Config;
import com.joe.api.service.ConfigService;
import com.joe.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化配置信息
 * create by Joe on 2018-08-07 18:20
 **/
@Component
public class InitConfig implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitConfig.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private ConfigService configService;

    @Override
    public void run(String... strings) {
        logger.info("初始化-配置信息加载缓存");
        List<Config> configList = configService.queryAll();
        for (Config config : configList) {
            redisService.putCache(config.getConfigKey(), config.getConfigValue());
        }
        logger.info("初始化-配置信息缓存完成");
    }
}
