package br.com.egc.myfinances.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.egc.myfinances.entity.UsuarioVO;

public class RequestFilter implements Filter {

	private FilterConfig config;

	@Override
	public void destroy() {
		System.out.println("RequestFilter.destroy()");
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("RequestFilter.doFilter()");

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(true);
		System.out.println(request.getRequestURI());

		String url = request.getRequestURI();

		if (url.equals("/myFinances/login.xhtml") || url.equals("/myFinances/")) {

			session.setAttribute("USUARIO", null);

			filterChain.doFilter(request, response);

		} else if (url.contains("/myFinances/resources/")) {
			filterChain.doFilter(request, response);
			
		} else {

			UsuarioVO usuarioVO = (UsuarioVO) session.getAttribute("USUARIO");

			if (usuarioVO == null) {

				redirecionando(request, response, "/myFinances/");

			} else {
				filterChain.doFilter(request, response);

			}

		}

		// filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("RequestFilter.init()");

		this.config = filterConfig;

	}

	private void redirecionando(HttpServletRequest request, HttpServletResponse response, String url1)
			throws IOException {
		if (isAjax(request)) {
			response.getWriter().print(xmlPartialRedirectToPage(request, url1));
			response.flushBuffer();
		} else {
			response.sendRedirect(url1);
		}
	}

	private boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	private String xmlPartialRedirectToPage(HttpServletRequest request, String page) {

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<partial-response><redirect url=\"").append(request.getContextPath())
				.append(request.getServletPath()).append(page).append("\"/></partial-response>");

		return sb.toString();
	}

}
