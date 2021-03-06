package hello;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model{
	
	ObjectContainer usuarios = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuarios.db4o");
	ObjectContainer chamados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/chamados.db4o");
	
	//M�todos de exclus�o para teste est�tico
	public void excluirTodosChamados(){
		for(Chamado chamado: consultarTodosChamados()) chamados.delete(chamado);
	}
	
	public void excluirTodosUsuarios(){
		for(Usuario user: consultarTodosUsuarios()) usuarios.delete(user);
	}
	//End m�todos de teste est�tico
	
	private List<Usuario> consultarTodosUsuarios(){
		List<Usuario> allUsuarios = new LinkedList<>();
		Query query = usuarios.query();
		query.constrain(Usuario.class);
	    ObjectSet<Usuario> todosUsuarios = query.execute();
	    for(Usuario usuario: todosUsuarios) allUsuarios.add(usuario);
	    return allUsuarios;
	}
	
	public List<Chamado> consultarTodosChamados(){
		List<Chamado> allChamados = new LinkedList<>();
		Query query = chamados.query();
		query.constrain(Chamado.class);
		ObjectSet<Chamado> todosChamados = query.execute();
		for(Chamado chamado: todosChamados) allChamados.add(chamado);
		return allChamados;
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
	    	if(user.getUsuarioNome().equals(email) && user.getUsuarioSenha().equals(senha)) return user;
	    }
	    return null;
	}
	
	public boolean darUsuarioAcesso(String email){
		Usuario user = pesquisarUsuarioNome(email);
		if (user == null) return false;
		user.setUsuarioAcesso(1);
		usuarios.store(user);
		usuarios.commit();
		return true;
	}
	
	public List<Chamado> consultarChamadosData(){
		List<Chamado> a = new LinkedList<>();
		a = consultarTodosChamados();
		a.sort(null);
		return a;
	}
	
	public List<Chamado> consultarChamadosPrioridade(){
		List<Chamado> p3 = new ArrayList();
		List<Chamado> p2 = new ArrayList();
		List<Chamado> p1 = new ArrayList();
		for(Chamado chamado: consultarTodosChamados()){
			if(chamado.getChamadoPrioridade() == 3) p3.add(chamado);
			else if(chamado.getChamadoPrioridade() == 2) p2.add(chamado);
			else if(chamado.getChamadoPrioridade() == 1) p1.add(chamado);
		}
		p3.sort(null);
		p2.sort(null);
		p1.sort(null);
		p3.addAll(p2);
		p3.addAll(p1);
		return p3;
	}
	
	
	
}
