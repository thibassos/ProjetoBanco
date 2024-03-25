package br.com.cdb.bancodigital.entity;

public class CartaoDebito extends Cartao {

	public CartaoDebito(Cliente c, int numeroCartao, String validadeMes, String validadeAno, String codigoSeguranca,
			String senhaCartao) {
		super(c, numeroCartao, validadeMes, validadeAno, codigoSeguranca, senhaCartao);
		
	}
	public void pagamento(double valor) {
			if (dono.suasContas.get(0).saldo >= valor) {
				dono.suasContas.get(0).setSaldo(dono.suasContas.get(0).saldo -= valor); ;
		}

	}
}
