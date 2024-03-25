package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.com.cdb.bancodigital.utils.Constantes;

public class Poupanca extends Conta {

	public Poupanca(Cliente cli, String tipoCliente) {
		super(cli);
		this.setTipoConta(Constantes.POUPANCA);
		this.numeroConta = (cli.getId() + 02);
		
		agendarRendimentoDiario();
	}
	
	public boolean transferir(Cliente cli, Corrente c, double transferencia) {
		if (transferencia <= this.getSaldo()) {
		setSaldo(getSaldo()-transferencia);
		cli.suasContas.contains(c);
		c.depositar(transferencia);
		return true;
		}
		return false;
	}
	private void agendarRendimentoDiario() {
        Timer timer = new Timer();
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);
        long delay = amanha.atStartOfDay().toEpochSecond(null) * 1000 - System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                aumentarSaldoDiariamente();
                agendarRendimentoDiario(); // Agendar a próxima diminuição diária
            }
        }, delay);
    }

    private void aumentarSaldoDiariamente() {
    	
        double valorAumento;
        switch(dono.getTipoCliente()) {
        	case Constantes.COMUM:
        		valorAumento = ((dono.suasContas.get(1).getSaldo() * 0.5)/100)/30;
        		break;
        	case Constantes.SUPER:
            	valorAumento = ((dono.suasContas.get(1).getSaldo() * 1.0)/100)/30;
            	break;
        	case Constantes.PREMIUM:
            	valorAumento = ((dono.suasContas.get(1).getSaldo() * 1.5)/100)/30;
            	break;
            default:
            	valorAumento = 0;
            	break;
        }
        setSaldo(getSaldo() + valorAumento);
        System.out.println("Saldo rendendo " + valorAumento + " diariamente.");
    }
	
	Calendar cal = Calendar.getInstance();
	
	int mesAtual = cal.get(Calendar.MONTH);
	int mesAnterior = (Calendar.MONTH - 1);
	
}