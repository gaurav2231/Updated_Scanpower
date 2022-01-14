package com.Scanpower.api;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class MyAuthFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// Access-Control-Allow-Origin
        String origin = httpRequest.getHeader("Origin");
        httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpResponse.setHeader("Vary", "Origin");

        // Access-Control-Max-Age
        httpResponse.setHeader("Access-Control-Max-Age", "3600");

        // Access-Control-Allow-Credentials
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Access-Control-Allow-Methods
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        // Access-Control-Allow-Headers
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type, Accept, Cache-Control, remember-me");
        
        if (httpRequest.getMethod().equals("OPTIONS")) {
        	httpResponse.flushBuffer();
        }else {
    		String authHeader = httpRequest.getHeader("Authorization");
    		if (authHeader != null) {
    			String[] authHeaderArr = authHeader.split("Bearer ");
    			if(authHeaderArr.length > 1 && authHeaderArr[1] != null) {
    				String token = authHeaderArr[1];
    				try {
    					Claims c = Jwts.parser().setSigningKey(JWTConfig.API_SECRET_KEY)
    							.parseClaimsJws(token).getBody();
    					
    					httpRequest.setAttribute("email", c.get("email").toString());
    					httpRequest.setAttribute("password", c.get("password").toString());
    					
    				} catch (Exception e) {
    					e.printStackTrace();
    					sendAuthErrorMessage("invalid/expired token", httpResponse);
    					//httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
    					return;
    					
    				}
    			}else {
    				sendAuthErrorMessage("Authorization token must be in form of: Bearer [TOKEN]", httpResponse);
    				//httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be in form of: Bearer [TOKEN]");
    				return;
    				
    			}
    		}else {
    			sendAuthErrorMessage("Authorization token must be provided.", httpResponse);
    			//httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided.");
    			return;
    		}
    		chain.doFilter(request, response);
        }
	}
	
	private void sendAuthErrorMessage(String message, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		try {
			response.getOutputStream().println("{ \"success\": false, \"error\":\""+message+"\" }");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
