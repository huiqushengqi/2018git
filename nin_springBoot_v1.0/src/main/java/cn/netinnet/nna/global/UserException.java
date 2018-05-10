package cn.netinnet.nna.global;

/**
 * FileName: UserException
 *
 * @version ${version}
 * @Description: 自定义用户异常类，用于测试事务控制
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */

import org.springframework.dao.DataAccessException;

/**
 * 自定义异常，用于测试事务
 */
public class UserException extends DataAccessException {
    private static final long serialVersionUID = 8901479830692029025L;

    public UserException(String msg) {
        super(msg);
    }
}
