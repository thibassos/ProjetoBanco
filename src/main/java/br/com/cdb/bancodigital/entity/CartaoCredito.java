package br.com.cdb.bancodigital.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import br.com.cdb.bancodigital.utils.Constantes;

public class CartaoCredito extends Cartao {

	private int numeroCartao;
	private boolean credito = true; 
	private boolean ativo;
	private boolean seguro;
	private double valorSeguro;
	private String tipoCliente;
	private double limitePreAprovado;
	private double saldoDisponivel;
	private double totalFatura;
	private String dataVencimento;
	private double apolice;
	private double taxaFechamentoFatura = 0.05;
	private HashMap<String, Double> fatura = new HashMap<>();
	ArrayList<Seguro> Se = new ArrayList<>();

	public CartaoCredito(Cliente dono, int numeroCartao, String validadeMes, String validadeAno, String codigoSeguranca,
			String senhaCartao) {
		super(dono, numeroCartao, validadeMes, validadeAno, codigoSeguranca, senhaCartao);
		this.numeroCartao = numeroCartao;
		this.ativo = false;
		this.tipoCliente = dono.getTipoCliente();
		this.limitePreAprovado = getLimitePreAprovado(dono.getTipoCliente());
		this.saldoDisponivel = limitePreAprovado;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 10);
		this.dataVencimento = sdf.format(calendar.getTime());

	}


	private double getLimitePreAprovado(String tipoCliente) {
		switch (tipoCliente) {
		case Constantes.COMUM:
			this.limitePreAprovado = 1000.00;
			break;
		case Constantes.SUPER: 
			this.limitePreAprovado = 5000.00;
			break;
		case Constantes.PREMIUM:
			this.limitePreAprovado = 10000.00;
			break;
		}
		return limitePreAprovado;
	}

	public boolean ativarCartao() {
		if (!ativo) {
			this.ativo = true;
			return true;
		} else {
			System.out.println("Seu Cartão já está ativo.");
			return false;
		}
	}
	public boolean ativarSeguro() {
		if (!seguro) {
			this.seguro = true;
			return true;				
		} else {
			return false;
		}
	}

	public double liberarLimite() {
		if (!ativo) {
			System.out.println("Cartão precisa estar ativo para liberar o limite.");
			return 0;
		}

		double limiteLiberado = getLimitePreAprovado(tipoCliente);
		saldoDisponivel += limiteLiberado;
		return limiteLiberado;
	}

	public boolean desativarCartao() {
		if (ativo) {
			this.ativo = false;
			return true;
		} else {
			System.out.println("Cartão já está Inativo.");
			return false;
		}
	}
	public boolean desativarSeguro() {
		if (seguro) {
			this.seguro = false;
			return true;
		} else {
			return false;
		}
	}
	public double calcularSeguro() {
		switch (dono.getTipoCliente()) {
		case Constantes.COMUM:
			return this.valorSeguro = 50.0;
		case Constantes.SUPER: 
			return this.valorSeguro = 50.0;
		case Constantes.PREMIUM:
			return this.valorSeguro = 0.0;
		default: 
			return this.valorSeguro = 0.0;
		}
	}
	public boolean realizarCompra(double valorCompra) {

	    if (!ativo) {
	        System.out.println("Cartão precisa estar ativo para realizar compras.");
	        return false;
	    }

	    else if (valorCompra > saldoDisponivel) {
	        System.out.println("Saldo insuficiente para realizar compra. Saldo disponível: R$" + saldoDisponivel);
	        return false;
	    }
	    else {
	    saldoDisponivel -= valorCompra;
	    fatura.put(dataAtual(), valorCompra);
	    totalFatura += valorCompra;
	    return true;
	    }
	    
	}
	public void seguroFraude() {
		if(seguro) {
		dono.suasContas.get(0).depositar(apolice);
		Transacao t = new Transacao("Seguro de fraude", apolice);
		}
	}

	public String dataAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date());
	}

	public void mostrarSaldoDisponivel() {
		System.out.println("Saldo disponível: R$" + saldoDisponivel);
	}
	
	
	public double fecharFatura() {
        if (totalFatura >= (limitePreAprovado * 0.8)) {
            totalFatura += totalFatura * taxaFechamentoFatura;
            totalFatura += valorSeguro;
            return totalFatura;
        }
        else {
        	totalFatura += valorSeguro;
        	return totalFatura;
        }
	}
	public Transacao salvarFatura() {
		double total = fecharFatura();
		Transacao cc = new Transacao(this.dono.suasContas.get(0), "Fatura cartão", total);
		return cc;
	}
	


	public boolean isCredito() {
		return credito;
	}


	public void setCredito(boolean credito) {
		this.credito = credito;
	}


	public HashMap<String, Double> getFatura() {
		return fatura;
	}


	public void setFatura(HashMap<String, Double> fatura) {
		this.fatura = fatura;
	}

	
	
}
