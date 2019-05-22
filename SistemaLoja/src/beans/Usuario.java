package beans;

public class Usuario {
	
	private String nome;
	private String senha;
	private boolean admin;
	private String[][] produtoComprado = new String [1000][3]; //coluna 1 = produto comprado, coluna 2 = quantidade, coluna 3 = preço da unidade do produto
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String[][] getProdutoComprado() {
		return produtoComprado;
	}
	public void setProdutoComprado(String[][] produtoComprado) {
		this.produtoComprado = produtoComprado;
	}
	
}
