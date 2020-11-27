package com.algaworks.algamoney.api.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//Put RefreshToken in request
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessor implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if("/oauth/token".equalsIgnoreCase(req.getRequestURI()) && req.getParameter("grant_type").equals("refresh_token")
				&& req.getCookies() != null) {
			for(Cookie cokkie : req.getCookies()) {
				if(cokkie.getName().equals("refreshToken")) {
					String refreshToken = cokkie.getValue();
					req = new MyServletRequest(req, refreshToken);
				}
			}
		}
		chain.doFilter(req, response);
	}
	
	class MyServletRequest extends HttpServletRequestWrapper{

		private String refreshToken;
		
		public MyServletRequest(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] { this.refreshToken });
			map.setLocked(true);
			return map;
		}
	}
	
}
