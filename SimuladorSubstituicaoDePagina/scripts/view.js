
function atualizaListaProcessos() {
    var processosPendentes = document.getElementById('processosPendentes');
    processosPendentes.innerHTML = ''; 

    if (processosEntrar.length != 0) {
        for (var i = 0; i < processosEntrar.length; i++) {

            var processoAtual = processosEntrar[i];

            var processoDiv = document.createElement('div');

            processoDiv.className = 'item';
            processoDiv.innerHTML = `P:${processoAtual.getId()} | ${processoAtual.getTamanho()}pg | ${processoAtual.getEntrada()}u.t`;

            processosPendentes.appendChild(processoDiv);
        }
    }
}

function mostra(Saida) {
    
    var LinhaDoTempo = document.getElementById('LinhaDoTempo');

    var saida = document.createElement('div');
    saida.className = 'linha-saida';
    saida.innerHTML = Saida.replace(/\n/g, '<br>');

    LinhaDoTempo.appendChild(saida);
}

function habilitaBotaoInicia(){
    
    const botaoInicia = document.getElementById('botaoInicia'); 
    botaoInicia.disabled = false;
    botaoInicia.classList.remove("disabled");

    const botaoCriaMemoria = document.getElementById('botaoIniciaMemoria'); 
    botaoCriaMemoria.disabled = false;
    botaoCriaMemoria.classList.remove("disabled");

    const botaoCriaProcesso = document.getElementById('botaoCriaProcesso'); 
    botaoCriaProcesso.disabled = false;
    botaoCriaProcesso.classList.remove("disabled");

    const checkbox = document.getElementById('criarProcessoAleatorio');
    checkbox.disabled = false;
}


function desabilitaBotaoInicia(){

    const botaoInicia = document.getElementById('botaoInicia'); 
    botaoInicia.disabled = true;
    botaoInicia.classList.add("disabled");

    const botaoCriarNiveis = document.getElementById('botaoIniciaMemoria'); 
    botaoCriarNiveis.disabled = true;
    botaoCriarNiveis.classList.add("disabled");

    const botaoCriaProcesso = document.getElementById('botaoCriaProcesso'); 
    botaoCriaProcesso.disabled = true;
    botaoCriaProcesso.classList.add("disabled");

    const checkbox = document.getElementById('criarProcessoAleatorio');
    checkbox.disabled = true;
}


function habilitaBotaoPare(){
    var botaoPara = document.getElementById('botaoPara'); 
    botaoPara.disabled = false;
    botaoPara.classList.remove("disabled");
}

function desabilitaBotaoPare(){
    const botaoPara = document.getElementById('botaoPara'); 
    botaoPara.disabled = true;
    botaoPara.classList.add("disabled");
}

