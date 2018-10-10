package br.com.egc.myfinances.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Getter
	@Setter
//	private String emailUsuario = "e83carvalho@gmail.com";
	private String emailUsuario;

	@Getter
	@Setter
	private String senhaUsuario;

	public void actionEntrar() {

		try {

			usuarioService.buscarUsuario(emailUsuario, senhaUsuario);

			redirect("dashboard.xhtml");

		} catch (Exception e) {
			addErrorMessage(e.getMessage());
		}

	}

}
