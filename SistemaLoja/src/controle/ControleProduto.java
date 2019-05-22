package controle;

import javax.swing.JOptionPane;

import beans.Produto;
import dados.BDDados;

public class ControleProduto {

//	Variável para auxiliar no JOptionPane.showOptionDialog
	private Object[] sim_nao = {"SIM", "NÃO"};
	
//	Método que verifica se nome do produto da mesma marca já está sendo utilizado
	public boolean ProdutoExiste(Produto produto) {
//		Variável que retorna no final do método
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
		
//		Se o produto de tal marca ainda não foi cadastrado, então pode cadastrar
		if (ProdutoExiste(produto) == false) {
			BDDados.arrayProdutos.add(produto);
		}else {
			JOptionPane.showMessageDialog(null, "O produto \""+produto.getNome()+"\" da marca \""+produto.getMarca()+"\" já existe!", "Erro ao cadastrar!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void AlterarProduto(int codigo, Produto produto, String nomeProdutoAntigo, String marcaProdutoAntigo) {
		
//		Se o usuário não alterou o nome ou a marca do produto então pode confirmar a alteração
//		Se o o usuário alterou seu nome ou a marca então verifica se já está sendo utilizado o novo nome
		if ((nomeProdutoAntigo.equals(produto.getNome()) && marcaProdutoAntigo.equals(produto.getMarca())) || (ProdutoExiste(produto) == false)) {
			BDDados.arrayProdutos.set(codigo, produto);	
		}else {
			JOptionPane.showMessageDialog(null, "O produto \""+produto.getNome()+"\" da marca \""+produto.getMarca()+"\" já existe!", "Erro ao alterar!", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void ExcluirProduto(int codigo) {
		
		if (JOptionPane.showOptionDialog(null, "Deseja realmente excluir o produto?", "Excluir?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, sim_nao, null) == 0) {
			BDDados.arrayProdutos.remove(codigo);
		}
	}
}
