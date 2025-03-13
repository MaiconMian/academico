class MRU {

    constructor(tamanho){
        this.tamanho = tamanho; 
        this.memoria = new Array(tamanho); 
        this.tabelaProcessos = []; 
    }

    getTamanho() {
        return this.tamanho;
    }
    
    setTamanho(valor) {
        this.tamanho = valor;
    }

    iniciaMemoria(){
        for (let i = 0; i < this.tamanho; i++){
            this.memoria[i] = -1;
        }
    }

    encontrarpeloID(id){
        for(let k = 0; k < this.tabelaProcessos.length; k++){
            if(this.tabelaProcessos[k].getId() == id){
                return this.tabelaProcessos[k];
            }
        }
    }

    retiraElemento(id){
        for (var i = 0; i < this.tabelaProcessos.length; i++){
            if (this.tabelaProcessos[i].getId() == id){
                this.tabelaProcessos.splice(i, 1)[0];
                return true;
            }
        }
        return false;
    }

    buscaMenorClasse(){
        let menor = 1000;
        for (let i = 0; i < this.tabelaProcessos.length; i++){
            if(this.tabelaProcessos[i].getClasse() < menor){
                menor = this.tabelaProcessos[i].getClasse();
            }
        }
        return menor;
    }

    
    desfragmentaMemoria(){

        for(let i = 0; i < this.memoria.length; i++){
            this.memoria[i] = -1;
        }

        for( let j = 0; j < this.tabelaProcessos.length; j++){
            this.firstFit(this.tabelaProcessos[j]);
        }

        console.log("memoria desfragmentada:");
        console.log(this.memoria);
    }

    totalespacosVazios(){

        let total = 0;
        for (let i = 0; i < this.tamanho; i++){

            if(this.memoria[i] == -1){
                total++;
            }   
        }
        return total;
    }

    firstFit(processo) {
        var casaInicial = 0;
        var tamanhovazio = 0;
    
        for (let i = 0; i < this.tamanho; i++) {
            
            if (this.memoria[i] == -1) {

                casaInicial = i;
                tamanhovazio = 0;
    
                for (let j = i; j < this.tamanho && this.memoria[j] == -1; j++) {
                    tamanhovazio++;
                }
    
                if (tamanhovazio >= processo.getTamanho()) {
                    let tamProc = processo.getTamanho();
    
                    for (let k = casaInicial; k < this.tamanho && tamProc != 0; k++) {
                        this.memoria[k] = processo.getId();
                        tamProc--;
                    }
                    processo.setEnderecoInicio(casaInicial);
                    return processo;
                } else {
                    i = casaInicial + tamanhovazio - 1;
                }
            }
        }
        return null;
    }
    
    NUR(processo) {
    
        let menorClasse = this.buscaMenorClasse();
        let processosMenorClasse = [];
    
        // Seleciona todos os processos da menor classe
        for (let i = 0; i < this.tabelaProcessos.length; i++) {
            if (this.tabelaProcessos[i].getClasse() == menorClasse) {
                processosMenorClasse.push(this.tabelaProcessos[i]);
            }
        }
    
        // Sorteia um processo aleatoriamente dos menores
        let numeroSorteado = Math.floor(Math.random() * processosMenorClasse.length);
        let processoAleatorio = processosMenorClasse[numeroSorteado];
    
        // Removendo o processo
        for (let k = processoAleatorio.getEnderecoInicio(); this.memoria[k] == processoAleatorio.getId(); k++) {
            this.memoria[k] = -1;
        }


        Saida = Saida + ' P' + processoAleatorio.getId();

        this.retiraElemento(processoAleatorio.getId());
    
        // Verifica se o espaço foi suficiente
        if (this.totalespacosVazios() >= processo.getTamanho()) {
            this.desfragmentaMemoria();
            this.firstFit(processo);
            return;
        } else {
            this.NUR(processo);
        }
    }
    

    adicionaProcesso(processo){

        var retorno = this.firstFit(processo);

        Saida = Saida + '\n Entra o processo P' + processo.getId() + ' com bits R' + processo.getR() + ' M' + processo.getM() + ' '; 

        if(retorno == null){

            Saida = Saida + '\n No lugar dos processos: ';
            this.NUR(processo);
            Saida = Saida + ' \n';

        } else {

        }

        this.tabelaProcessos.push(processo);

    }


    incrementaR(){
        
        for(let j = 0; j < this.tabelaProcessos.length; j++){
            if(this.tabelaProcessos[j].getR() == 1){
                Saida = Saida + '\n Por conta dos 10 ciclos de clock: ';
            
                this.tabelaProcessos[j].setR(0);
                Saida = Saida + '\n P ' + this.tabelaProcessos[j].getId() + ' foi para R0, R' + this.tabelaProcessos[j].getR() + ' M' + this.tabelaProcessos[j].getM();
            }
            
        }
    }

    incrementaM(){
        for(let j = 0; j < this.tabelaProcessos.length; j++){
            
            if(this.tabelaProcessos[j].getContador() == 10){
                this.tabelaProcessos[j].setM(0);
                this.tabelaProcessos[j].setContador(1);
                Saida = Saida + '\n Por conta dos 10 ciclos desde a ultima alteração do M: ';
                Saida = Saida + '\n P ' + this.tabelaProcessos[j].getId() + ' foi para M0, R' + this.tabelaProcessos[j].getR() + ' M' + this.tabelaProcessos[j].getM();
            } else {
                this.tabelaProcessos[j].aumentaContador();
            }
        }
    }

    aleatoriza(){

        for(let j = 0; j < this.tabelaProcessos.length; j++){

            let probabilidadeModificado = Math.random() * 100; 

            if (probabilidadeModificado < 5) {
                this.tabelaProcessos[j].setR(1);
                this.tabelaProcessos[j].setM(1);
                this.tabelaProcessos[j].setContador(0);
                Saida = Saida + '\n P ' + this.tabelaProcessos[j].getId() + ' foi referenciado e modificado R' + this.tabelaProcessos[j].getR() + ' M' + this.tabelaProcessos[j].getM();
            } else {

                let probabilidadeReferenciado = Math.random() * 100;  

                if (probabilidadeReferenciado < 5){
                    this.tabelaProcessos[j].setR(1);
                    Saida = Saida + '\n P ' + this.tabelaProcessos[j].getId() + ' foi referenciado R' + this.tabelaProcessos[j].getR() + ' M' + this.tabelaProcessos[j].getM();
                }
            }

        }
    }

}
