package br.com.cdb.bancodigital.model;

import java.util.Scanner;

import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import br.com.cdb.bancodigital.utils.Constantes;

public class Menu {
	ClienteService cs = new ClienteService();
	static HistoricoTransacoes ht = new HistoricoTransacoes();
	Scanner input = new Scanner(System.in);

	public void operacoes() {
		try {
		Cliente usuario = null;
		while(usuario == null) {
			System.out.println("Seja bem vindo!\n1- Login\n2- Criar conta\n3- Sair");
			int escolha = input.nextInt();
			switch(escolha) {
				case 1:	
						usuario = cs.login();
						while(usuario != null) {
							System.out.println("logado");
							System.out.println(usuario.toString());
							System.out.println("Como podemos te ajudar?\n1 - Informações da conta\n2 - Realizar pagamentos\n3 - Histórico de transações\n4 - Fatura\n5 - Minha poupança\n6 - Seguro\n 7 - Sair ");
							int op = input.nextInt();
							switch(op) {
								case 1:	usuario.toString();
									break;
								case 2:	System.out.println("Que tipo de pagamento deseja realizar?\n1 -  PIX | 2 - Débito | 3 - Crédito");
									break;
								case 3: System.out.println(ht.getTransacoesPorMes());
									break;
								case 4:
									if(usuario.getContas().get() != 0) {
										usuario.getContas().get(1);
									}
									else {
										System.out.println("Deseja criar uma poupança?\n1- Sim | 2- Não");
										int opo = input.nextInt();
										input.nextLine();
									}
									break;
								case 5:
									break;
								case 6:
									break;
								case 7:
									System.out.println("Até mais!");
									usuario = null;
									break;
								
							}
							break;
						}
					break;
				case 2: 
						cs.criarConta();
					break;
			}
			}
		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
}
}


