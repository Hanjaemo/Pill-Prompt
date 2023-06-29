package capstone.pillprompt.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private int status;
    private String message;
    private String errorCode;

    public CustomException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.toString();
    }
}
