package br.com.cdb.bancodigital.entity;

import java.util.ArrayList;

public class Cliente {
	private final int id;
	private final String nome;
	private final String cpf;
	private final String nascimento;
	private final String endereco;
	private final String senha;
	private String tipoCliente;
	
	ArrayList<Conta> suasContas = new ArrayList<Conta>();
	
	
	public Cliente(String nome, String cpf,String nascimento, String endereco, String tipoCliente, String senha, int id) {
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.endereco = endereco;
		this.tipoCliente = tipoCliente;
		this.id = id;
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	
	public String getNascimento() {
		return nascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void mudarTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public ArrayList<Conta> getContas() {
		return suasContas;
	}

	public void addConta(Conta conta) {
		suasContas.add(conta);
	}
	
	@Override
    public String toString() {
		return ("Cliente: " + this.getNome() + "ID: " + this.getId()+"\nSaldo: " + suasContas.get(0).getSaldo() +"\nCPF: " + this.getCpf() + "\nNascimento: " + this.getNascimento() + "\nEndereço: " + this.getEndereco());
	}
	
}
