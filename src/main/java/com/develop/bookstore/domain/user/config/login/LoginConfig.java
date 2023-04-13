package com.develop.bookstore.domain.user.config.login;

import com.develop.bookstore.domain.user.api.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // LogInterceptor 미리 만든 인터셉터 등록.
                .order(1) //인터셉터의 호출 순서를 지정. 낮을수록 먼저 실행.
                .addPathPatterns("/**") // 인터셉터를 적용할 URL 패턴을 지정한다.
                .excludePathPatterns("/", "/member/memberRegister", "/members/add", "/auth/login", "/auth/logout", "/css/**", "/*.ico", "/error"); // 인터셉터는 여기서 제외 할 패턴을 지정 가능.
    }
}
