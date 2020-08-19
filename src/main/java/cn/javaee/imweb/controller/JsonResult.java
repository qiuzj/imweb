package cn.javaee.imweb.controller;

public class JsonResult<T> {

    /** 状态码 */
    private int code;
    /** 状态信息 */
    private String message;
    /** 响应数据 */
    private T data;

    public JsonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static JsonResult success() {
        return new JsonResult(0, "成功", null);
    }

    public static JsonResult error() {
        return error(1, "服务端错误");
    }

    public static JsonResult error(String message) {
        return error(1, message);
    }

    public static JsonResult error(int code, String message) {
        return new JsonResult(code, message, null);
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
