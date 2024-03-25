package br.com.cdb.bancodigital.entity;

public class Cartao {

	protected Cliente dono;
	private int numeroCartao;
	private String validadeMes;
	private String validadeAno;
	private String codigoSeguranca;
	private String senhaCartao;

	

	public Cartao(Cliente c, int numeroCartao, String validadeMes, String validadeAno, String codigoSeguranca,
			String senhaCartao) {
		this.dono = c;
		this.numeroCartao = numeroCartao;
		this.validadeMes = validadeMes;
		this.validadeAno = validadeAno;
		this.codigoSeguranca = codigoSeguranca;
		this.senhaCartao = senhaCartao;
	}

	public int getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getValidadeMes() {
		return validadeMes;
	}

	public void setValidadeMes(String validadeMes) {
		this.validadeMes = validadeMes;
	}

	public String getValidadeAno() {
		return validadeAno;
	}

	public void setValidadeAno(String validadeAno) {
		this.validadeAno = validadeAno;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getSenhaCartao() {
		return senhaCartao;
	}

	public void setSenhaCartao(String SenhaCartao) {
		this.senhaCartao = SenhaCartao;
	}

	public void alterarSenha(String senhaAtual, String novaSenha, String confirmarNovaSenha) {

		if (!senhaAtual.equals(getSenhaCartao())) {
			throw new IllegalArgumentException("Senha atual incorreta!");
		}

		if (!validarSenha(novaSenha)) {
			throw new IllegalArgumentException("Senha inválida! A senha deve ter 6 dígitos numéricos.");
		}

		if (!novaSenha.equals(confirmarNovaSenha)) {
			throw new IllegalArgumentException("Senhas não coincidem!");
		}

		setSenhaCartao(novaSenha);
	}

	private boolean validarSenha(String senha) {
		return senha.matches(" ");
	}

}
