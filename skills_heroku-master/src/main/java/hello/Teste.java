package hello;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

public class Teste {
	
	final static Model model = new Model();
	
	public static void main(String[] args) {

		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

		
        
        initialize();
		
        
		
		staticFileLocation("/static");
		
		REST controller = new REST(model); 
		
		
		controller.getLogin();
		controller.pesquisarChamado();
		controller.alterarChamado();
		controller.darPermissaoADM();
		controller.listarChamados();
    }
	
	public static void initialize (){
		//Vari�veis sendo inicializadas para teste est�tico
		model.excluirTodosChamados();
		model.excluirTodosUsuarios();
		model.adicioarUsuario(new Usuario("teste@teste.com", "123456"));
		model.adicioarUsuario(new Usuario("willian@teste.com", "123456"));
		model.adicioarUsuario(new Usuario("novo@teste.com", "abc"));
		
		model.adicionarChamado(new Chamado("Teste Chamado", "Esse � um teste do chamado", "17/09/2016",2));
		model.adicionarChamado(new Chamado("Novo Teste", "That's a new test folks", "03/02/2016",3));
	}
}
