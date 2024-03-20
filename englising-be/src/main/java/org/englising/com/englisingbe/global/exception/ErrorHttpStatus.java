package org.englising.com.englisingbe.global.exception;

import lombok.Getter;

@Getter
public enum ErrorHttpStatus {
    UNAUTHORIZED_REFRESH_TOKEN(403,"RefreshToken이 유효하지 않습니다"),
    UNAUTHORIZED_TOKEN(403,"JWT토큰이 유효하지 않습니다"),
    NO_MATCHING_TRACK(404, "일치하는 노래가 없습니다");

    private final int code;
    private final String message;

    ErrorHttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
