package controle;

import javax.swing.JOptionPane;

import beans.Produto;
import dados.BDDados;

public class ControleProduto {

//	Vari�vel para auxiliar no JOptionPane.showOptionDialog
	private Object[] sim_nao = {"SIM", "N�O"};
	
//	M�todo que verifica se nome do produto da mesma marca j� est� sendo utilizado
	public boolean ProdutoExiste(Produto produto) {
//		Vari�vel que retorna no final do m�todo
		boolean existe = false;
		
//		Loop que percorre todo o array de produtos
		for (int i = 0; i < BDDados.arrayProdutos.size(); i++) {
			if (produto.getNome().equals(BDDados.arrayProdutos.get(i).getNome()) && produto.getMarca().equals(BDDados.arrayProdutos.get(i).getMarca())) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	public void CadastrarProduto(Produto produto) {
		
//		Se o produto de tal marca ainda n�o foi cadastrado, ent�o pode cadastrar
		if (ProdutoExiste(produto) == false) {
			BDDados.arrayProdutos.add(produto);
		}else {
			JOptionPane.showMessageDialog(null, "O produto \""+produto.getNome()+"\" da marca \""+produto.getMarca()+"\" j� existe!", "Erro ao cadastrar!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void AlterarProduto(int codigo, Produto produto, String nomeProdutoAntigo, String marcaProdutoAntigo) {
		
//		Se o usu�rio n�o alterou o nome ou a marca do produto ent�o pode confirmar a altera��o
//		Se o o usu�rio alterou seu nome ou a marca ent�o verifica se j� est� sendo utilizado o novo nome
		if ((nomeProdutoAntigo.equals(produto.getNome()) && marcaProdutoAntigo.equals(produto.getMarca())) || (ProdutoExiste(produto) == false)) {
			BDDados.arrayProdutos.set(codigo, produto);	
		}else {
			JOptionPane.showMessageDialog(null, "O produto \""+produto.getNome()+"\" da marca \""+produto.getMarca()+"\" j� existe!", "Erro ao alterar!", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void ExcluirProduto(int codigo) {
		
		if (JOptionPane.showOptionDialog(null, "Deseja realmente excluir o produto?", "Excluir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, sim_nao, null) == 0) {
			BDDados.arrayProdutos.remove(codigo);
		}
	}
}
