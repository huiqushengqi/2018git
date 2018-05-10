package cn.netinnet.nna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * FileName: RedisSessionConfig
 *
 * @version v1.0
 * @Description: 开启spring session支持，实现session共享，结合redis用于集群部署
 * @author: chen.wb
 * @date: 2017.8.9
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 3600)
public class RedisSessionConfig {
}
