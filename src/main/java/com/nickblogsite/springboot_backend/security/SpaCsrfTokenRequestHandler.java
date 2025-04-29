package com.nickblogsite.springboot_backend.security;

import java.util.function.*;

import org.springframework.security.web.csrf.*;
import org.springframework.util.*;

import jakarta.servlet.http.*;

/**
 * <a href="https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html">Special CSRF handling for SPAs</a>
 */
final class SpaCsrfTokenRequestHandler implements CsrfTokenRequestHandler {
	private final CsrfTokenRequestHandler plain = new CsrfTokenRequestAttributeHandler();
	private final CsrfTokenRequestHandler xor = new XorCsrfTokenRequestAttributeHandler();

	@Override
	public void handle( final HttpServletRequest request, final HttpServletResponse response, final Supplier<CsrfToken> csrfToken ) {
		/*
		 * Always use XorCsrfTokenRequestAttributeHandler to provide BREACH protection of
		 * the CsrfToken when it is rendered in the response body.
		 */
		this.xor.handle(request, response, csrfToken);
		/*
		 * Render the token value to a cookie by causing the deferred token to be loaded.
		 */
		csrfToken.get();
	}

	@Override
	public String resolveCsrfTokenValue( final HttpServletRequest request, final CsrfToken csrfToken ) {
		final String headerValue = request.getHeader( csrfToken.getHeaderName() );
		/*
		 * If the request contains a request header, use CsrfTokenRequestAttributeHandler
		 * to resolve the CsrfToken. This applies when a single-page application includes
		 * the header value automatically, which was obtained via a cookie containing the
		 * raw CsrfToken.
		 *
		 * In all other cases (e.g. if the request contains a request parameter), use
		 * XorCsrfTokenRequestAttributeHandler to resolve the CsrfToken. This applies
		 * when a server-side rendered form includes the _csrf request parameter as a
		 * hidden input.
		 */
		return ( StringUtils.hasText( headerValue ) ? this.plain : this.xor).resolveCsrfTokenValue( request, csrfToken );
	}
}