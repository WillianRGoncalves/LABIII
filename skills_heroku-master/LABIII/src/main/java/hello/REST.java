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
	            	User user = model.login(request.params(":username"), request.params(":password"));
	            	
	            	if(user != null){
	            		
	            		JSONArray jsonResult = new JSONArray();
		         	    JSONObject jsonObj = new JSONObject();
		         	    
		         	    jsonObj.put("acesso", user.getAcesso());
		        				        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
         	    JSONObject jsonObj = new JSONObject();

        		jsonObj.put("acesso", false);
        		
        		
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

		        		jsonObj.put("nome", chamado.getNome());
		        		jsonObj.put("descricao", chamado.getDescricao());
		        		if(chamado.isResolvido())	jsonObj.put("resolvido", "1");
		        		else						jsonObj.put("resolvido", "0");
		        		
		        		
		             	jsonResult.put(jsonObj);
		             	
		             	return jsonResult;
	            		
	            	} else {
	            		
	            		
	            		
	            	}
	            	
	            	
	             	
	        		} catch (JSONException e) {
	        				
	        			//e.printStackTrace();

	        		}
	         	    	
	
	            JSONArray jsonResult = new JSONArray();
        	    JSONObject jsonObj = new JSONObject();

        	    jsonObj.put("nome", "");
        		jsonObj.put("descricao", "");
        		jsonObj.put("resolvido", "");
       		
       		
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
            	boolean alterado = model.editarChamado(request.params(":idChamado"), request.params(":nomeChamado"), request.params(":descricaoChamado"), request.params(":resolvidoChamado"));
            	
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
	
	
	
}