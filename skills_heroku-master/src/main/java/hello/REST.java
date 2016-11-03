package hello;



import static spark.Spark.get;
import static spark.Spark.post;


import java.io.UnsupportedEncodingException;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;


public class REST{
	
	private Model model;
	
	
	public REST(Model store){
		this.model = store;
	}
	
	
	public void getLogin(){
		
		get("/login/:username/:password", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            
	            
	            try {
	            	Usuario user = model.login(request.params(":username"), request.params(":password"));
	            	
	            	if(user != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();
		         	    
		         	    jsonObj.put("acesso", user.getUsuarioAcesso());
		        				        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();

        		jsonObj.put("acesso", "");
        		
        		
             	jsonResult.put(jsonObj);
             	
             	return jsonResult;
	            
	     	     
	         }
	         
	      });
			

	}
	
	public void pesquisarChamado(){
		
		post("/pesquisarchamadoid", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	           response.header("Access-Control-Allow-Origin", "*");

	        	
	        	
	        	
	           JSONObject json = new JSONObject(request.body());
	        	
	           String idChamado = json.getString("idChamado");
         	    
	           try {
	        	   Chamado chamado = model.pesquisarChamadoId(Integer.parseInt(idChamado));
	            	
	            	if(chamado != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();

		        		jsonObj.put("chamadoNome", chamado.getChamadoNome());
		        		jsonObj.put("chamadoDescricao", chamado.getChamadoDescricao());
		        		if(chamado.isChamadoResolvido())	jsonObj.put("chamadoResolvido", "1");
		        		else						jsonObj.put("chamadoResolvido", "0");
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
        	    JSONObject jsonObj = new JSONObject();

        	    jsonObj.put("chamadoNome", "");
        		jsonObj.put("chamadoDescricao", "");
        		jsonObj.put("chamadoResolvido", "");
       		
       		
            	jsonResult.put(jsonObj);
            	
            	return jsonResult;
         	   
         	   
	        	
		   }
		});     

         
	}
	
	public void alterarChamado(){
		
		get("/alterado/:idChamado/:nomeChamado/:descricaoChamado/:resolvidoChamado", new Route() {
			@Override
	        public Object handle(final Request request, final Response response){
	        	
	        	 
	        	 
	        	response.header("Access-Control-Allow-Origin", "*");
	        	 
	            
	            
	            try {
	            	char resolvido = request.params(":resolvidoChamado").charAt(0);
	            	boolean alterado = model.editarChamado(request.params(":idChamado"), request.params(":nomeChamado"), request.params(":descricaoChamado"), resolvido);
	            	
	            	//if(user != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();
		         	    
		         	    jsonObj.put("alterado", alterado);
		        				        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	//} else {
	            		
	            		
	            		
	            	//}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();
	
	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
	     	    JSONObject jsonObj = new JSONObject();
	
	    		jsonObj.put("alterado", false);
	    		
	    		
	         	jsonResult.put(jsonObj);
	         	
	         	return jsonResult;
	            
	     	     
	         }
	         
	      });
	
	     
	}

	public void darPermissaoADM(){
		get("/darpermissao/:username", new Route() {
			@Override
	        public Object handle(final Request request, final Response response){
	        	
	           response.header("Access-Control-Allow-Origin", "*");
	   	    
	           try {
	        	   boolean resultado = model.darUsuarioAcesso(request.params(":username"));
	            	
	            	if(resultado){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();
	
		        		jsonObj.put("resultado", resultado);
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();
	
	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
	    	    JSONObject jsonObj = new JSONObject();
	
	    	    jsonObj.put("resultado", false);
	
	   		
	        	jsonResult.put(jsonObj);
	        	
	        	return jsonResult;
	     	   
	     	   
	        	
		   }
		});     
	
	}
	
	public void listarChamados(){
		get("/listarChamados", new Route(){
			
			public Object handle(final Request request, final Response response){
				response.header("Access-Control-Allow-Origin", "*");
				
				JSONArray jsonArray = new JSONArray();
				
				try{
					List<Chamado> chamados = model.consultarChamadosData();
					
					for(Chamado chamado : chamados){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("numeroChamado", chamado.getChamadoId());
						jsonObject.put("nomeUsuarioChamado", chamado.getChamadoAutor());
						jsonObject.put("descricaoChamado", chamado.getChamadoDescricao());
						jsonObject.put("dataChamado", chamado.getChamadoData());
						jsonObject.put("situacaoChamado", chamado.isChamadoResolvido());
						jsonArray.put(jsonObject);
					}
					return jsonArray;
					
				}catch(JSONException e){
					e.printStackTrace();
				}
				
				return null;
			}
		});
	}
}
