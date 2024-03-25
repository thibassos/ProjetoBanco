package br.com.cdb.bancodigital.dao;
import br.com.cdb.bancodigital.entity.*;

import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.Cliente;

public class ClienteDAO {
	ArrayList<Cliente> ListaDeClientes = new ArrayList<>();
	
	public void addCliente(Cliente cliente) {
		ListaDeClientes.add(cliente);
	}
	public void removerCliente(Cliente cliente) {
		ListaDeClientes.remove(cliente);
	}
	public ArrayList<Cliente> getListaDeClientes() {
		return ListaDeClientes;
	}
	public void mostrar() {
		getListaDeClientes();
		for (Cliente cli : ListaDeClientes) {
			System.out.println(cli);
		}
	}
	
}
