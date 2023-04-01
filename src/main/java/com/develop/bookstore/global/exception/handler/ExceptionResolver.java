package com.develop.bookstore.global.exception.handler;

import com.develop.bookstore.domain.user.exception.UserInsertFailedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			if (ex instanceof UserInsertFailedException) {
				log.info("UserInsertFailedException resolver to 400");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
				return new ModelAndView();
			}
		} catch (IOException e) {
			log.error("resolver exception", e);
		}

		return null;
	}
}
