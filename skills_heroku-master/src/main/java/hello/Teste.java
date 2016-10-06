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
    }
	
	public static void initialize (){
		model.addUser(new User("teste@teste.com", "123456"));
		model.addUser(new User("willian@teste.com", "123456"));
		model.addUser(new User("novo@teste.com", "abc"));
		
		model.addChamado(new Chamado("Teste Chamado", "Esse é um teste do chamado", "17/09/2016"));
	}
}
