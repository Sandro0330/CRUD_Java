package br.com.model;

/**
 * Esta é a classe User no pacote model que representa o Usuário
 * 
 * @author Sandro
 *
 */

public class User {

	protected int id;
	protected String nome;
    protected String email;
    protected String pais;

    public User() {
    	
	}
    
    public User(String nome, String email, String pais) {
    	super();
		this.nome = nome;
		this.email = email;
		this.pais = pais;
	}
    
    public User(int id, String nome, String email, String pais) {
		super();
    	this.id = id;
		this.nome = nome;
		this.email = email;
		this.pais = pais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
