package hello;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Chamado implements Comparable <Chamado> {
	int chamadoId;
	int chamadoPrioridade;
	String chamadoNome;
	String chamadoDescricao;
	String chamadoData;
	String chamadoAutor;
	boolean chamadoResolvido;
	
	public Chamado(String chamadoNome, String chamadoDescricao, String chamadoData) {
		this.chamadoNome = chamadoNome;
		this.chamadoDescricao = chamadoDescricao;
		this.chamadoData = chamadoData;
	}

	@Override
	public int compareTo(Chamado o) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate chamado1 = LocalDate.parse(this.getChamadoData(), formatter);
		LocalDate chamado2 = LocalDate.parse(o.getChamadoData(), formatter);
		if(chamado1.compareTo(chamado2) == 0 && this.getChamadoPrioridade() > o.getChamadoPrioridade()) return -1;
		if(chamado1.compareTo(chamado2) == 0 && this.getChamadoPrioridade() < o.getChamadoPrioridade()) return 1;
		return chamado1.compareTo(chamado2);	
	}
	
	
	
}

