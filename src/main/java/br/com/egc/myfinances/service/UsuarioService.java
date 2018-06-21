package br.com.egc.myfinances.service;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.egc.myfinances.dao.UsuarioDAO;
import br.com.egc.myfinances.entity.UsuarioVO;

@Transactional
public class UsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	public UsuarioVO buscarUsuario(String emailUsuario, String senhaUsuario) throws Exception {

		UsuarioVO usuarioVO = usuarioDAO.buscarUsuario(emailUsuario);

		if (usuarioVO == null) {

			throw new Exception("Usuario não Localizado.");
		}

		if (!usuarioVO.getSenhaUsuario().equals(senhaUsuario)) {

			throw new Exception("Senha Informada Inválida.");

		}

		Util.setUsuarioNaSession(usuarioVO);

		return usuarioVO;
	}
}
