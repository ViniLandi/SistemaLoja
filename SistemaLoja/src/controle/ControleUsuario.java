package controle;

import javax.swing.JOptionPane;

import beans.Usuario;
import dados.BDDados;

public class ControleUsuario {

//	Variável que armazena o nome do atual usuário logado, se estiver vazia não há ninguém logado
	public static String usuarioLogado = "";
//	Variável que armazena e identifica se o usuário atual logado é admin ou não
	public static boolean usuarioLogadoAdmin = false;
	
//	Variável para auxiliar no JOptionPane.showOptionDialog
	private Object[] sim_nao = {"SIM", "NÃO"};
	
	
//	Método que verifica se nome do usuário já está sendo utilizado
	public boolean UsuarioExiste(Usuario usuario) {
//		Variável que retorna no final do método
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
		
//		Se o nome informado para o cadastro ainda não estiver em uso, então pode cadastrar
		if (UsuarioExiste(usuario) == false) {
			BDDados.arrayUsuarios.add(usuario);
		}else {
			JOptionPane.showMessageDialog(null, "Nome de usuário já está sendo utilizado,\n por favor tente outro nome!", "Erro ao cadastrar!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void AlterarUsuario(int codigo, Usuario usuario, String nomeUsuarioAntigo) {
		
//		Se o usuário não alterou seu nome então pode confirmar a alteração
//		Se o o usuário alterou seu nome então verifica se já está sendo utilizado o novo nome
		if (nomeUsuarioAntigo.equals(usuario.getNome()) || UsuarioExiste(usuario) == false) {
			BDDados.arrayUsuarios.set(codigo, usuario);	
		}else {
			JOptionPane.showMessageDialog(null, "Nome de usuário já está sendo utilizado,\n por favor tente outro nome!", "Erro ao alterar!", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void LogarUsuario(Usuario usuario) {
		
		boolean usuarioCorreto = false;
		
//		Loop que verifica se o nome e a senha informada são válidos
		for (int i = 0; i < BDDados.arrayUsuarios.size(); i++) {
			if (usuario.getNome().equals(BDDados.arrayUsuarios.get(i).getNome()) && usuario.getSenha().equals(BDDados.arrayUsuarios.get(i).getSenha())) {
				usuarioLogado = BDDados.arrayUsuarios.get(i).getNome();
				
				usuarioLogadoAdmin = BDDados.arrayUsuarios.get(i).isAdmin();
				
				usuarioCorreto = true;
				break;
			}
		}
		
//		Se o nome e a senha informadas forem inválidas ou não existirem, acusa erro
		if (usuarioCorreto == false) {
			JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos ou não foram cadastrados!", "Erro ao logar!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void DeslogarUsuario() {
		
//		Pergunta se usuário realmente quer sair da conta
		if (JOptionPane.showOptionDialog(null, "Deseja realmente sair da conta?", "Deslogar?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, sim_nao, null) == 0) {
			usuarioLogado = "";
			usuarioLogadoAdmin = false;
		}
		
	}
}
