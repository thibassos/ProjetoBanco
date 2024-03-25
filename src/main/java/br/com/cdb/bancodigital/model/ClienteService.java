package br.com.cdb.bancodigital.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.dao.ContaDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import br.com.cdb.bancodigital.entity.Corrente;
import br.com.cdb.bancodigital.entity.Poupanca;
import br.com.cdb.bancodigital.utils.Constantes;

public class ClienteService {
	
	static ClienteDAO cd = new ClienteDAO();
	static GerenciarId gerId = new GerenciarId();
	Scanner input = new Scanner(System.in);
	
	public Cliente login() {
		System.out.println("Insira seu CPF: ");
		String cpf = input.nextLine();
		System.out.println("Digite sua senha: ");
		String senha = input.nextLine();
		if (cd.getListaDeClientes().size() > 0) {
			for (Cliente cli : cd.getListaDeClientes()) {
					if (cd.getListaDeClientes().contains(cli) && cli.getCpf().equals(cpf) && cli.getSenha().equals(senha)) {
						return cli;
					}
				}
		}
		return null;
	}
	
	public void criarConta() {
		System.out.println("Digite seu nome: ");
		String nome = input.nextLine();
		System.out.println("Insira seu CPF: ");
		String cpf = input.nextLine();
		System.out.println("Insira sua data de nascimento: ");
		String nascimento = input.nextLine();
		System.out.println("Insira seu CEP: ");
		String endereco = input.nextLine();
		System.out.println("qual a sua renda mensal?");
		double renda = input.nextDouble();
		int v;
		if(renda < 2000) {
			v = 1;
		}else if(renda >= 2000 && renda < 5000) {
			v = 2;
		}else {
			v = 3;
		}
		System.out.println("Qual tipo de conta deseja abrir?");
		switch(v) {
			case 1:
				System.out.println("1 - Comum: Taxa de manutenção mensal para conta corrente: R$12,00 | Taxa de rendimento mensal para conta poupança: 0,5% | Limite de crédito R$ 1.000");
				break;
			case 2:
				System.out.println("1 - Comum: Taxa de manutenção mensal para conta corrente: R$12,00 | Taxa de rendimento mensal para conta poupança: 0,5% | Limite de crédito R$ 1.000");
				System.out.println("2 - Super: Taxa de manutenção mensal para conta corrente: R$8,00 | Taxa de rendimento mensal para conta poupança: 1% | Limite de crédito R$ 5.000");
				break;
			case 3:
				System.out.println("1 - Comum: Taxa de manutenção mensal para conta corrente: R$12,00 | Taxa de rendimento mensal para conta poupança: 0,5% | Limite de crédito R$ 1.000");
				System.out.println("2 - Super: Taxa de manutenção mensal para conta corrente: R$8,00 | Taxa de rendimento mensal para conta poupança: 1% | Limite de crédito R$ 5.000");
				System.out.println("3 - Premium: Taxa de manutenção mensal para conta corrente: Isenta | Taxa de rendimento mensal para conta poupança: 1,5% | Limite de crédito R$ 10.000");
				break;
			default:
				System.out.println("Valor inválido");
				break;
		}
		int a = input.nextInt();
		input.nextLine();
		String tipoCliente;
		switch(a) {
			case 1:
				tipoCliente = Constantes.COMUM;
				break;
			case 2:
				tipoCliente = Constantes.SUPER;
				break;
			case 3:
				tipoCliente = Constantes.PREMIUM;
				break;
			default:
				tipoCliente = Constantes.COMUM;
				break;
		}

		System.out.println("Crie uma senha: ");
		String senha = input.nextLine();
		System.out.println("Confirme sua senha: ");
		String senha2 = input.nextLine();
		
		addCliente(nome, cpf, nascimento, endereco, tipoCliente, senha);
	}
	
	public boolean addCliente(String nome, String cpf,String endereco, String nascimento,String tipoCliente, String senha) {
		if (!validarNome(nome)) {
			return false;
		}
		if (!validarCpf(cpf)) {
			return false;
		}
		if (!validarCEP(endereco)) {
			return false;
		}
		if (!validarMaiorIdade(nascimento)) {
			return false;
		}
		int id = gerId.getId();
		gerId.setId(id);
		Cliente cliente = new Cliente(nome, cpf, nascimento, endereco, tipoCliente, senha, id);
		gerarContaBancaria(cliente, 1);
		cd.addCliente(cliente);
		return true;
	}
	
	
	public boolean removeCliente(Cliente cli) {
		if (cd.getListaDeClientes().contains(cli)) {
			cd.removerCliente(cli);
		}
		return false;
	}
	public static boolean validarMaiorIdade(String dataNascimento) {
		
        dataNascimento = dataNascimento.replaceAll("[^0-9]", "");

        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})(\\d{4})");
        Matcher matcher = pattern.matcher(dataNascimento);

        if (matcher.matches()) {
          
            int dia = Integer.parseInt(matcher.group(1));
            int mes = Integer.parseInt(matcher.group(2));
            int ano = Integer.parseInt(matcher.group(3));

            try {
                LocalDate dataNascimento1 = LocalDate.of(ano, mes, dia);
                LocalDate dataAtual = LocalDate.now();

                int idade = dataAtual.getYear() - dataNascimento1.getYear();
                if (dataNascimento1.plusYears(idade).isAfter(dataAtual)) {
                    idade--; 
                }

                return idade >= 18;
            } catch (Exception e) {
               
                return false;
            }
        } else {
          
            return false;
        }
    }

	private boolean validarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;

        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9)
            primeiroDigito = 0;


        if (primeiroDigito != cpf.charAt(9) - '0')
            return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9)
            segundoDigito = 0;

        if (segundoDigito != cpf.charAt(10) - '0')
            return false;
        
        for (Cliente cli : cd.getListaDeClientes()) {
        	if (cli.getCpf().equals(cpf))
        		return false;
        }
        return true;
    }
	
	public static boolean validarCEP(String cep) {
        String regex = "^\\d{5}-?\\d{3}$";

        if (!Pattern.matches(regex, cep)) {
            return false;
        }
       
        return true;
    }


	private boolean validarNome(String nome) {
		if(nome.length() >= 2 && nome.length() <= 100) {
			return true;
		}
		else {
		return false;
		}
	}
	
	private static Cliente encontrarCliente(int idCliente) {
		Cliente cliente = null;
		if (cd.getListaDeClientes().size() > 0) {
			for (Cliente c : cd.getListaDeClientes()) {
				if (idCliente == c.getId()) {
					cliente = c;
				}
			}
		}
		return cliente;
	}
	
	private static void gerarContaBancaria(Cliente cli, int tipo) {
		String tipoCliente = cli.getTipoCliente();
		switch(tipo) {
			case 1: 
					Corrente c = new Corrente(cli, tipoCliente);
				break;
			case 2:
					Poupanca p = new Poupanca(cli, tipoCliente);
				break;
			default:
				break;
		}
	}
	
	private static Conta encontrarConta(int idConta) {
		Conta conta = null;
		if (cd.getListaDeClientes().size() > 0) {
			for (Cliente cli : cd.getListaDeClientes()) {
				for (Conta c : cli.getContas()) {
					if (c.getNumeroConta() == idConta) {
						conta = c;
					}
				}
			}
		}
		return conta;
	}


	public ClienteDAO getCd() {
		return cd;
	}

	
	
}
