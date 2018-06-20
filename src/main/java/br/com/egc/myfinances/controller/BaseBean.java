package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@SessionScoped
@Named
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void redirect(String url) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/" + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
