package controle;

import javax.swing.JOptionPane;

import beans.Usuario;
import dados.BDDados;

public class ControleUsuario {

//	Vari�vel que armazena o nome do atual usu�rio logado, se estiver vazia n�o h� ningu�m logado
	public static String usuarioLogado = "";
//	Vari�vel que armazena e identifica se o usu�rio atual logado � admin ou n�o
	public static boolean usuarioLogadoAdmin = false;
	
//	Vari�vel para auxiliar no JOptionPane.showOptionDialog
	private Object[] sim_nao = {"SIM", "N�O"};
	
	
//	M�todo que verifica se nome do usu�rio j� est� sendo utilizado
	public boolean UsuarioExiste(Usuario usuario) {
//		Vari�vel que retorna no final do m�todo
		boolean existe = false;
		
//		Loop que percorre todo o array de usuarios
		for (int i = 0; i < BDDados.arrayUsuarios.size(); i++) {
			if (usuario.getNome().equals(BDDados.arrayUsuarios.get(i).getNome())) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	public void CadastrarUsuario(Usuario usuario) {
		
//		Se o nome informado para o cadastro ainda n�o estiver em uso, ent�o pode cadastrar
		if (UsuarioExiste(usuario) == false) {
			BDDados.arrayUsuarios.add(usuario);
		}else {
			JOptionPane.showMessageDialog(null, "Nome de usu�rio j� est� sendo utilizado,\n por favor tente outro nome!", "Erro ao cadastrar!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void AlterarUsuario(int codigo, Usuario usuario, String nomeUsuarioAntigo) {
		
//		Se o usu�rio n�o alterou seu nome ent�o pode confirmar a altera��o
//		Se o o usu�rio alterou seu nome ent�o verifica se j� est� sendo utilizado o novo nome
		if (nomeUsuarioAntigo.equals(usuario.getNome()) || UsuarioExiste(usuario) == false) {
			BDDados.arrayUsuarios.set(codigo, usuario);	
		}else {
			JOptionPane.showMessageDialog(null, "Nome de usu�rio j� est� sendo utilizado,\n por favor tente outro nome!", "Erro ao alterar!", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void LogarUsuario(Usuario usuario) {
		
		boolean usuarioCorreto = false;
		
//		Loop que verifica se o nome e a senha informada s�o v�lidos
		for (int i = 0; i < BDDados.arrayUsuarios.size(); i++) {
			if (usuario.getNome().equals(BDDados.arrayUsuarios.get(i).getNome()) && usuario.getSenha().equals(BDDados.arrayUsuarios.get(i).getSenha())) {
				usuarioLogado = BDDados.arrayUsuarios.get(i).getNome();
				
				usuarioLogadoAdmin = BDDados.arrayUsuarios.get(i).isAdmin();
				
				usuarioCorreto = true;
				break;
			}
		}
		
//		Se o nome e a senha informadas forem inv�lidas ou n�o existirem, acusa erro
		if (usuarioCorreto == false) {
			JOptionPane.showMessageDialog(null, "Nome de usu�rio ou senha incorretos ou n�o foram cadastrados!", "Erro ao logar!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void DeslogarUsuario() {
		
//		Pergunta se usu�rio realmente quer sair da conta
		if (JOptionPane.showOptionDialog(null, "Deseja realmente sair da conta?", "Deslogar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, sim_nao, null) == 0) {
			usuarioLogado = "";
			usuarioLogadoAdmin = false;
		}
		
	}
}
