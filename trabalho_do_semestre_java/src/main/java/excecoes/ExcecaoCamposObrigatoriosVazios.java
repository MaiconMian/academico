package excecoes;

/**
 * ExcecaoCamposObrigatoriosVazios é uma exceção personalizada que é lançada
 * quando campos obrigatórios estão vazios.
 */
public class ExcecaoCamposObrigatoriosVazios extends Exception{
    /**
     * Construtor da classe ExcecaoCamposObrigatoriosVazios.
     *
     * @param mensagem a mensagem de erro.
     */
    public ExcecaoCamposObrigatoriosVazios (String mensagem){
        super(mensagem);
    }

}
