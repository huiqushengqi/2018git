package cn.netinnet.nna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * FileName: ${file_name}
 *
 * @version ${version}
 * @Description: 配置支持thymleaf和jsp模板
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
@Configuration
@ImportResource(locations={"classpath:application-bean.xml"})
public class ViewConfiguration {

}
