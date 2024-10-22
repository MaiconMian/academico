/*
+-------------------------------------------------------------+
| UNIFAL – Universidade Federal de Alfenas.                    |
| BACHARELADO EM CIENCIA DA COMPUTACAO.                        |
| Trabalho..: Escalonamento por Prioridade                     |
| Disciplina: Sistemas Operacionais                            |
| Professor.: Fellipe Rey                                      |
| Aluno..: Maicon Almeida Mian 2023.1.08.013                   |
+-------------------------------------------------------------+
*/

var numeroProcessos = 0; // contador para o número de processos
var gestorDeNiveis = null; // fila de pronto 
var processamentoatual = null; // qual o processo atual
var numeroNiveis = 0 // numero de niveis
var ut = 0; // armazena a unidade de tempo atual
var parei = false; // valor para parar o processamento

/**
* Função que inicia todo o processamento
*/
function iniciar() {

    var quantumMaximo = parseInt(document.getElementById('tamanhoQuantum').value, 10), // valor maximo de um quantum
        subirPrioridade = parseInt(document.getElementById('SubirPrioridade').value, 10), // valor para subir de prioridade
        quantum = 0; // inicia a variavel quantum
        executando = false, // nenhum processo executando ainda
        probabilidade = parseInt(document.getElementById('Probabilidade').value, 10), // probabilidade de gerar um processo aleatorio
        maxTamanho = parseInt(document.getElementById('MaxTamanho').value, 10); // tamanho maximo que um processo aleatorio pode ter
    
    const checkbox = document.getElementById('criarProcessoAleatorio'), // checkbox, constante pra não alterar durante execução
        isChecked = checkbox.checked; // verifica se o checkbox está precionado

    // desabilitando botão inicia
    desabilitaBotaoInicia();

    try {

        // faz as ferificações para erro
        if (!numeroNiveis || numeroNiveis == 0){ // não iniciou os níveis
            throw new Error("Você não iniciou os níveis");
        } else if (!quantumMaximo || quantumMaximo == 0){ // não setou o valor do quantum
            throw new Error("Você não iniciou o valor do quantum"); 
        } else if (!isChecked && gestorDeNiveis != null && gestorDeNiveis.getPrioridadeMaisAlta() == -1 && processamentoatual == null){ // não tem o que executar
            throw new Error("Não existirão processos a serem executados!"); 
        } else if (isChecked && (!probabilidade || !maxTamanho || probabilidade < 1 || probabilidade > 100 || maxTamanho < 1)){
            throw new Error("Verifique a probabilidade [1-100] e o tamanho maximo[1-*] para que o processo aleatorio seja gerado");
        } else if (!subirPrioridade || subirPrioridade < 1){ // não colocou o valor de u.t sem executar para subir prioridae
            throw new Error("Defina um tempo para que um processo suba de nível se não for executado");
        } else {
           // habilita botao de pare
           habilitaBotaoPare();
           // se deu tudo certo, executa
            processar(quantum, executando, quantumMaximo, probabilidade, maxTamanho, subirPrioridade, isChecked, );
        }
    
    } catch(e){
       // tratamento de erros
       habilitaBotaoInicia();
       alert(e.message);
    }

}

/**
* Função que representa o processamento de um processo
* Cada iteração na função é considerada um u.t
* @param {number} quantum - Qual o quantum atual de execução
* @param {boolean} executando - Guarda se existe algum processo sendo executado
* @param {number} quantumMaximo - Guarda o tamanho do quantum máximo
* @param {number} probabilidade - A probabilidade de um processo ser gerado
* @param {number} maxTamanho - Qual o tamanho máximo de um processo elatorio
* @param {number} subirPrioridade - Qual o tempo para que um processo suba de prioridade
* @param {boolean} isChecked - Verifica se o checkbox está ativado
*/
function processar(quantum, executando, quantumMaximo, probabilidade, maxTamanho, subirPrioridade, isChecked,) {
    
    // aumenta um na unidade de tempo
    ut++;

    // verifica se clicou no botão de pare
    if (verificaParar()){

        desabilitaBotaoPare();
        habilitaBotaoLimpa();
        atualizarVisualizacao();
        return;
    }

    if (quantum < quantumMaximo && executando) {
        processamentoatual.decresceTamanho();
        // se o processo acabou a execução
        if (processamentoatual.getTamanho() == 0) {
            // não tem nada executando
            executando = false; 
            processamentoatual = null;
        } 
        quantum++;
    } 

    // se chegou ao fim do quantum ou não tem nenhum processo executando
    if (quantum >= quantumMaximo || !executando) {

        quantum = 0;
        
        // se o processo não chegou ao fim, retorna a fila
        if (processamentoatual != null && processamentoatual.getTamanho() != 0) {
             // volta prioridade anterior e zera o tempo sem executar
            processamentoatual.voltaPrioridade(); 
            processamentoatual.zeraTempoSemExecutar();
            gestorDeNiveis.getNivel(processamentoatual.getPrioridade()).adicionaFila(processamentoatual);
        }


        // se ainda existem processos
        if (gestorDeNiveis.getPrioridadeMaisAlta() != -1) {
            // pega o processo da prioridade mais alta
            processamentoatual = gestorDeNiveis.getNivel(gestorDeNiveis.getPrioridadeMaisAlta()).retiraFila();
            executando = true;

        } else if (!isChecked){ // para a execução apenas se não roda infinitamente por conta dos processos eleatórios

            // desabilita botão e reseta variáveis para fim de programa
            parei = false;
            desabilitaBotaoPare();
            habilitaBotaoLimpa();
            executando = false;

            // atualiza vizualização e volta
            atualizarVisualizacao();
            return;

        } 

    }

    // cria um proesso aleatorio
    criaProcessoAleatorio(isChecked, probabilidade, maxTamanho);
    // atualiza o nivel de prioridade dos processos
    gestorDeNiveis.atualizaPrioridades(subirPrioridade-1);
    // atualiza vizualização
    atualizarVisualizacao();
    // chama novamente a função
    setTimeout(() => {
        processar(quantum, executando, quantumMaximo, probabilidade, maxTamanho, subirPrioridade, isChecked);
    }, 1300); 

}

/**
* Função que é chamada para parar o processamento
*/
function parar(){
    parei = true;
}

/**
* Função que verifica se o botão de pare foi apertado
*/
function verificaParar(){
    return parei;
}



