package tela;

import excecoes.ExcecaoCamposObrigatoriosVazios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import controle.*;
import java.lang.Exception;

/**
 * Classe PagCadastroControlador é responsável por controlar a tela de cadastro de produtos.
 */
public class PagCadastroControlador {
    // atributos de um produto
    @FXML
    private TextField textProdutoCadastro;
    @FXML
    private TextField textPrecoCadastro;
    @FXML
    private TextField textCustoCadastro;
    @FXML
    private TextField textCategoriaCadastro;
    @FXML
    private TextField textDescricaoCadastro;
    @FXML
    private TextField textMarcaCadastro;


    /**
     * Método invocado para ir a tela inicial.
     */
    @FXML
    protected void changeScreenToMenu() {
        Main.chanceScreen("pagInicial", null);
    }

    /**
     * Metodo para limpar todos os atributos ao trocar de tela
     */
    protected void clean(){
        textProdutoCadastro.setText(null);
        textPrecoCadastro.setText(null);
        textCustoCadastro.setText(null);
        textCategoriaCadastro.setText(null);
        textMarcaCadastro.setText(null);
        textDescricaoCadastro.setText(null);
    }

    /**
     * Método invocado quando o botao cadastrar um produto é acionado.
     */
    @FXML
    protected void buttonCadastrar() {

        try{
            // pega e inicia as variaveis do produto
            String produto = textProdutoCadastro.getText();
            String preco = textPrecoCadastro.getText();
            String custo = textCustoCadastro.getText();
            String marca = textMarcaCadastro.getText();
            String categoria = textCategoriaCadastro.getText();
            String descricao = textDescricaoCadastro.getText();

            // verifica se os campos obrigatorios estao preenchidos
            if (produto == null || produto.trim().isEmpty() || preco == null || preco.trim().isEmpty()){
                throw new ExcecaoCamposObrigatoriosVazios("Campos obriatórios não preenchidos");
            }

            // converte os tipos
            double custo_, preco_;
            if (custo != null){
                custo_ = Double.parseDouble(custo);
            } else {
                custo_ = 0.0;
            }
            if (preco != null){
                preco_ = Double.parseDouble(preco);
            } else {
                preco_ = 0.0;
            }
            // cria o produto e volta a tela inicial
            Loja loja = Loja.getLoja();
            loja.criarProduto(produto, descricao, categoria, marca, preco_, custo_);
            Main.chanceScreen("pagInicial", null);

        } catch (ExcecaoCamposObrigatoriosVazios e){
            // mostra mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText("Campos obrigatórios não preenchidos");
            alert.setContentText("Por favor, preencha os campos Produto e Preço.");
            alert.showAndWait();
        }

    }
}