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
	
	//Métodos de exclusão para teste estático
	public void excluirAllChamados(){
		for(Chamado chamado: consultarTodosChamados()) chamados.delete(chamado);
	}
	
	public void excluirAllUsers(){
		for(User user: consultarTodosUsuarios()) users.delete(user);
	}
	//End métodos de teste estático
	
	private List<User> consultarTodosUsuarios(){
		Query query = users.query();
		query.constrain(User.class);
	    ObjectSet<User> allUsers = query.execute();
	    return allUsers;
	}
	
	private List<Chamado> consultarTodosChamados(){
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
	
	public User pesquisarUsuarioUsername(String username){
		for(User user: consultarTodosUsuarios()){
			if(user.getUserName().equals(username)) return user;
		}
		return null;
	}
	
	public boolean editarChamado(String id, String nome, String descricao, char resolvido){
		Chamado chamado = pesquisarChamadoId(Integer.parseInt(id));
		if (chamado == null) return false;
		chamado.setId(Integer.parseInt(id));
		chamado.setNome(nome);
		chamado.setDescricao(descricao);
		chamado.setResolvido(resolvido == '1');
		chamados.store(chamado);
		chamados.commit();
		return true;
	}
	
	public User login(String email, String senha){
	    for(User user: consultarTodosUsuarios()){
	    	if(user.getUserName().equals(email) && user.getPassword().equals(senha)) return user;
	    }
	    return null;
	}
	
	public boolean darPermissao(String email){
		User user = pesquisarUsuarioUsername(email);
		if (user == null) return false;
		user.setAcesso(1);
		users.store(user);
		users.commit();
		return true;
	}
	
}
