package excecoes;

/**
 * ExcecaoNenhumProdutoSelecionado é uma exceção personalizada que é lançada
 * quando nenhum produto foi selecionado e o botão de editar ou remover
 * é apertado.
 */
public class ExcecaoNenhumProdutoSelecionado extends Exception{

    /**
     * Construtor da classe ExcecaoNenhumProdutoSelecionado.
     *
     * @param mensagem a mensagem de erro.
     */
    public ExcecaoNenhumProdutoSelecionado (String mensagem){
        super(mensagem);
    }
}
