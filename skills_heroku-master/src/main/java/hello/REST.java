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
		        		jsonObj.put("resolvido", chamado.isResolvido());
		        		
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
	
	post("/alterado", new Route() {
		@Override
        public Object handle(final Request request, final Response response){
        	
           response.header("Access-Control-Allow-Origin", "*");

        	
        	
        	
           JSONObject json = new JSONObject(request.body());
        	
           String idChamado = json.getString("idChamado");
           String nomeChamado = json.getString("nomeChamado");
           String descricaoChamado = json.getString("descricaoChamado");
           String resolvidoChamado = json.getString("chamadoResolvido");
           boolean resolvido = (Integer.parseInt(resolvidoChamado) != 0);
     	    
           /*try {
        	  
        		} catch (JSONException e) {
        				
        			//e.printStackTrace();

        		}*/
     	   
           boolean alterado = model.editarChamado(Integer.parseInt(idChamado),nomeChamado,descricaoChamado,resolvido);
    	   JSONArray jsonResult = new JSONArray();
   	       JSONObject jsonObj = new JSONObject();
   	    
   	       jsonObj.put("alterado", alterado);
  		
   	       jsonResult.put(jsonObj);
       	
   	       return jsonResult;
        	
	   }
	});     

     
}
	
	
	
}
