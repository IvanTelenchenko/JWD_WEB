package by.epam.web.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		
		System.out.println("!!!Filter!!!");
		
		chain.doFilter(request, response);
	}

}
