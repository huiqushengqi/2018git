package cn.netinnet.nna.dao;

import cn.netinnet.nna.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *
 * @ClassName:    UserMapper
 * @Description:  UserMapper Dao接口层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     * Mybatis分为注解方式以及XML配置方式
     */

    // 注解方式
    @Insert("insert user(id,username) values(#{id},#{username})")
    int insert(User u);

    @Select("select id,username from user where id=#{id} ")
    User findById(@Param("id")String id);

    // XML配置方式，注：方法名和要UserMapper.xml中的id一致
    int add(User user);

    int deleteByIds(String[] ids);

    int update(User user);

    User queryUserByObject(User user);

    User queryUserById(int id);

    List<User> queryUserList();

    List<User> query(@Param("userName")String userName);

}