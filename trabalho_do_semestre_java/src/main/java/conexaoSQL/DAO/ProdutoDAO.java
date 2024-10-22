package conexaoSQL.DAO;

import java.sql.*;
import java.util.*;

import conexaoSQL.FabricaConexao;
import modelos.*;

/**
 * Classe ProdutoDAO responsável por gerenciar as operações de acesso a dados
 * do Produto no banco de dados MySQL.
 *
 * Esta classe fornece métodos para criar, ler, atualizar e deletar registros
 * da tabela PRODUTOS presente no banco de dados.
 */
public class ProdutoDAO {

    /**
     * Insere um novo produto na tabela PRODUTOS.
     *
     * @param p o objeto Produto a ser inserido.
     */
    public void create(Produto p){
        // cria uma conexao com o banco de dados
        Connection con = FabricaConexao.getConection();
        // cria uma conexao para editar o banco de dados
        PreparedStatement stmt = null;

        try {
            // atribui os atributos do produto a ser inserido
            stmt = con.prepareStatement("INSERT PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getMarca());
            stmt.setString(4, p.getCategoria());
            stmt.setDouble(5, p.getPreco());
            stmt.setDouble(6, p.getCusto());
            // sexeuta
            stmt.executeUpdate();
        } catch (SQLException e) {
            FabricaConexao.trataProblemaBancoDados(e);
        } finally {
            //fecha a conexao criada
            FabricaConexao.closeConnection(con, stmt);
        }
    }

    /**
     * Le toda a tabela do banco de dados
     *
     * @param produtos a lista com os produtos á ser preenchida.
     * @return a lista de produtos preenchida com os registros da tabela PRODUTOS.
     */
    public List<Produto> read (List<Produto> produtos){

        // cria conexoes necesarias
        Connection con = FabricaConexao.getConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            stmt = con.prepareStatement("SELECT * FROM PRODUTOS");
            rs = stmt.executeQuery();

            //enquanto existirem produtos no banco de dados

            while(rs.next()){
                Produto produto = new Produto();
                produto.setID(rs.getLong("PRODUTOS_ID"));
                produto.setNome(rs.getString("NOME"));
                produto.setDescricao(rs.getString("DESCRICAO"));
                produto.setMarca(rs.getString("MARCA"));
                produto.setCategoria(rs.getString("CATEGORIA"));
                produto.setPreco(rs.getDouble("PRECO"));
                produto.setCusto(rs.getDouble("CUSTO"));
                produtos.add(produto); // insere o produto na lista
            }
        } catch (SQLException e) {
            FabricaConexao.trataProblemaBancoDados(e);
        } finally {
            //fecha a conecao
            FabricaConexao.closeConnection(con, stmt, rs);
        }

        return produtos;
    }

    /**
     * Atualiza um produto no banco de dados
     *
     * @param p o produto a ter seus dados atualizados.
     */
    public void update( Produto p ){
        // cria conexao
        Connection con = FabricaConexao.getConection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE PRODUTOS SET NOME = ?, DESCRICAO = ?, MARCA = ?, CATEGORIA = ?, PRECO = ?, CUSTO = ? WHERE PRODUTOS_ID = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getMarca());
            stmt.setString(4, p.getCategoria());
            stmt.setDouble(5, p.getPreco());
            stmt.setDouble(6, p.getCusto());
            stmt.setLong(7, p.getID()); // modifica o objeto pelo ID dele
            stmt.executeUpdate();
        } catch (SQLException e) {
            FabricaConexao.trataProblemaBancoDados(e);
        } finally {
            //fecha a conexao
            FabricaConexao.closeConnection(con, stmt);
        }
    }

    /**
     * Remove um produto no banco de dados
     *
     * @param p o produto a ser removido.
     */
    public void delete( Produto p ){
        // cria conexao
        Connection con = FabricaConexao.getConection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM PRODUTOS WHERE PRODUTOS_ID = ?");
            stmt.setLong(1, p.getID()); // deleta pelo ID do produto
            stmt.executeUpdate();

        } catch (SQLException e) {
            FabricaConexao.trataProblemaBancoDados(e);
        } finally {
            FabricaConexao.closeConnection(con, stmt);
        }
    }
}
