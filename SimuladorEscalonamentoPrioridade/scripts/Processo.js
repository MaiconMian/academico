/**
 * Classe que representa um Processo a ser executado
 * Cada processo tem uma prioridade, um tamanho e um identificador único.
 */
class Processo {

    /**
     * Construtor do objeto do tipo Processo
     * 
     * @param {number} prioridade - Qual o nível de prioridade do processo ao ser criado.
     * @param {number} prioridadeatual - Qual o nível de prioridade atual do processo (usado para quando o processo sobe de prioridade)
     * @param {number} tamanho - O tamanho do processo em unidades de tempo.
     * @param {number} id - O identificador do processo.
     * @param {number} tempoSemExecutar - Guarda o tempo que o processo está sem executar
     */
    constructor(prioridade, tamanho, id){
        this.prioridade = prioridade; 
        this.prioridadeatual = prioridade; 
        this.tamanho = tamanho; 
        this.id = id; 
        this.tempoSemExecutar = 0;
    }

    /**
     * Método que retorna o id do processo.
     * 
     * @returns {number} - identificador do processo.
     */
    getID(){
        return this.id;
    }

    /**
     * Método que retorna a prioridade do processo ao ser criado.
     * 
     * @returns {number} - prioridade do processo.
     */
    getPrioridade(){
        return this.prioridade;
    }

    /**
     * Método que retorna a prioridade atual do processo.
     * 
     * @returns {number} - prioridade atual que o processo se encontra.
     */
    getPrioridadeAtual(){
        return this.prioridadeatual;
    }

    /**
     * Método que retorna o tamanho atual do processo.
     * 
     * @returns {number} - tamanho atual que o processo se encontra.
     */
    getTamanho(){
        return this.tamanho;
    }

    /**
     * Método que retorna a prioridade atual do processo.
     * 
     * @returns {number} - prioridade atual do processo.
     */
    getPrioridadeAtual(){
        return this.prioridadeatual;
    }

    /**
     * Método que retorna o tempo sem executar de um processo.
     * 
     * @returns {number} - retorna o tempo sem executar do processo.
     */
    getTempoSemExecutar()
    {
        return this.tempoSemExecutar;
    }

    /**
     * Método que diminui em um o tamanho  do processo.
     */
    decresceTamanho() {
        this.tamanho--;
    } 

    /**
     * Método que sobe a prioridade de um processo.
     * 
     * @param {number} max - Qual o maior nível que um processo pode ter 
     * @returns {number} - retorna 1 para processo
     */
    sobePrioridade(max) {
        // se o proceso já não estiver no topo
        if(this.prioridadeatual < max){
            // sobe e retorna 1 (verdadeiro)
            this.prioridadeatual++;
            return 1;
        } else {
            return 0;
        }
    } 

    /**
     * Método que desce a prioridade de um processo
     * 
     * @returns {number} - retorna 1 para processo
     */
    descePrioridade() {
        // se não estiver no ultimo nivel de prioridade
        if(this.prioridade >= 0){
            // decresce
            this.prioridade--;
            return 1;
        } else {
            return 0;
        }
    } 

    /**
     * Método que zera o tempo sem executar de um processo
     */
    zeraTempoSemExecutar(){
        this.tempoSemExecutar = 0;
    }

    /**
     * Método que aumenta o tempo sem executar de um processo
     */
    aumentaTempoSemExecutar()
    {
        this.tempoSemExecutar++;
    }

    /**
     * Método que volta o processo á sua prioridade atual
     */
    voltaPrioridade(){
        this.prioridadeatual = this.prioridade;
    }

}