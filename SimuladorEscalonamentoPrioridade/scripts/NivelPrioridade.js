/**
 * Classe que representa um nível de prioridade específico
 * Contém o número do nível e a fila de processos.
 */
class NivelPriodidade {

    /**
     * Construtor do objeto do tipo Nivel Prioridade
     * 
     * @param {number} nivel - Qual o nível de prioridade do processo ao ser criado.
     * @param {number} fila - Qual o nível de prioridade atual do processo (usado para quando o processo sobe de prioridade)
     */
    constructor(nivel){
        this.nivel = nivel;
        this.fila = []; 
    }

    /**
     * Método que retorna um processo de acordo com seu índice
     * 
     * @param {number} - indice do processo.
     */
    getProcesso(indice){
        return this.fila[indice];
    }

    /**
     * Método que retorna o tamanho de uma fila.
     * 
     * @returns {number} - tamanho da fila.
     */
    getTamanhoFila(){
        return this.fila.length;
    }

    /**
     * Método que adiciona um processo a fila
     */
    adicionaFila(processo){
        this.fila.push(processo);
    }

    /**
     * Método que remove um processo a fila
     * 
     * @returns {Processo} - Retorna o processo removido da fila 
     */
    retiraFila(){
        return this.fila.shift();
    }

    /**
     * Método que verifica o tamanho de uma fila
     * 
     * @returns {number} - Retorna o tamanho da fila
     */
    nivelVazio(){
        return (this.fila.length == 0);
    }

    /**
     * Método que remove um processo de acordo com o seu id.
     * 
     * @returns {Processo} - Retorna o processo removido da fila 
     */
    retiraElemento(id){
        // percorre toda a fila
        for (var i = 0; i <= this.getTamanhoFila()-1; i++){
            // quando encontrar o ID
            if (this.fila[i].getID() == id){
                var removedElement = this.fila.splice(i, 1)[0];
                return removedElement;
            }
        }
        return null;

    }

    /**
     * Método que atualiza o nivel de um processo (aqui, o retiramos da fila)
     * 
     * @param {number} indice - qual o indice do processo
     * @param {number} subirPrioridade - qual o tempo que um processo deve ter sem executar para subir a prioridade
     * @returns {Processo} - Retorna o processo removido da fila 
     */
    atualizaNivel(indice, subirPrioridade){
        
        // se o processo estiver chegado ao tempo sem executar
        if(this.fila[indice].getTempoSemExecutar() == subirPrioridade){
            // sobe a prioridade e sai dessa fila 
            this.fila[indice].sobePrioridade(numeroNiveis);
            return this.retiraElemento(this.fila[indice].getID());
        } else {
            // aumenta seu tempo sem executar
            this.fila[indice].aumentaTempoSemExecutar();
            return null;
        }
    }

    

}