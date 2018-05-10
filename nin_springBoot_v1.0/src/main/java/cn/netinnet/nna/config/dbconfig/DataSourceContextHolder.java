package cn.netinnet.nna.config.dbconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileName: ${file_name}
 *
 * @version ${version}
 * @Description: 本地线程
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
public class DataSourceContextHolder {

    private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    //线程本地环境
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读库
     */
    public static void setRead() {
        local.set(DataSourceType.read.getType());
        log.info("数据库切换到读库...");
    }

    /**
     * 写库
     */
    public static void setWrite() {
        local.set(DataSourceType.write.getType());
        log.info("数据库切换到写库...");
    }

    public static String getReadOrWrite() {
        return local.get();
    }

    public static void clear(){
        local.remove();
    }
}
