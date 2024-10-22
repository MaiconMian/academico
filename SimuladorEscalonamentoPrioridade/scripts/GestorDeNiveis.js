/**
 * Classe que representa o gestor dos níveis de prioridade, tendo n niveis de prioridade.
 * Contém o número de níveis e o vetor dos níveis.
 */
class GestorDeNiveis {

    /**
     * Construtor do objeto do tipo FilaPronto
     * 
     * @param {number} numero_niveis - guarda o numero de níveis de prioridade que a fila de pronto tem.
     * @param {NivelPriodidade} nivel - vetor que guarda os níveis de prioridade.
     */
    constructor(numero_niveis){
        this.numero_niveis = numero_niveis;
        this.nivel = [];
    }

    /**
     * Método que retorna o número de níveis de prioridade.
     * 
     * @returns {number} - número de níveis de prioridade.
     */
    getNumeroNiveis(){
        return this.numero_niveis;
    }

    /**
     * Método que retorna a prioridade mais alta que contém algum processo á ser executado.
     * 
     * @returns {number} - retorna o nível de prioridade mais alta, e -1 se nenhum nível tem processo.
     */
    getPrioridadeMaisAlta(){
        // percorre os níveis de cima para baixo
        for(let i = (this.numero_niveis-1); i >= 0; i--){
            // se o nível não for vazio, ele é o de maior prioridade
            if (!this.nivel[i].nivelVazio()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que retorna um nível
     * 
     * @returns {NivelPriodidade} - retorna o nível de prioridade mais alta de índice i
     */
    getNivel(i){
        return this.nivel[i];
    }

    /**
     * Método que inicia todos os níveis
     */
    iniciaNiveis(){
        for (let i = 0; i < this.numero_niveis; i++){
            this.nivel[i] = new NivelPriodidade(i);
        }
    }

    /**
     * Método que atualiza a prioridade dos processos que não foram executados previamente
     * 
     * @param {number} subirPrioridade - número de unidades de tempo sem executar que um processo deve ter para subir de prioridade
     */
    atualizaPrioridades(subirPrioridade){
        // percorre todos os níveis, menos o mais alto, pois não teria nível a subir
        for (let i = (this.numero_niveis-2); i >= 0 ; i--){

            // percorre todos os processos dos níveis
            for(let j = 0; j < this.nivel[i].getTamanhoFila();j++){

                // tenta subir a prioridade de um processo 
                var processo = this.nivel[i].atualizaNivel(j, subirPrioridade);

                // caso o processo tenha subido de prioridade
                if(processo != null){
                    // adiciona ele a prioridade superior
                    this.nivel[i+1].adicionaFila(processo);
                    processo.zeraTempoSemExecutar();
                    // volta um elemento pois o atual foi retirado
                    j = j-1;
                } 

            }
        }
    }

}