package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.UsuarioVO;
import br.com.egc.myfinances.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Getter
	@Setter
	private String emailUsuario;

	@Getter
	@Setter
	private String senhaUsuario;

	public void actionEntrar() {

		try {
			
			usuarioService.buscarUsuario(emailUsuario, senhaUsuario);

			redirect("dashboard.xhtml");
						
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void redirect(String url) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/" + url);
		} catch (IOException e) {
		}
	}

}
