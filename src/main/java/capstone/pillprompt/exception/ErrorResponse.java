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

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.toString();
    }
}
