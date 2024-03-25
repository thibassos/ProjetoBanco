package br.com.cdb.bancodigital.dao;
import br.com.cdb.bancodigital.entity.*;
import java.util.ArrayList;

public class ContaDAO {
	ArrayList<Conta> Contas = new ArrayList<Conta>();
	
	public void addConta(Conta conta) {	
		Contas.add(conta);
	}

}
