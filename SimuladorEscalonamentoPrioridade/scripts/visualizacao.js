// cores para os processos
const cores = ['#6a0dad', '#1e90ff', '#32cd32', '#ffff00', '#ff0000', '#ff1493'];

/**
* Função que é chamada para atualizar a vizualização geral
* dos níveis, do processo executando e da linha do tempo
*/
function atualizarVisualizacao() {
    
    // pega a div que guarda os níveis e limpa ela
    var escalonador = document.getElementById('niveis');
    escalonador.innerHTML = '';

    // se existirem processos
    if(gestorDeNiveis != null){
        // percorre toda a fila de pronto
        for (var i = 0; i < gestorDeNiveis.getNumeroNiveis(); i++) {

            // cria o nivel e a div dele
            var nivel = gestorDeNiveis.getNivel(i);
            var nivelDiv = document.createElement('div');
            
            // cria a div no html do título do nivel
            nivelDiv.className = 'nivel';
            nivelDiv.innerHTML = `<p>nivel ${i+1}</p><br>`;
    
            // cria todos os processos que pertecem aquele nível
            for(let j = 0; j < nivel.getTamanhoFila(); j++){
    
                // pega o processo
                processo = nivel.getProcesso(j);
                // cria a div com as informações
                var processoDiv = document.createElement('div');
                processoDiv.className = 'processo';
                processoDiv.textContent = `P${processo.getID()} ${processo.getTamanho()}u.t`;
                // seta a cor do processo
                processoDiv.style.backgroundColor = cores[processo.getID() % 6];
                nivelDiv.appendChild(processoDiv);
            }
    
            escalonador.appendChild(nivelDiv);
        }
    }
    
    // atualiza demais vizualizações
    atualizaProcessoAtual();
    atualizaLinhadoTempo();

}

/**
* Função que é chamada para atualizar o processo processado na ultima ut
*/
function atualizaProcessoAtual(){

    // limpa o anterior
    var atual = document.getElementById('processoExecutando');
    atual.innerHTML = '';
    
    // se ainda existe um processo
    if(processamentoatual != null){
        // cria a div e preenche ela
        let processamentoatualDiv = document.createElement('div');
        processamentoatualDiv.className = 'processo';
        processamentoatualDiv.textContent = `${processamentoatual.getID()}`;
        processamentoatualDiv.style.backgroundColor = cores[processamentoatual.getID() % 6];
        // colocando ela na div do processamento atual
        atual.appendChild(processamentoatualDiv);

    }
}

/**
* Função que é chamada para atualizar a linha do tempo
*/
function atualizaLinhadoTempo(){

    // pega a div da linha do tempo
    var linhaDoTempo = document.getElementById('linhaDoTempo');

    // se existir um novo processo á ser alocado
    if(processamentoatual != null){

        // cria a div do processo
        let bloco = document.createElement('div');
        bloco.className = 'utProcesso';

        // cria a div da unidade de tempo
        let unidadeDeTempo = document.createElement('div');
        unidadeDeTempo.className = 'ut';
        unidadeDeTempo.textContent = `${ut}u.t: `;
        bloco.appendChild(unidadeDeTempo);

        // cria o elemento do processamento com as informações (inclusive a cor)
        let processamentoatualDiv = document.createElement('div');
        processamentoatualDiv.className = 'processo';
        processamentoatualDiv.textContent = `P${processamentoatual.getID()}`;
        processamentoatualDiv.style.backgroundColor = cores[processamentoatual.getID() % 6];

        // coloca as divs corretamente
        bloco.appendChild(processamentoatualDiv);
        linhaDoTempo.appendChild(bloco);
    }
}

/**
* Função que é chamada para limpar a vizualização quando o processamento termina
*/
function limpar(){

    // desabilita botão de limpa e pare e habilita o de iniciar
    habilitaBotaoInicia();
    desabilitaBotaoPare();
    desabilitaBotaoLimpa();

    // limpa as divs
    var linhaDoTempo = document.getElementById('linhaDoTempo');
    linhaDoTempo.innerHTML = ''; 

    var atual = document.getElementById('processoExecutando');
    atual.innerHTML = '';

    var escalonador = document.getElementById('niveis');
    escalonador.innerHTML = '';

    // reinicia variáveis
    numeroProcessos = 0; 
    gestorDeNiveis = null; 
    processamentoatual = null; 
    numeroNiveis = 0 
    ut = 0;
    parei = false;
    
}

/**
* Função que é chamada para habilitar o botão de iniciar e todos os botões que iniciam níveis, processos 
* e processos aleatórios
*/
function habilitaBotaoInicia(){
    // habilita botão de inicia
    const botaoInicia = document.getElementById('botaoInicia'); 
    botaoInicia.disabled = false;
    botaoInicia.classList.remove("disabled");

    // habilita botão de criar níveis
    const botaoCriarNiveis = document.getElementById('botaoCriaNiveis'); 
    botaoCriarNiveis.disabled = false;
    botaoCriarNiveis.classList.remove("disabled");

    // habilita botão de criar processo
    const botaoCriaProcesso = document.getElementById('botaoCriaProcesso'); 
    botaoCriaProcesso.disabled = false;
    botaoCriaProcesso.classList.remove("disabled");

    // habilita checkbox
    const checkbox = document.getElementById('criarProcessoAleatorio');
    checkbox.disabled = false;
}

/**
* Função que é chamada para desabilitar o botão de iniciar e todos os botões que iniciam níveis, processos 
* e processos aleatórios
*/
function desabilitaBotaoInicia(){
    // desabilita botão de inicia
    const botaoInicia = document.getElementById('botaoInicia'); 
    botaoInicia.disabled = true;
    botaoInicia.classList.add("disabled");

    // desabilita botão de criar niveis
    const botaoCriarNiveis = document.getElementById('botaoCriaNiveis'); 
    botaoCriarNiveis.disabled = true;
    botaoCriarNiveis.classList.add("disabled");

    // desabilita botão de criar processo
    const botaoCriaProcesso = document.getElementById('botaoCriaProcesso'); 
    botaoCriaProcesso.disabled = true;
    botaoCriaProcesso.classList.add("disabled");

    // desabilita checkbox
    const checkbox = document.getElementById('criarProcessoAleatorio');
    checkbox.disabled = true;
}

/**
* Função que é chamada para habilitar o botão de parar
*/
function habilitaBotaoPare(){
    var botaoPara = document.getElementById('botaoPara'); 
    botaoPara.disabled = false;
    botaoPara.classList.remove("disabled");
}

/**
* Função que é chamada para desabilitar o botão de parar
*/
function desabilitaBotaoPare(){
    const botaoPara = document.getElementById('botaoPara'); 
    botaoPara.disabled = true;
    botaoPara.classList.add("disabled");
}

/**
* Função que é chamada para habilitar o botão de limpar
*/
function habilitaBotaoLimpa(){
    var botaoLimpa = document.getElementById('botaoLimpa'); 
    botaoLimpa.disabled = false;
    botaoLimpa.classList.remove("disabled");
}

/**
* Função que é chamada para desabilitar o botão de limpar
*/
function desabilitaBotaoLimpa(){
    var botaoLimpa = document.getElementById('botaoLimpa'); 
    botaoLimpa.disabled = true;
    botaoLimpa.classList.add("disabled");
}