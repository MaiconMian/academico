class Processo {

    constructor(id, tamanho, R, M, entrada){
        this.id = id;
        this.tamanho = tamanho;
        this.R = R;
        this.M = M;
        this.entrada = entrada;
        this.enderecoInicio;
        this.contador = 0;
    }

    getEntrada(){
        return this.entrada;
    }


    getContador(){
        return this.contador;
    }

    setContador(valor){
        this.contador = valor;
    }

    aumentaContador(){
        this.contador++;
    }

    setEnderecoInicio(endereco){
        this.enderecoInicio = endereco;
    }

    getEnderecoInicio(){
        return this.enderecoInicio;
    }

    getClasse(){
        if(this.R == 0){
            if(this.M == 0){
                return 0;
            } else {
                return 1;
            }
        }else if(this.R == 1){
            if(this.M == 0){
                return 2;
            } else {
                return 3;
            }
        }
    }

    getId() {
        return this.id;
    }
    
    setId(id) {
        this.id = id;
    }
    
    getTamanho() {
        return this.tamanho;
    }
    
    setTamanho(tamanho) {
        this.tamanho = tamanho;
    }
    
    getR() {
        return this.R;
    }
    
    setR(R) {
        this.R = R;
    }
    
    getM() {
        return this.M;
    }
    
    setM(M) {
        this.M = M;
    }

    getEnderecoInicio(){
        return this.enderecoInicio
    }

    setEnderecoInicio(enderecoInicio){
        this.enderecoInicio = enderecoInicio;
    }

    getProx(){
        return this.prox;
    }

    setProx(prox){
        this.prox = prox;
    }
    
}