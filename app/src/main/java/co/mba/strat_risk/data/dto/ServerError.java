package co.mba.strat_risk.data.dto;

import com.google.gson.annotations.SerializedName;

public class ServerError {

    @SerializedName("class")
    private String className;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Object data;

    @SerializedName("status")
    private int status;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
