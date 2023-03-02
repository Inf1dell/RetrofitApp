package apk.karmak.retrofitapp;

public class DataModel {

    private String message;
    private String token;
    private String errors;

    public DataModel(String message,
                     String token,
                     String errors) {
        this.message = message;
        this.token = token;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
