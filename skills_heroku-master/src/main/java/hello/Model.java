package hello;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model{
	
	ObjectContainer users = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/users.db4o");
	ObjectContainer chamados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/chamados.db4o");
	
	
	public List<User> consultarTodosUsuarios(){
		Query query = users.query();
		query.constrain(User.class);
	    ObjectSet<User> allUsers = query.execute();
	    return allUsers;
	}
	
	public List<Chamado> consultarTodosChamados(){
		Query query = chamados.query();
		query.constrain(Chamado.class);
		ObjectSet<Chamado> allChamados = query.execute();
		return allChamados;
	}
	
	public boolean isFirstUser(){
		return consultarTodosUsuarios().size() == 0;
	}
	
	public void addUser(User user){
		if (isFirstUser()) user.setAcesso(1);
		else user.setAcesso(0);
		users.store(user);
	}
	
	public void addChamado(Chamado chamado){
		chamado.setResolvido(false);
		chamado.setId(consultarTodosChamados().size());
		chamados.store(chamado);
	}
	
	public Chamado pesquisarChamadoId(Integer id){
		for(Chamado chamado: consultarTodosChamados()){
			if(chamado.getId() == id) return chamado;
		}
		return null;
	}
	
	public boolean editarChamado(Integer id, String nome, String descricao, boolean resolvido){
		Chamado chamado = pesquisarChamadoId(id);
		if (chamado == null) return false;
		chamado.setNome(nome);
		chamado.setDescricao(descricao);
		chamado.setResolvido(resolvido);
		chamados.store(chamado);
		return true;
	}
	
	public User login(String email, String senha){
	    for(User user: consultarTodosUsuarios()){
	    	if(user.getUserName().equals(email) && user.getPassword().equals(senha)) return user;
	    }
	    return null;
	}
	
}
