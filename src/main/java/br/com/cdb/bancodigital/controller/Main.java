package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.model.ClienteService;
import br.com.cdb.bancodigital.model.Menu;
import br.com.cdb.bancodigital.utils.Constantes;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello world!");
	//	ClienteService cs = new ClienteService();
	//	ClienteDAO cd = new ClienteDAO();
		
//		cs.addCliente("ana", "44871456862", Constantes.COMUM, "123");
		//cs.addCliente("bruna", "44871456861", Constantes.COMUM, "1234");
	//	cs.addCliente("luana", "44871456864", Constantes.COMUM, "12345");
//		cs.addCliente("daniela", "44871456862", Constantes.COMUM, "123");
		//cs.addCliente("beatriz", "44871456861", Constantes.COMUM, "1342");
	//	cs.getCd().mostrar();
		Menu menu = new Menu();
		menu.operacoes();
	}

}
