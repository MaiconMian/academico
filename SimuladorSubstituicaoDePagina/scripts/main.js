var numeroProcessos = 0; 
var mru = null;
var processosEntrar = [];
var ut = 0;
var ciclo = 0;
var Saida = '';
var tamanhoMemoria =  -1;
var isChecked = false;
var pare = false;

function iniciaMemoria(){

    tamanhoMemoria = parseInt(document.getElementById('tamanhoMemoria').value, 10);

    try {

        if (!tamanhoMemoria || tamanhoMemoria <= 0){ 
            throw new Error("Tamanho de Memoria Invalido!");
        } else {
            mru = new MRU(tamanhoMemoria);
            mru.iniciaMemoria();
        }
    
    } catch(e){
       alert(e.message);
    }
}

function criaProcesso(){

    var tamanhoProcesso = parseInt(document.getElementById('tamanhoProcesso').value, 10);
    var horaentrar = parseInt(document.getElementById('horaEntrar').value, 10);
    
    try {

        if (!tamanhoProcesso || tamanhoProcesso <= 0){ 
            throw new Error("Tamanho do processo Invalido!");
        } else if (mru == null){
            throw new Error("A memória não foi iniciada!");
        } else if (isNaN(horaentrar) ||  horaentrar < 0) {
            throw new Error("Hora de entrar inválida!");
        } else if (tamanhoProcesso > tamanhoMemoria){
            throw new Error("O processo não cabe na memória!");
        } else {
            let processo = new Processo(numeroProcessos, tamanhoProcesso, 1, 1, horaentrar);
            processosEntrar.push(processo);
            numeroProcessos++;
            atualizaListaProcessos()
        }
    
    } catch(e){
       alert(e.message);
    }

}

function inicia(){

    try {
        if (processosEntrar == null || processosEntrar == undefined || processosEntrar.length == 0){ 
            throw new Error("Não existem processos a serem colocados na memória!");
        } else if (mru == null){
            throw new Error("A memória não foi iniciada!");
        } else {
            desabilitaBotaoInicia();
            habilitaBotaoPare();
            const checkbox = document.getElementById('criarProcessoAleatorio');
            isChecked = checkbox.checked;
            processar();
        }
    
    } catch(e){
       alert(e.message);
    }
}

function parar(){
    pare = true;
}

function geraAleatorio(){
    let tamanho = Math.floor(Math.random() * tamanhoMemoria) + 1;
    let processo = new Processo(numeroProcessos, tamanho, 1, 1, ut);
    numeroProcessos++;
    mru.adicionaProcesso(processo);
    Saida = Saida + '\nAdicionando Processo Aleatorio de Tamanho ' + tamanho + ', bits R1 e M1';
}

function processar(){

    if(pare){
        desabilitaBotaoPare();
        habilitaBotaoInicia();
        pare = false;
        return;
    }

    Saida = ' ';

    console.log(ut + " u.t");
    Saida = Saida + ' ' + ut + ' u.t:';

    for (let i = processosEntrar.length - 1; i >= 0; i--) {
        if (processosEntrar[i].getEntrada() == ut) {
            mru.adicionaProcesso(processosEntrar[i]);
            processosEntrar.splice(i, 1); 
        }
    }
    

    atualizaListaProcessos();

    if(ciclo == 10){
        mru.incrementaR();
        ciclo = 1;
    } else {
        ciclo++;
    }

    mru.incrementaM();
    mru.aleatoriza();

    if(isChecked){
        console.log("ta check");
        if (Math.random() <= 0.1) {
            geraAleatorio(); 
        }
    }

    mostra(Saida);

    ut++;

    setTimeout(() => {
        processar();
    }, 1000); 

    
}