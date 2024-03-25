package br.com.cdb.bancodigital.entity;
import br.com.cdb.bancodigital.utils.*;

public class Conta {
	
	protected Double saldo = 0.0;
	protected Cliente dono;
	protected int numeroConta;
	protected String tipoConta;
	
	public Conta(Cliente cli) {
		this.dono = cli;
	}
	public void depositar(double deposito) {
		this.saldo += deposito;	
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Cliente getDono() {
		return dono;
	}
	public void setDono(Cliente dono) {
		this.dono = dono;
	}
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	@Override
	public String toString() {
		
		return this.dono.toString();
	}
	
}
