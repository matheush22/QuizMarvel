package br.com.quizmarvel.bns;

public class Jogador {

	private String nome;
	private String categoria;
	private double maiorPontuacao;
	private double ultimaPontuacao;

	public Jogador() {
	}

	public Jogador(String nome, String categoria, double maiorPontuacao, double ultimaPontuacao) {
		this.nome = nome;
		this.categoria = categoria;
		this.maiorPontuacao = maiorPontuacao;
		this.ultimaPontuacao = ultimaPontuacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCipo(String categoria) {
		this.categoria = categoria;
	}

	public double getMaiorPontuacao() {
		return maiorPontuacao;
	}

	public void setMaiorPontuacao(double maiorPontuacao) {
		this.maiorPontuacao = maiorPontuacao;
	}

	public double getUltimaPontuacao() {
		return ultimaPontuacao;
	}

	public void setUltimaPontuacao(double ultimaPontuacao) {
		this.ultimaPontuacao = ultimaPontuacao;
	}

}

