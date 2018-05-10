package cn.netinnet.nna.service;

import cn.netinnet.nna.annotation.ReadDataSource;
import cn.netinnet.nna.annotation.WriteDataSource;
import cn.netinnet.nna.dao.UserMapper;
import cn.netinnet.nna.domain.User;
import cn.netinnet.nna.util.SpringContextUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @ClassName:    UserService
 * @Description:  user服务实现层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/

/**
 * 如果需要事务，自行在方法上添加@Transactional
 * 如果方法有内部有数据库操作，则必须指定@WriteDataSource还是@ReadDataSource
 *
 * 注：AOP，内部方法之间互相调用时，如果是this.xxx()这形式，不会触发AOP拦截，可能会
 * 导致无法决定数据库是走写库还是读库
 * 方法：为了触发AOP的拦截，调用内部方法时，需要特殊处理下，看方法getService()
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public int add(User user) {
        return this.userMapper.add(user);
    }

    public int deleteByIds(String[] ids) {
        return this.userMapper.deleteByIds(ids);
    }

    public int update(User user) {
        return this.userMapper.update(user);
    }

    public User queryUserByObject(User user) {
        return this.userMapper.queryUserByObject(user);
    }

    public User queryUserById(int id) {
        return this.userMapper.queryUserById(id);
    }

    public List<User> queryUserList() {
        return this.userMapper.queryUserList();
    }

    @Transactional
    public void testTransaction(User user) {
        System.out.println(this.userMapper.add(user));
        int temp = 1/0;
        // throw new UserException("测试事务");
    }

    private UserService getService(){
        return SpringContextUtil.getBean(this.getClass());
    }

    /**
     * 写事务里面调用读
     * @param u
     */
    public void wirteAndRead(User u){
        getService().insert(u);//这里走写库，那后面的读也都要走写库
        //这是刚刚插入的
        User uu = getService().findById(u.getId());
        System.out.println("==读写混合测试中的读(刚刚插入的)====id="+u.getId()+",  user_name=" + uu.getUsername());
        //为了测试,3个库中id=1的username是不一样的
        User uuu = getService().findById("1");
        System.out.println("==读写混合测试中的读====id=1,user_name=" + uuu.getUsername());

    }

    /**
     * 先读操作后写入
     * @param u
     */
    public void readAndWirte(User u){
        //为了测试,3个库中id=1的username是不一样的
        User uu = getService(). findById("1");
        System.out.println("==读写混合测试中的读====id=1,user_name=" + uu.getUsername());
        getService().insert(u);
    }

    @WriteDataSource
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly=false)
    public void insert(User u){
        this.userMapper.insert(u);

        // 如果类上面没有@Transactional,方法上也没有，哪怕throw new RuntimeException,数据库也会成功插入数据
        // throw new RuntimeException("测试插入事务");
    }

    @ReadDataSource
    public User findById(String id){
        User u = this.userMapper.findById(id);
        return u;
    }

    @ReadDataSource
    public PageInfo<User> queryPage(String userName, int pageNum, int pageSize){
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        //PageHelper会自动拦截到下面这查询sql
        this.userMapper.query(userName);
        return page.toPageInfo();
    }
}
