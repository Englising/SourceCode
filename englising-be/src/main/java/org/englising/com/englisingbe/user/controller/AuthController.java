package org.englising.com.englisingbe.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.englising.com.englisingbe.global.dto.DefaultResponseDto;
import org.englising.com.englisingbe.jwt.CookieUtil;
import org.englising.com.englisingbe.jwt.JwtResponseDto;
import org.englising.com.englisingbe.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth Controller", description = "로그인 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final CookieUtil cookieUtil;

    @PostMapping("/guest")
    // API 상세 정보 기술
    @Operation(
            summary = "게스트 로그인",
            description = "게스트로 로그인합니다"
    )
    public ResponseEntity<DefaultResponseDto<?>> guest(HttpServletResponse response) throws Exception {

        JwtResponseDto jwtResponseDto = authService.guest();

        Cookie accessCookie = cookieUtil.createAccessCookie("Authorization", jwtResponseDto.getAccessToken());
        Cookie refreshCookie = cookieUtil.createRefreshCookie("Authorization-refresh", jwtResponseDto.getRefreshToken());
        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
        // todo. 에러 처리 추가

        // ResponseEntity에 헤더와 본문 설정
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DefaultResponseDto<>(200, "토큰 발급이 완료되었습니다.", jwtResponseDto));
    }

    @GetMapping("/login")
    @Operation(
            summary = "회원 아이디 반환",
            description = "카카오 로그인을 성공하면 토큰을 기반으로 회원 아이디를 반환합니다"
    )
    public ResponseEntity<DefaultResponseDto<?>> getUserId(HttpServletRequest request) {

        Long userId = authService.getUserID(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DefaultResponseDto<>(200, "회원 아이디를 가져왔습니다", userId));
    }
}
