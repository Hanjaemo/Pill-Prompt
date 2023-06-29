package capstone.pillprompt.exception.domain;

import capstone.pillprompt.exception.CustomException;
import capstone.pillprompt.exception.ErrorCode;

public class PillException extends CustomException {
    public PillException(ErrorCode errorCode) {
        super(errorCode);
    }
}
