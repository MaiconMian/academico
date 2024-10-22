package controle;
import java.util.*;

import conexaoSQL.DAO.*;
import modelos.Produto;

/**
 * Classe Loja criada pelo princípio GRASP do controlador, visa fazer a conexao
 * com a interface e o restante da aplicação.
 *
 * Fornece metodos para verificar, remover, adicionar e modificar itens presentes na loja.
 */
public class Loja {
    // lista de produtos que a loja possui
    private List<Produto> produtos;
    // nome da loja
    private String nomeLoja;
    // Design Patter: Singleton
    private static Loja loja;

    private Loja(){}

    private Loja(String nome){
        nomeLoja = nome;
    }

    /**
     * Obtém o objeto da loja atualmente instânciado.
     *
     * @return a loja atualmente usada.
     */
    public static Loja getLoja (){
        // caso nenhuma loja foi iniciada
        if (loja == null){
            loja = new Loja("Blue Velvet Music Store");
        }
        return loja;
    }

    /**
     * le todos os produtos da tabela do banco de dados e guarda na Lista.
     */
    public void lerProdutos(){
        // cria um produtoDAO para conexao
        ProdutoDAO pdao = new ProdutoDAO();
        // inicia novamente a lista
        produtos = new LinkedList<>();
        // a lista recebe os produtos lidos
        produtos = pdao.read(produtos);
    }

    /**
     * Retorna a lista com os produtos atuais da loja
     *
     * @return lista com todos os protdutos da loja
     */
    public List<Produto> getProdutos(){
        // le os protudos
        lerProdutos();
        // ordena a lista por ordem alfabetica
        Collections.sort(produtos);
        return produtos;
    }

    /**
     * Cria um produto e o insere no banco de dados com base nas informações fornecidas
     *
     * @param nome do produto a ser inserido
     * @param descricao do produto a ser inserido
     * @param categoria do produto a ser inserido
     * @param marca do produto a ser inserido
     * @param preco do produto a ser inserido
     * @param custo do produto a ser inserido
     */
    public void criarProduto(String nome, String descricao, String categoria, String marca, double preco, double custo){
        Produto p = new Produto(nome, descricao, categoria, marca, preco, custo);
        ProdutoDAO pdao = new ProdutoDAO();
        // cria um novo produto no banco de dados
        pdao.create(p);
    }
    /**
     * Modifica um produto com base no seu ID.
     *
     * @param ID do produto a ser modificado
     * @param nome do produto a ser modificado
     * @param descricao do produto a ser modificado
     * @param categoria do produto a ser modificado
     * @param marca do produto a ser modificado
     * @param preco do produto a ser modificado
     * @param custo do produto a ser modificado
     */
    public void modificarPruduto(Long ID, String nome, String descricao, String categoria, String marca, double preco, double custo){

        ProdutoDAO pdao = new ProdutoDAO();
        Produto analisado = null;
        // busca o produto na lista de porodutos
        for (Produto p : produtos){
            if (p.getID() == ID){
                analisado = p;
                break;
            }
        }

        // quando o encontrar, modifica todos os seus atributos
        analisado.setNome(nome);
        analisado.setDescricao(descricao);
        analisado.setCategoria(categoria);
        analisado.setMarca(marca);
        analisado.setPreco(preco);
        analisado.setCusto(custo);
        // chyama o conector para o atualizar
        pdao.update(analisado);
    }

    /**
     * Remove um produto com base no seu ID.
     *
     * @param ID do produto a ser modificado
     */
    public void removerProduto(Long ID){
        ProdutoDAO pdao = new ProdutoDAO();
        Produto analisado = null;
        // busca o ID na lista de produtos
        for (Produto p : produtos){
            if (p.getID() == ID){
                analisado = p;
                break;
            }
        }
        // remove pelo ID
        pdao.delete(analisado);
    }
}
