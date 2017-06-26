package kr.co.torrent.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AuthFilter implements Filter {

	private List<String> pageList;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String pages = config.getInitParameter("pages");
		String[] arr = pages.split(";");
		pageList = new ArrayList<>();
		for (String page : arr) {
			pageList.add(page.trim());
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		String uri = hRequest.getRequestURI();
		
		String contextPath = hRequest.getContextPath();
		
		uri = uri.substring(contextPath.length());
		
		int index = pageList.indexOf(uri);
		
		boolean isRedirect = false;
		if (index == -1) {
			HttpSession session = hRequest.getSession();
			Object user = session.getAttribute("user");
			if (user == null) {
				isRedirect = true;
			}
		}
		
		if (isRedirect) {
			HttpServletResponse hResponse = 
					(HttpServletResponse)response;
			hResponse.sendRedirect(
					contextPath + "/user/LoginForm");
		}
		else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		
	}

}








