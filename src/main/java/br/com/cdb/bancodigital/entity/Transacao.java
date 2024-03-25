package br.com.cdb.bancodigital.entity;

import java.time.LocalDateTime;

public class Transacao {
		private Conta origem;
		private Conta destino;
	    private String descricao;
	    private double valor;
	    private LocalDateTime dataHora;

	    public Transacao(Conta origem, Conta destino, String descricao, double valor) {
	    	this.origem = origem;
	        this.descricao = descricao;
	        this.valor = valor;
	        this.dataHora = LocalDateTime.now();
	    }
	    public Transacao(Conta origem, String descricao, double valor) {
	    	this.origem = origem;
	        this.descricao = descricao;
	        this.valor = valor;
	        this.dataHora = LocalDateTime.now();
	    }
	    public Transacao(String descricao, double valor) {
	    	this.descricao = descricao;
	    	this.valor = valor;
	    }

	    
	    public String getDescricao() {
	        return descricao;
	    }

	    public Conta getOrigem() {
			return origem;
		}

		public void setOrigem(Conta origem) {
			this.origem = origem;
		}

		public Conta getDestino() {
			return destino;
		}

		public void setDestino(Conta destino) {
			this.destino = destino;
		}

		public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public void setValor(double valor) {
	        this.valor = valor;
	    }

	    public LocalDateTime getDataHora() {
	        return dataHora;
	    }

	    public void setDataHora(LocalDateTime dataHora) {
	        this.dataHora = dataHora;
	    }
	    
	    public String toString() {
			return "R$" + Double.toString(getValor()) + "- Descrição: " + getDescricao() +"- Origem: "+ getOrigem() ;
	    	
	    }
}
