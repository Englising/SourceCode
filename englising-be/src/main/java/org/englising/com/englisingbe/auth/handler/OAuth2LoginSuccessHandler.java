package org.englising.com.englisingbe.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.englising.com.englisingbe.auth.jwt.CookieUtil;
import org.englising.com.englisingbe.auth.jwt.JwtProvider;
import org.englising.com.englisingbe.auth.jwt.JwtResponseDto;
import org.englising.com.englisingbe.auth.CustomOAuth2User;
import org.englising.com.englisingbe.user.entity.User;
import org.englising.com.englisingbe.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// OAuth2 로그인 성공 시 로직 처리
// 로그인 성공했으면 accessToken, refreshToken 생성 후 헤더애 넣어주기
// token 헤더에 넣어주기
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final CookieUtil cookieUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("OAuth2 로그인 성공 - SuccessHandler");

        try {
            // CustomOauth2User로 캐스팅하여 인증된 사용자 정보 가져옴
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            log.info("OAuth2LoginSuccessHandler - oAuth2User 가져오나??" + oAuth2User);

            String email = oAuth2User.getEmail();
//            String email = oAuth2User.getAttribute("email");
            log.info("OAuth2LoginSuccessHandler - email --> " + email);

            User user = userRepository.findByEmail(email).orElse(null);
            // user가 null이라서 밑에게 실행안하는 것 !!!!
            if (user != null) {
                // 유저 정보 기반으로 토큰 생성
                JwtResponseDto jwtResponseDto = jwtProvider.createTokens(authentication, user.getUserId());

                // AccessToken 쿠키로 설정
                Cookie accessCookie = cookieUtil.createAccessCookie("Authorization", jwtResponseDto.getAccessToken());

                //refreshToken 쿠키도 설정하고 응답에 쿠키 추가
                Cookie refreshCookie = cookieUtil.createRefreshCookie("Authorization-refresh", jwtResponseDto.getRefreshToken());

                // 응답에 쿠키 추가
                response.addCookie(accessCookie);
                response.addCookie(refreshCookie);
                response.sendRedirect("https://j10a106.p.ssafy.io/englising/selectSingle1");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
