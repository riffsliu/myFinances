package br.com.egc.myfinances.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.entity.UsuarioVO;
import br.com.egc.myfinances.service.Util;

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

  public static UsuarioVO getUsuarioLogado() {

    try {
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      HttpSession session = request.getSession();

      return (UsuarioVO) session.getAttribute("USUARIO");

    } catch (Exception e) {
      return null;
    }

  }

  public static ContaVO getContaDaSessao() {

    try {

      return Util.getContaNaSession();

    } catch (Exception e) {
      return null;
    }

  }

  public static void addErrorMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "Error!"));
  }

  public static void addInfoMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "Sucesss!"));
  }

  protected void openDialog(String widgetVar) {
    RequestContext.getCurrentInstance().execute("PF('" + widgetVar + "').show()");
  }
  
  public Boolean renderizaCssValorPositivo(BigDecimal valor) {

    if (valor == null) {
      return Boolean.FALSE;
    }
    if (valor.compareTo(BigDecimal.ZERO) == -1) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;

    }

  }
}
