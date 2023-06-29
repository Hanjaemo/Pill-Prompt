package capstone.pillprompt.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int status;
    private final String message;
    private final String errorCode;

    public CustomException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.toString();
    }
}
