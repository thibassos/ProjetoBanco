package br.com.cdb.bancodigital.model;
import br.com.cdb.bancodigital.entity.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class HistoricoTransacoes {
	   private Map<YearMonth, List<Transacao>> historicoPorMes;
	   
	   LocalDate dataAtual = LocalDate.now();
       
   
       int mesAtual = dataAtual.getMonthValue();
       
    
       int anoAtual = dataAtual.getYear();
	   
	    public HistoricoTransacoes() {
	        this.historicoPorMes = new HashMap<>();
	    }

	    public void adicionarTransacao(Transacao transacao) {
	        YearMonth mesTransacao = YearMonth.from(transacao.getDataHora());
	        historicoPorMes.computeIfAbsent(mesTransacao, k -> new ArrayList<>()).add(transacao);
	    }

	    public List<Transacao> getTransacoesPorMes() {
	    	int ano = getAnoAtual();
	    	int mes = getMesAtual();
	        YearMonth mesConsulta = YearMonth.of(ano, mes);
	        return historicoPorMes.getOrDefault(mesConsulta, new ArrayList<>());
	    }
	    


		public LocalDate getDataAtual() {
			return dataAtual;
		}

		public void setDataAtual(LocalDate dataAtual) {
			this.dataAtual = dataAtual;
		}

		public int getMesAtual() {
			return mesAtual;
		}

		public void setMesAtual(int mesAtual) {
			this.mesAtual = mesAtual;
		}

		public int getAnoAtual() {
			return anoAtual;
		}

}	
