package tela;

import controle.Loja;
import excecoes.ExcecaoNenhumProdutoSelecionado;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import modelos.Produto;
import java.util.List;
import java.util.ArrayList;
/**
 * Controlador responsável por gerenciar a interface da tela inicial do sistema.
 */
public class PagInicialControlador {

    @FXML
    public TableView<Produto> tvProdutos; // tabela para exibicao dos produtos
    @FXML
    private TextField txtPesquisa;
    private static final int ITEMS_PER_PAGE = 10; // guarda o numero de item com pagina como 10
    @FXML
    private Pagination pagination;
    private List<Produto> produtosFiltrados;

    /**
     * Método chamado ao clicar no botão "Remover Produto Selecionado".
     * Remove o produto selecionado na tabela.
     */
    @FXML
    protected void removerProdutoSelecionado(){
        try{
            if (tvProdutos.getSelectionModel().getSelectedItem() != null){
                Loja loja = Loja.getLoja();
                Produto produtoSelecionado = tvProdutos.getSelectionModel().getSelectedItem();
                loja.removerProduto(produtoSelecionado.getID());
                atualiza();
            } else {
                throw new ExcecaoNenhumProdutoSelecionado("Nenhum produto foi selecionado");
            }

        } catch (ExcecaoNenhumProdutoSelecionado ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro: " + ex.getMessage());
            alert.setContentText("Por favor, selecione algum item para remover.");
            alert.showAndWait();
        }

    }
    /**
     * Método inicializado ao carregar a interface.
     * Configura as colunas da tabela e inicializa a paginação.
     */
    @FXML
    protected void initialize() {

        // configura as colunas da tabela
        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Produto, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));

        TableColumn<Produto, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMarca()));

        TableColumn<Produto, String> colCategoria = new TableColumn<>("Categoria");
        colCategoria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategoria()));

        TableColumn<Produto, Double> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPreco()).asObject());

        TableColumn<Produto, Double> colCusto = new TableColumn<>("Custo");
        colCusto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getCusto()).asObject());

        //adiciona
        tvProdutos.getColumns().addAll(colNome, colDescricao, colMarca, colCategoria, colPreco, colCusto);

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> atualizaTabela(newIndex.intValue()));
        atualiza();
        atualizaTabela(0);
    }

    /**
     * Método para atualizar a exibição da tabela de produtos.
     */
    private void atualizaTabela(int pageIndex) {
        int start = pageIndex * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, produtosFiltrados.size());
        if (start <= end) {
            tvProdutos.getItems().setAll(produtosFiltrados.subList(start, end));
        }
    }

    /**
     * Método para atualizar a lista de produtos exibidos na tela.
     * Utiliza a instância da classe Loja para obter os produtos.
     */
    public void atualiza() {
        Loja loja = Loja.getLoja();
        produtosFiltrados = loja.getProdutos();
        atualizaPaginacao();
    }

    /**
     * Método para atualizar a paginação de acordo com os produtos filtrados.
     */
    private void atualizaPaginacao() {
        // Configura a paginação
        int pageCount = (int) Math.ceil((double) produtosFiltrados.size() / ITEMS_PER_PAGE);
        pagination.setPageCount(pageCount);
        // Garante que a página atual não ultrapasse o número total de páginas
        if (pagination.getCurrentPageIndex() >= pageCount) {
            pagination.setCurrentPageIndex(pageCount - 1);
        } else {
            atualizaTabela(pagination.getCurrentPageIndex());
        }
    }

    /**
     * Método chamado ao realizar uma pesquisa de produtos pelo nome.
     * Atualiza a lista de produtos exibidos de acordo com o resultado da pesquisa.
     */
    @FXML
    public void pesquisa() {
        String nomeProduto = txtPesquisa.getText();
        if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
            atualiza();
        } else {
            Loja loja = Loja.getLoja();
            List<Produto> produtos = loja.getProdutos();
            produtosFiltrados = new ArrayList<>();

            // busca dentre todos os produtos quais tem o nome filtrado
            String nomeProdutoMinusculo = nomeProduto.toLowerCase();
            for (Produto p : produtos) {
                String nomeProdutoTabela = p.getNome().toLowerCase();
                if (nomeProdutoTabela.contains(nomeProdutoMinusculo)) {
                    produtosFiltrados.add(p);
                }
            }

            // Atualiza a paginação para os produtos filtrados
            atualizaPaginacao();
        }
    }
    @FXML
    /**
     * Método chamado ao clicar no botão "Cadastrar Produto".
     * Altera a tela para a tela de cadastro de produtos.
     */
    protected void changeScreenToCadastrar(){
        Main.chanceScreen("pagCadastro",null );
    }
    @FXML
    /**
     * Método chamado ao clicar no botão "Editar Produto".
     * Verifica se um produto está selecionado na tabela e altera a tela para a tela de edição desse produto.
     */
    protected void changeScreenToEdicao(){
        try{
            if(tvProdutos.getSelectionModel().getSelectedItem() != null){
                Produto produtoSelecionado = tvProdutos.getSelectionModel().getSelectedItem();
                // chama a tela com o produto selecionado
                Main.chanceScreen("pagEdicao", produtoSelecionado);
            } else {
                throw new ExcecaoNenhumProdutoSelecionado("Nenhum Produto Selecionado");
            }
        } catch (ExcecaoNenhumProdutoSelecionado ex) {
            // mostra mensagem de erro que nenhum produto foi selecionado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro: " + ex.getMessage());
            alert.setContentText("Por favor, selecione algum item para editar.");
            alert.showAndWait();
        }
    }
}