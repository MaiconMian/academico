package tela;

import controle.Loja;
import excecoes.ExcecaoCamposObrigatoriosVazios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import modelos.Produto;

/**
 * Classe responsável por controlar a tela de cadastro de edicao de um produto.
 */
public class PagEdicaoControlador {

    // atributos de um produto
    @FXML
    private TextField textProdutoEdicao;
    @FXML
    private TextField textPrecoEdicao;
    @FXML
    private TextField textCustoEdicao;
    @FXML
    private TextField textCategoriaEdicao;
    @FXML
    private TextField textDescricaoEdicao;
    @FXML
    private TextField textMarcaEdicao;
    private Produto produtoParaEditar;

    /**
     * Método para definir o produto a ser editado e preencher os campos de texto com seus dados.
     *
     * @param produto o produto a ser editado.
     */
    public void setProdutoEditar(Produto produto) {
        this.produtoParaEditar = produto;
        textProdutoEdicao.setText(produto.getNome());
        textPrecoEdicao.setText(String.valueOf(produto.getPreco()));
        textCustoEdicao.setText(String.valueOf(produto.getCusto()));
        textCategoriaEdicao.setText(produto.getCategoria());
        textMarcaEdicao.setText(produto.getMarca());
        textDescricaoEdicao.setText(produto.getDescricao());
    }

    @FXML
    protected void changeScreenToMenu(){
        Main.chanceScreen("pagInicial", null);
    }

    /**
     * Método chamado quando o botão "Salvar" é clicado na tela de edição.
     * Valida os campos, atualiza o produto e retorna à tela inicial.
     */
    @FXML
    protected void buttonSalvar(){
        try{
            String produto = textProdutoEdicao.getText();
            String preco = textPrecoEdicao.getText();
            String custo = textCustoEdicao.getText();
            String marca = textMarcaEdicao.getText();
            String categoria = textCategoriaEdicao.getText();
            String descricao = textDescricaoEdicao.getText();

            // verifica se apos a edicao ainda permanece com os camposobrigatorios
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
            Loja loja = Loja.getLoja();
            loja.modificarPruduto(produtoParaEditar.getID(), produto, descricao, categoria, marca, preco_, custo_);
            Main.chanceScreen("pagInicial", null);
        } catch (ExcecaoCamposObrigatoriosVazios ex){
            // mostra uma tela de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(ex.getMessage());
            alert.setHeaderText("Campos obrigatórios não preenchidos");
            alert.setContentText("Por favor, preencha os campos Produto e Preço.");
            alert.showAndWait();
        }
    }
}