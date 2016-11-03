package hello;

import static spark.Spark.*;

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
    }
	
	public static void initialize (){
		//Variáveis sendo inicializadas para teste estático
/*		model.excluirTodosChamados();
		model.excluirTodosUsuarios();*/
		model.adicioarUsuario(new Usuario("teste@teste.com", "123456"));
		model.adicioarUsuario(new Usuario("willian@teste.com", "123456"));
		model.adicioarUsuario(new Usuario("novo@teste.com", "abc"));
		
		model.adicionarChamado(new Chamado("Teste Chamado", "Esse é um teste do chamado", "17/09/2016"));
		model.adicionarChamado(new Chamado("Novo Teste", "That's a new test folks", "03/11/2016"));
	}
}
