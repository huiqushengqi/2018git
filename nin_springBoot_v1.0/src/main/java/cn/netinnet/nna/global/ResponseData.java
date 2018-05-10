package cn.netinnet.nna.global;

/**
 * FileName: ResponseData
 * Description: 统一ajax请求返回的数据格式
 *
 *  status 响应状态 true/false
 *  code 状态码 @see HttpStatus.java in spring-web-4.3.9.REALEASE.jar
 *  message 返回的信息
 *  data 返回的json数据
 */
public class ResponseData<T> {

    private boolean status;
    private Integer code;
    private String message;
    private T data;

    public ResponseData() {}

    public ResponseData(boolean status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}