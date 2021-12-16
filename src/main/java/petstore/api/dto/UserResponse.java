package petstore.api.dto;

public class UserResponse {
    private Long code;
    private String type;
    private String message;

    public UserResponse() {
    }

    public UserResponse(Long code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public UserResponse setCode(Long code) {
        this.code = code;
        return this;
    }

    public String getType() {
        return type;
    }

    public UserResponse setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        UserResponse that = (UserResponse) o;
        return code.equals(that.code) &&
                type.equals(that.type) &&
                message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "UserResponce{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

