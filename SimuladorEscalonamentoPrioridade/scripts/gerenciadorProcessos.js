/**
* Função responsável por criar os níveis 
*/
function criarNiveis(){

    if(gestorDeNiveis != null){
        terminei = true;
    }
    
    // pega o número de níveis selecionado pelo usuário
    numeroNiveis = parseInt(document.getElementById('numeroNiveis').value, 10);

    try {
        // verifica se está no intervalo 
        if(!numeroNiveis || numeroNiveis < 1 || numeroNiveis > 10){
            throw new Error("O número de níveis é invalido, deve ser entre 1 e 10");
        } 
        // cria a fila de pronto, inicia os níveis e atualiza vizualização
        gestorDeNiveis = new GestorDeNiveis(numeroNiveis);
        gestorDeNiveis.iniciaNiveis();
        atualizarVisualizacao();
    } catch (e){
        // trata erro
        alert(e.message);
    }

}

/**
* Função responsável por criar um processo
*/
function criarProcesso() {

    try {

        // verifica se existe fila de pronto
        if (gestorDeNiveis == null){
            throw new Error("Você não inicinou os níveis!");
        }

        // chama os elementos que o processo deve ter
        var tamanhoprocesso = parseInt(document.getElementById('tamanhoprocesso').value, 10);
        var prioridadeprocesso = parseInt(document.getElementById('prioridadeprocesso').value, 10) - 1;

        // verifica se os parâmetros são aceitaveis
        if(!tamanhoprocesso || tamanhoprocesso <= 0){
            throw new Error("Tamanho do processo inválido");
        }
        if (prioridadeprocesso != 0 && !prioridadeprocesso || prioridadeprocesso <= -1 || prioridadeprocesso > numeroNiveis-1){
            throw new Error("Prioridade do processo invlálida!");
        }
    
        // aumenta o número de processos
        numeroProcessos++;

        // cria o processo e o coloca na fila
        var processoatual = new Processo(prioridadeprocesso, tamanhoprocesso, numeroProcessos);
        gestorDeNiveis.getNivel(prioridadeprocesso).adicionaFila(processoatual);
        atualizarVisualizacao();
        
    } catch (e) {
        alert(e.message);
    }
    
}

/**
* Função responsável por criar um processo aleatoriomente
*
*   @param {boolean} isChecked - Verifica se a opção de criar um processo aleatório está selecionada
*   @param {number} probabilidade - A probabilidade de um processo novo ser gerado
*   @param {number} maxTamanho - qual o tamanho máximo que um processo pode ter
*/
function criaProcessoAleatorio(isChecked, probabilidade, maxTamanho){
    // se for pra criar um processo
    if (isChecked){

        // gera a probabilidade
        var probabilidadeGerada = Math.random() * (100 - 0) + 0;

        if(probabilidadeGerada < probabilidade){
            // cria o processo
            numeroProcessos++;
            let prioridade = Math.floor(Math.random() * numeroNiveis); 
            let tamanho = Math.floor(Math.random() * maxTamanho) + 1; 
            var novoprocesso = new Processo(prioridade, tamanho, numeroProcessos);
            gestorDeNiveis.getNivel(prioridade).adicionaFila(novoprocesso);
        }

    }
}

