
function mostrarCampos(){

    var tipo = document.getElementById('tipo').value;

    var camposRetaReta = document.getElementById('camposRetaReta');
    var camposRetaPlano = document.getElementById('camposPlanoReta');
    var camposPlanoPlano = document.getElementById('camposPlanoPlano');

    if(tipo == 'retaReta'){
        camposRetaReta.style.display = 'block';
        camposRetaPlano.style.display = 'none';
        camposPlanoPlano.style.display = 'none';
    } else if (tipo == 'retaPlano'){
        camposRetaReta.style.display = 'none';
        camposRetaPlano.style.display = 'block';
        camposPlanoPlano.style.display = 'none';
    } else {
        camposRetaReta.style.display = 'none';
        camposRetaPlano.style.display = 'none';
        camposPlanoPlano.style.display = 'block';
    }
}

class equacoes {

    ProdutoMisto(Vetor1, Vetor2, Vetor3){
        var valor = (Vetor1[0]*((Vetor2[1]*Vetor3[2])-(Vetor2[2]*Vetor3[1]))) + (-Vetor1[1]*((Vetor2[0]*Vetor3[2])-(Vetor2[2]*Vetor3[0]))) + (Vetor1[2]*((Vetor2[0]*Vetor3[1])-(Vetor2[1]*Vetor3[0])));
        return (valor == 0);
    }

    DependenciaLinar(Vetor1, Vetor2){
        var escalares = [];

        for (var i = 0; i < 3; i++){

            if(Vetor2[i] != 0){
                escalares.push(parseFloat(Vetor1[i])/parseFloat(Vetor2[i]));
            } else {
                if(Vetor1[i] != 0){
                    return false;
                }
            }

            if (escalares.length > 1){
                if(escalares[i] != escalares[i-1]){
                    return false;
                }
            }
        }

        return true;
    }

}

class reta extends equacoes {

    constructor (X, Y, Z, A, B, C){
        super();
        this.VetorDiretor = [A, B, C];
        this.Ponto = [X,Y,Z];
    }

    PontoPerntece (Ponto){
        var Resultado = [];
        for (var i = 0; i < 3; i ++){
            if (this.VetorDiretor[i] != 0){
                Resultado.push((Ponto[i] - this.Ponto[i]) / this.VetorDiretor[i]);
            } 

            if (Resultado.length > 1){
                if(Resultado[i] != Resultado[i-1]){
                    return false;
                }
            }
        }

        return true;
    }


}

class Plano extends equacoes{

}

class PlanoGeral extends Plano{
    constructor(A, B, C, D){
        super();
        this.vetorNormal = [A,B,C];
        this.coeficienteD = D;
    }
}

class PlanoVetorial extends Plano{
    constructor(X, Y, Z, A, B, C, D, E, F){
        super();
        this.Ponto = [X, Y, Z];
        this.vetorDiretor1 = [A,B,C];
        this.vetorDiretor2 = [D,E,F];
    }
}

function leElementos(ids, n_elementos){
    var variaveis = [];
    var valor_lido;

    
    for (var i = 0; i < n_elementos; i++){
        
        valor_lido = document.getElementById(ids[i]).value;

        if(!valor_lido){
            valor_lido = 0;
        } else {
            if (valor_lido.includes('/')){
                var numero = valor_lido.split('/');
                var dividendo = numero[0];
                var divisor = numero[1];

                valor_lido = dividendo/divisor;
    
            } else if (valor_lido.includes('raiz')){
                var indiceNumeros = valor_lido.search(/\d/);
                var numero = valor_lido.substring(indiceNumeros);
                valor_lido = Math.sqrt(numero);
            } else if (isNaN(valor_lido)){
                return false;
            }
        }

        variaveis[i] = valor_lido;
    }

    return variaveis;

}


