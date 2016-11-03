package hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		try {
			Date chamado1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.getChamadoData());
			Date chamado2 = new SimpleDateFormat("dd/MM/yyyy").parse(o.getChamadoData());
			if(chamado1.compareTo(chamado2) == 0 && this.getChamadoPrioridade() > o.getChamadoPrioridade()) return -1;
			if(chamado1.compareTo(chamado2) == 0 && this.getChamadoPrioridade() < o.getChamadoPrioridade()) return 1;
			return chamado1.compareTo(chamado2);
					
		} catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}
	
	
	
}

