package com.develop.bookstore.domain.user.api.interceptor;

import com.develop.bookstore.domain.user.domain.member.Member;
import com.develop.bookstore.domain.user.enumconst.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "해당 서비스는 로그인이 필요합니다");
            return false;
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("member 로그인 인증 완료 = {} : {}", member.getMemberId(), member.getMemberName());
        return true;
    }
}
