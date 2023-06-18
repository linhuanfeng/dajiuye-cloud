package springbootueditor.Result;

public class Result {

    private int code;
    private String msg;
    private Object data;

    public Result() {
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "VideoResult [code=" + code + ", msg=" + msg + ", object=" + data + "]";
    }

}
