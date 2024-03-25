package br.com.cdb.bancodigital.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.com.cdb.bancodigital.utils.*;

public class Corrente extends Conta {
	
	Calendar cal = Calendar.getInstance();
	
	LocalDate d01 = LocalDate.now();
	LocalDate diaAnterior = d01.minusDays(1);
	
	public Corrente(Cliente cli, String tipoCliente) {
		super(cli);
		this.setTipoConta(Constantes.CORRENTE);
		this.numeroConta = (cli.getId() + 01);
		agendarDiminuicaoSaldoMensal();
	}
	
	public void pix(Corrente c, double valor) {
		this.setSaldo(getSaldo()-valor);
		c.setSaldo(c.getSaldo()+valor);
		}
	
	public void depositar(double deposito) {
		if (deposito > 0.0) {
			setSaldo(getSaldo()+deposito);
			System.out.println("Operação realizada com sucesso");
		}else {
			System.out.println("Deposite uma quantia real");
		}
	}

	public void transferir(double transferencia, Conta destino) {
		if (transferencia > 0 && transferencia <= getSaldo()) {
		setSaldo(getSaldo()-transferencia);
		destino.depositar(transferencia);
		System.out.println("Operação realizada com sucesso");
		Transacao t = new Transacao(this.dono.suasContas.get(0), destino, "Transferência", transferencia);
		}
		else {
			System.out.println("Não foi possivel realizar a operação");
		}
	}
	private void agendarDiminuicaoSaldoMensal() {
        Timer timer = new Timer();
        LocalDate dataAtual = LocalDate.now();
        LocalDate primeiroDiaMesSeguinte = dataAtual.withDayOfMonth(1).plusMonths(1);
        long delay = primeiroDiaMesSeguinte.atStartOfDay().toEpochSecond(null) * 1000 - System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                diminuirSaldoMensalmente();
                agendarDiminuicaoSaldoMensal(); // Agendar a próxima diminuição mensal
            }
        }, delay);
    }

    private void diminuirSaldoMensalmente() {
        double valorDescontoMensal = calcularValorDescontoMensal(); // Defina aqui a taxa de desconto mensal
        saldo -= valorDescontoMensal;
        System.out.println("Saldo diminuído em " + valorDescontoMensal + " no início do mês.");
        Transacao t = new Transacao(dono.suasContas.get(0), "Taxa de manutenção", valorDescontoMensal);
    }

    private double calcularValorDescontoMensal() {
    	double taxaDescontoMensal;
    	switch (dono.getTipoCliente()) {
    	case Constantes.COMUM:
    		taxaDescontoMensal = 12;
    		break;
    	case Constantes.SUPER:
        	taxaDescontoMensal = 8;
        	break;
    	case Constantes.PREMIUM:
        	taxaDescontoMensal = 0;
        	break;
        default:
        	taxaDescontoMensal = 0;
        	break;
    	}
    	
        return saldo - taxaDescontoMensal;
    }

}
