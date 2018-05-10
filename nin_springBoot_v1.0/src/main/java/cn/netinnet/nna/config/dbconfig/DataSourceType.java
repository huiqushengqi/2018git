package cn.netinnet.nna.config.dbconfig;

/**
 * FileName: ${file_name}
 *
 * @version ${version}
 * @Description: 数据源类型
 * @author: ${user}
 * @date: ${date} ${time}
 * <p>
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * ${date}      ${user}         1.0             1.0
 * Why & What is modified: <修改原因描述>
 */
public enum DataSourceType {

    read("read", "从库"),
    write("write", "主库");

    private String type;

    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
