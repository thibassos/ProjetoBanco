package br.com.cdb.bancodigital.model;

public class GerenciarId {
	private int id = 1;

	public void setId(int id) {
		this.id = ++id;
	}

	public int getId() {
		return id;
	}
	
}
