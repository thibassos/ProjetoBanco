package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public abstract class Seguro  {

    private int numeroSeguro;
    private int numeroCartaoSegurado;
    private int numeroApoliceSeguro;
    private LocalDate dataContratacao;
    private LocalDate dataVencimento;
    private double valorIndenizacao;
    
    public Seguro(int numeroCartaoSegurado, int numeroApoliceSeguro,double valorIndenizacao) {
        this.numeroCartaoSegurado = numeroCartaoSegurado;
        this.numeroApoliceSeguro = numeroApoliceSeguro;
        this.valorIndenizacao = valorIndenizacao;
    }

    public int getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(int numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public int getNumeroCartaoSegurado() {
        return numeroCartaoSegurado;
    }

    public void setNumeroCartaoSegurado(int numeroCartaoSegurado) {
        this.numeroCartaoSegurado = numeroCartaoSegurado;
    }

    public int getNumeroApoliceSeguro() {
        return numeroApoliceSeguro;
    }

    public void setNumeroApoliceSeguro(int numeroApoliceSeguro) {
        this.numeroApoliceSeguro = numeroApoliceSeguro;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    public double getValorIndenizacao() {
        return valorIndenizacao;
    }

    public void setValorIndenizacao(double valorIndenizacao) {
        this.valorIndenizacao = valorIndenizacao;
    }

    

    public abstract String gerarApolicePdf();

	public double calcularValor() {
		// TODO Auto-generated method stub
		return 0;
	}

}