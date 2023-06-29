package capstone.pillprompt.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonPropertyOrder({"httpStatus", "errorCode", "message"})
public class ErrorResponse {

    private int status;
    private String message;
    private String errorCode;

    public ErrorResponse(CustomException e) {
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.errorCode = e.getErrorCode();
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.message = message;
        this.errorCode = errorCode.toString();
    }
}
