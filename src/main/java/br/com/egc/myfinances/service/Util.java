package br.com.egc.myfinances.service;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.egc.myfinances.entity.UsuarioVO;

@Transactional
public class Util implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void setUsuarioNaSession(UsuarioVO usuarioVO) {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("USUARIO", usuarioVO);

	}

	public static UsuarioVO getUsuarioNaSession() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		return (UsuarioVO) session.getAttribute("USUARIO");

	}
}