function calcularRetas(){

    var outputElement = document.getElementById('output');

    var ids = ['X', 'Y', 'Z', 'A', 'B', 'C', 'X_', 'Y_', 'Z_', 'A_', 'B_', 'C_'];
    var variaveis = leElementos(ids, 12);

    if(variaveis == false){
        outputElement.textContent = 'Variáveis Inválidas';
    } else {
        var Reta1 = new reta(variaveis[0], variaveis[1], variaveis[2], variaveis[3], variaveis[4], variaveis[5]);
        var Reta2 = new reta(variaveis[6], variaveis[7], variaveis[8], variaveis[9], variaveis[10], variaveis[11]);

        if(Reta1.DependenciaLinar(Reta1.VetorDiretor, Reta2.VetorDiretor)){

            if (Reta1.PontoPerntece(Reta2.Ponto)){
                outputElement.textContent = 'Contidas/Coicidentes';
            } else {
                outputElement.textContent = 'Paralelas Distantes';
            }
 
        } else {
            var vetor = [];

            for (var i = 0; i < 3; i++){
                vetor[i] = Reta2.Ponto[i] - Reta1.Ponto[i];
            }

            if (Reta1.ProdutoMisto(vetor, Reta1.VetorDiretor, Reta2.VetorDiretor)){
                outputElement.textContent = 'Concorrentes';
            } else {
                outputElement.textContent = 'Reversas';
            }
        }
    }
   
}

function calcularRetaPlano(){

    var outputElement = document.getElementById('outputPlanoReta');

    var ids = ['XPlano', 'YPlano', 'ZPlano', 'APlano', 'BPlano', 'CPlano', 'X_Plano', 'Y_Plano', 'Z_Plano', 'A_Plano', 'B_Plano', 'C_Plano', 'EPlano', 'FPlano', 'GPlano'];
    var variaveis = leElementos(ids, 15);

    if(variaveis == false){
        outputElement.textContent = 'Variáveis Inválidas';
    } else {

        var Reta = new reta (variaveis[0], variaveis[1], variaveis[2], variaveis[3], variaveis[4], variaveis[5]);
        var Plano = new PlanoVetorial (variaveis[6], variaveis[7], variaveis[8], variaveis[9], variaveis[10], variaveis[11], variaveis[12], variaveis[13], variaveis[14]);

        if(Reta.ProdutoMisto(Reta.VetorDiretor, Plano.vetorDiretor1, Plano.vetorDiretor2)){
        
            var vetor = [];
    
            for (var i = 0; i < 3; i++){
                vetor[i] = Plano.Ponto[i] - Reta.Ponto[i];
            }
    
            if (Reta.ProdutoMisto(vetor, Plano.vetorDiretor1, Plano.vetorDiretor2)){
                outputElement.textContent = 'Reta Contida';
            } else {
                outputElement.textContent = 'Disjunta';
            }
     
        } else {
            outputElement.textContent = 'Transversais';
        }
    }
}



function calcularPlanos(){

    var outputElement = document.getElementById('outputPlanos');

    var variaveis = [];
    var ids = ['a', 'b', 'c', 'd', 'a_', 'b_', 'c_', 'd_'];
    var variaveis = leElementos(ids, 8);

    if(variaveis == false){
        outputElement.textContent = 'Variáveis Inválidas';
    } else {
        var Plano1 = new PlanoGeral (variaveis[0], variaveis[1], variaveis[2], variaveis[3]);
        var Plano2 = new PlanoGeral (variaveis[4], variaveis[5], variaveis[6], variaveis[7]);
    
        if(Plano1.DependenciaLinar(Plano1.vetorNormal, Plano2.vetorNormal)){
            if(Plano1.coeficienteD == Plano2.coeficienteD){
                outputElement.textContent = 'Paralelos coicidentes';
            } else {
                outputElement.textContent = 'Paralelos Distintos';
            }
        } else {
            outputElement.textContent = 'Transversais';
        }
        
    }
    
}
