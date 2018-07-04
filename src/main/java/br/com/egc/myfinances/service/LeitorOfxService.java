package br.com.egc.myfinances.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.egc.myfinances.dao.CategoriaDAO;
import br.com.egc.myfinances.dao.TransacaoDAO;
import br.com.egc.myfinances.entity.CategoriaPK;
import br.com.egc.myfinances.entity.CategoriaVO;
import br.com.egc.myfinances.entity.TransacaoVO;
import net.sf.ofx4j.domain.data.MessageSetType;
import net.sf.ofx4j.domain.data.ResponseEnvelope;
import net.sf.ofx4j.domain.data.ResponseMessageSet;
import net.sf.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import net.sf.ofx4j.domain.data.banking.BankingResponseMessageSet;
import net.sf.ofx4j.domain.data.common.Transaction;
import net.sf.ofx4j.io.AggregateUnmarshaller;
import net.sf.ofx4j.io.OFXParseException;

public class LeitorOfxService implements Serializable {

	@Inject
	private CategoriaDAO categoriaDAO;
	@Inject
	private TransacaoDAO transacaoDAO;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TransacaoVO> processarArquivoOfx(InputStream inputStreamFile) throws IOException, OFXParseException {

		
		CategoriaVO categoriaDespesaPadraoVO = categoriaDAO.buscarCategoriaDespesaPadrao(Util.getUsuarioNaSession().getIdUsuario());
		CategoriaVO categoriaReceitaPadraoVO = categoriaDAO.buscarCategoriaReceitaPadrao(Util.getUsuarioNaSession().getIdUsuario());

		List<TransacaoVO> listTransacaoVO = new ArrayList<>();

		AggregateUnmarshaller a = new AggregateUnmarshaller(ResponseEnvelope.class);
		ResponseEnvelope re = (ResponseEnvelope) a.unmarshal(inputStreamFile);

		// objeto contendo informações como instituição financeira, idioma, data da
		// conta.
		// SignonResponse sr = re.getSignonResponse();

		// como não existe esse get "BankStatementResponse bsr =
		// re.getBankStatementResponse();"
		// fiz esse codigo para capturar a lista de transações
		MessageSetType type = MessageSetType.banking;
		ResponseMessageSet message = re.getMessageSet(type);
		if (message != null) {
			List<BankStatementResponseTransaction> bank = ((BankingResponseMessageSet) message).getStatementResponses();

			System.out.println("");

			// ContaVO contaVO = new ContaVO();
			// contaVO.setListTransacaoVO(new ArrayList<TransacaoVO>());
			//
			// contaVO.setSaldoConta(BigDecimal.valueOf(-39.14));// saldo em 01/05/2018

			for (BankStatementResponseTransaction b : bank) {
				// System.out.println("cc: " + b.getMessage().getAccount().getAccountNumber());
				// System.out.println("ag: " + b.getMessage().getAccount().getBranchId());
				// System.out.println("balanço final: " +
				// b.getMessage().getLedgerBalance().getAmount());
				// System.out.println("dataDoArquivo: " +
				// b.getMessage().getLedgerBalance().getAsOfDate());
				List<Transaction> list = b.getMessage().getTransactionList().getTransactions();
				// System.out.println("TRANSAÇÕES\n");
				for (Transaction transaction : list) {

					TransacaoVO transacaoVO = new TransacaoVO();
					transacaoVO.setTipoTransacao(transaction.getTransactionType().name());
					transacaoVO.setIdTransacaoOriginal(Long.parseLong(transaction.getId()));
					transacaoVO.setDataTransacao(transaction.getDatePosted());
					transacaoVO.setDescricaoTransacao(transaction.getMemo());
					transacaoVO.setValorTransacao(BigDecimal.valueOf(transaction.getAmount()));
					if(transacaoVO.getTipoTransacao().equals("DEBIT")) {
						transacaoVO.setCategoriaVO(categoriaDespesaPadraoVO);
						
					}
					if(transacaoVO.getTipoTransacao().equals("CREDIT")) {
						transacaoVO.setCategoriaVO(categoriaReceitaPadraoVO);
						
					}
					

					// contaVO.getListTransacaoVO().add(transacaoVO);
					//
					// contaVO.setSaldoConta(contaVO.getSaldoConta().add(transacaoVO.getValorTransacao()));

					// System.out.println("tipo: " + transaction.getTransactionType().name());
					// System.out.println("id: " + transaction.getId());
					// System.out.println("data: " + transaction.getDatePosted());
					// System.out.println("valor: " + transaction.getAmount());
					// System.out.println("descricao: " + transaction.getMemo());
					// System.out.println("");
					if(!transacaoDAO.existeTransacao(transacaoVO.getIdTransacaoOriginal())) {
						listTransacaoVO.add(transacaoVO);
						
					}
				}
			}

			// System.out.println("Saldo Final:" + contaVO.getSaldoConta());
		}
		return listTransacaoVO;

	}

}
