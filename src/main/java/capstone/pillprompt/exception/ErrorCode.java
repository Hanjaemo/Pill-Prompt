package capstone.pillprompt.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "요청한 값이 유효하지 않습니다."),
    NOT_FOUND_PILL(404, "존재하지 않는 약입니다."),
    INTERNAL_SERVER_ERROR(500, "서부 내부 오류입니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
