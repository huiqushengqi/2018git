package cn.netinnet.nna.config.dbconfig;

import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.PriorityOrdered;

/**
 * FileName: ${file_name}
 *
 * @version ${version}
 * @Description: 在service层设置切面切换读写数据源
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
// @Aspect
// @EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
// @Component
public class DataSourceAopInService implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);

    @Before("execution(* cn.netinnet.nna.service..*.*(..)) "
            + " and @annotation(cn.netinnet.nna.annotation.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setRead();
        }

    }

    @Before("execution(* cn.netinnet.nna.service..*.*(..)) "
            + " and @annotation(cn.netinnet.nna.annotation.WriteDataSource) ")
    public void setWriteDataSourceType() {

        DataSourceContextHolder.setWrite();
    }

    @Override
    public int getOrder() {
        /**
         * 值越小，越优先执行
         * 要优于事务的执行
         * 在启动类中加上了@EnableTransactionManagement(order = 10)
         */
        return 1;
    }
}
