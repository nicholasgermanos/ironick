package com.nickblogsite.springboot_backend.security;

import java.io.*;

import org.springframework.core.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE)
public class MyCORSFilter implements Filter {

	@Override
	public void doFilter( final ServletRequest req, final ServletResponse res, final FilterChain chain ) throws IOException, ServletException {

		final HttpServletRequest  request  = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, x-xsrf-token");

		chain.doFilter(req, res);
	}

	@Override
	public void init( final FilterConfig filterConfig ) {
	}

	@Override
	public void destroy() {
	}

}
