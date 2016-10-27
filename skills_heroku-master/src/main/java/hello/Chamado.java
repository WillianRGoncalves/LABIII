package hello;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Chamado {
	int chamadoId;
	String chamadoNome;
	String chamadoDescricao;
	String chamadoData;
	boolean chamadoResolvido;
	
	public Chamado(String chamadoNome, String chamadoDescricao, String chamadoData) {
		this.chamadoNome = chamadoNome;
		this.chamadoDescricao = chamadoDescricao;
		this.chamadoData = chamadoData;
	}
	
	
	
}

