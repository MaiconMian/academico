package conexaoSQL;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Classe FabricaConexao responsável por gerenciar a conexao com o banco de dados My SQL.
 */
public class FabricaConexao {

    // atributos necessários para conexao
    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/produtos_bd";
    private static final String USER = "Adm";
    private static final String PASS = "Secret_123";

    /**
     * Obtém uma conexao com o banco de dados.
     *
     * @return  uma conexao estabelecida com o banco de dados.
     * @throws RuntimeException quando a conexao nao foi possível com os dados atuais.
     */
    public static Connection getConection(){
        try {
            // busca o driver de conexao
            Class.forName(DRIVE);
            // retorna com os atributos necessários
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao:", ex);
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param con a conexão a ser fechada.
     */
    public static void closeConnection(Connection con){

        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException ex) {
            trataProblemaBancoDados(ex);
        }

    }

    /**
     * Fecha a conexão e o PreparedStatement, responsável por modificar o banco de dados
     *
     * @param con a conexão a ser fechada.
     * @param stmt o PreparedStatement a ser fechado.
     */
    public static void closeConnection(Connection con, PreparedStatement stmt){
        // chama o metodo anterior
        closeConnection(con);
        try {
            // se for diferente de nulo, fecha a conexao
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex) {
            trataProblemaBancoDados(ex);
        }

    }

    /**
     * Fecha a conexão, o PreparedStatement e o ResultSet.
     *
     * @param con a conexão a ser fechada.
     * @param stmt o PreparedStatement a ser fechado.
     * @param rs o ResultSet a ser fechado.
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        // fecha as conexoes anteriores
        closeConnection(con, stmt);
        try {
            // se existir fecha o ResultSet
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            trataProblemaBancoDados(ex);
        }

    }

    /**
     * Trata as execoes geradas pelo banco de dados
     *
     * @param ex a excecao gerada
     */
    public static void trataProblemaBancoDados(Exception ex){
        // cria uma tela de erro para mostrar que não conseguiu conectar ao banco
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro: " + ex.getMessage());
        alert.setContentText("Por favor, verifique sua conexao com o banco de dados MySQL.");
        alert.showAndWait();
    }
}
