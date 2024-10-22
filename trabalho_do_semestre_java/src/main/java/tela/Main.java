package tela;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import modelos.Produto;

/**
 * A classe Main é onde inicia o JavaFX.
 * E tambem esponsavel por inicializar e trocar entre as cenas da aplicação.
 */
public class Main extends Application {
    private static Stage stage;
    private static Scene pagInicialScene;
    private static Scene pagCadastroScene;
    private static Scene pagEdicaoScene;
    private static PagInicialControlador pagInicialControlador;
    private static PagEdicaoControlador pagEdicaoControlador;
    private static PagCadastroControlador pagCadastroControlador;

    /**
     * Método principal que lança a aplicação JavaFX.
     *
     * @param args os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * E o metodo resposnsavel por iniciar a tela principal
     *
     * @param primaryStage a tela principal da aplicação.
     * @throws IOException se ocorrer um erro ao carregar os arquivos FXML.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        primaryStage.setResizable(false);
        primaryStage.setTitle("Controle de Produtos");

        // carrega a tela inicial da pagina
        FXMLLoader fxmlInicial = new FXMLLoader(Main.class.getResource("PagInicial.fxml"));
        pagInicialScene = new Scene(fxmlInicial.load(), 1000, 540);
        pagInicialControlador = fxmlInicial.getController();

        // carrega a tela de cadastro da pagina
        FXMLLoader fxmlCadastro = new FXMLLoader(Main.class.getResource("PagCadastro.fxml"));
        pagCadastroScene = new Scene(fxmlCadastro.load(), 1000, 540);
        pagCadastroControlador = fxmlCadastro.getController();

        // carrega a tela de edicao da pagina
        FXMLLoader fxmlEdicao = new FXMLLoader(Main.class.getResource("PagEdicao.fxml"));
        pagEdicaoScene = new Scene(fxmlEdicao.load(), 1000, 540);
        pagEdicaoControlador = fxmlEdicao.getController();

        // inicia a tela
        primaryStage.setScene(pagInicialScene);
        primaryStage.show();
    }
    /**
     * Troca a cena atual da aplicação com base no parâmetro fornecido.
     *
     * @param scr o nome da cena para a qual trocar.
     * @param p o objeto Produto usado na cena de edição.
     */
    public static void chanceScreen(String scr, Produto p){
        switch (scr){
            case "pagInicial":
                stage.setScene(pagInicialScene);
                stage.setTitle("Controle de Produtos");
                // sempre que retorna a tela inicial, atualiza a tabela
                pagInicialControlador.atualiza();
                break;
            case "pagCadastro":
                stage.setScene(pagCadastroScene);
                stage.setTitle("Cadastro de Produtos");
                // limpa os ultimos atributos
                pagCadastroControlador.clean();
                break;
            case "pagEdicao":
                stage.setScene(pagEdicaoScene);
                stage.setTitle("Edição de Produtos");
                // tras o produto a ser editado na tela de edicao
                pagEdicaoControlador.setProdutoEditar(p);
                break;
        }
    }



}