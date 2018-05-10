package cn.netinnet.nna.domain;

import java.io.Serializable;

/**
 *
 * @ClassName:    User
 * @Description:  User实体层
 *
 * @author         ${user}
 * @version        V1.0
 * @Date           ${date} ${time}
 *
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -8366929034564774130L;
    private String id;

    private String username;

    private String password;

    public User(){};

    public User(String userName, String passWord) {
        this.username = userName;
        this.password = passWord;
    }

    public User(String id, String userName, String passWord) {
        this.id = id;
        this.username = userName;
        this.password = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}