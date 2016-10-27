package hello;

//import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model{
	
	ObjectContainer usuarios = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuarios.db4o");
	ObjectContainer chamados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/chamados.db4o");
	
	//Métodos de exclusão para teste estático
	public void excluirTodosChamados(){
		for(Chamado chamado: consultarTodosChamados()) chamados.delete(chamado);
	}
	
	public void excluirTodosUsuarios(){
		for(Usuario user: consultarTodosUsuarios()) usuarios.delete(user);
	}
	//End métodos de teste estático
	
	private List<Usuario> consultarTodosUsuarios(){
		Query query = usuarios.query();
		query.constrain(Usuario.class);
	    ObjectSet<Usuario> todosUsuarios = query.execute();
	    return todosUsuarios;
	}
	
	private List<Chamado> consultarTodosChamados(){
		Query query = chamados.query();
		query.constrain(Chamado.class);
		ObjectSet<Chamado> todosChamados = query.execute();
		return todosChamados;
	}
	
	public boolean ePrimeiroUsuario(){
		return consultarTodosUsuarios().size() == 0;
	}
	
	public void adicioarUsuario(Usuario usuario){
		if (ePrimeiroUsuario()) usuario.setUsuarioAcesso(1);
		else usuario.setUsuarioAcesso(0);
		usuarios.store(usuario);
	}
	
	public void adicionarChamado(Chamado chamado){
		chamado.setChamadoResolvido(false);
		chamado.setChamadoId(consultarTodosChamados().size());
		chamados.store(chamado);
	}
	
	public Chamado pesquisarChamadoId(Integer id){
		for(Chamado chamado: consultarTodosChamados()){
			if(chamado.getChamadoId() == id) return chamado;
		}
		return null;
	}
	
	public Usuario pesquisarUsuarioNome(String usuarioNome){
		for(Usuario usuario: consultarTodosUsuarios()){
			if(usuario.getUsuarioNome().equals(usuarioNome)) return usuario;
		}
		return null;
	}
	
	public boolean editarChamado(String chamadoId, String chamadoNome, String chamadoDescricao, char chamadoResolvido){
		Chamado chamado = pesquisarChamadoId(Integer.parseInt(chamadoId));
		if (chamado == null) return false;
		chamado.setChamadoId(Integer.parseInt(chamadoId));
		chamado.setChamadoNome(chamadoNome);
		chamado.setChamadoDescricao(chamadoDescricao);
		chamado.setChamadoResolvido(chamadoResolvido == '1');
		chamados.store(chamado);
		chamados.commit();
		return true;
	}
	
	public Usuario login(String email, String senha){
	    for(Usuario user: consultarTodosUsuarios()){
	    	if(user.getUsuarioNome().equals(email)) return user;
	    }
	    return null;
	}
	
	public boolean darPermissao(String email){
		Usuario user = pesquisarUsuarioNome(email);
		if (user == null) return false;
		user.setUsuarioAcesso(1);
		usuarios.store(user);
		usuarios.commit();
		return true;
	}
	
}
