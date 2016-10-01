package hello;


import java.util.LinkedList;
import java.util.List;

public class Chamado {
	int id;
	String nome;
	String descricao;
	String data;
	boolean resolvido;
	
	public Chamado(String nome, String descricao, String data) {
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isResolvido() {
		return resolvido;
	}
	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}
	
	
}

