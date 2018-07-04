package br.com.egc.myfinances.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.entity.ContaVO;
import br.com.egc.myfinances.service.ContaService;
import br.com.egc.myfinances.service.Util;
import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
public class DashboardBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private ContaVO contaVO;

	@Inject
	private ContaService contaService;

	public void initDashboard() {

		contaVO = contaService.listarConta().get(0);
		
		Util.setContaNaSession(contaVO);
		

	}

	public void actionDashboard() {
		
		redirect("dashboard.xhtml");
		
		
	}

}
