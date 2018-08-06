package br.com.egc.myfinances.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;

@Named
public class CategoriaConverter implements Converter {

	@Inject
	private CategoriaDAO categoriaDAO;

	public String getAsString(FacesContext fc, UIComponent uic, Object value) {

		if (value instanceof CategoriaVO) {

			CategoriaVO obj = (CategoriaVO) value;

			return obj.getCategoriaPK().getIdUsuario() + "-" + obj.getCategoriaPK().getIdCategoria();

		} else {

			return null;
		}

	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (!value.equals("SELECIONE")) {

			try {

				String pk[] = value.split("-");

				CategoriaPK categoriaPK = new CategoriaPK(Long.parseLong(pk[0]), Long.parseLong(pk[1]));

				return categoriaDAO.buscarCategoriaPorId(categoriaPK);

			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error Categoria",
						"Código Invalido."));

			}
		}
		return null;
	}
}
