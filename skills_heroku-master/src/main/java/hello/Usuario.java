package hello;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Usuario {
	
	private String usuarioNome;
	private String usuarioSenha;
	private int usuarioAcesso;
	
	
	
	public Usuario(String usuarioNome, String usuarioSenha) {
		this.usuarioNome = usuarioNome;
		this.usuarioSenha = usuarioSenha;
	}
	
	
	
	
	
}
