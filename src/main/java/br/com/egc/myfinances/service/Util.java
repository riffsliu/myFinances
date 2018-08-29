package br.com.egc.myfinances.service;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.UsuarioVO;
import br.com.egc.myfinances.util.Message;

@Transactional
public class Util implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void setUsuarioNaSession(UsuarioVO usuarioVO) {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("USUARIO", usuarioVO);

	}

	public static UsuarioVO getUsuarioNaSession() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		return (UsuarioVO) session.getAttribute("USUARIO");

	}

	public static ContaVO getContaNaSession() throws Exception {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();

		ContaVO contaVO = (ContaVO) session.getAttribute("CONTA");

		if (contaVO == null) {
			throw new Exception(Message.CONTA_BANCARIA_NAO_LOCALIZADA);
		} else {

			return (ContaVO) session.getAttribute("CONTA");
		}

	}

	public static void setContaNaSession(ContaVO contaVO) {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("CONTA", contaVO);

	}
}
