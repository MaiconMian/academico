#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "passeio.h"

/*Feito por
Maicon Almeida Mian RA 2023.1.08.013
*/

//guarda as dimensões do tabuleiro
int n, m;

//testa se o proximo movimento e possivel
bool testemovimento (int novalinha, int novacoluna, int n, int m, bool ** tabuleiro){
    //se estiver nos limites e a casa nao estiver marcada, retorna true
    return(novalinha >= 0 && novalinha < n && novacoluna >= 0 && novacoluna < m && !tabuleiro[novalinha][novacoluna]);
}

//empilha um item (anda com o cavalo)
void andar (int topo, int linhateste, int colunateste, PILHA * pilha){
    //a casa atual do item recebe a casa testada
    pilha[topo].casa.x = linhateste;
    pilha[topo].casa.y = colunateste;
    //guarda que nao acessou nenhum item ainda
    pilha[topo].acessados = 0;
}

void computa_passeios(bool **tabuleiro) {
    
     //variaveis inteiras
    int topo = -1, linhateste, colunateste, abertos = 0, fechados = 0;
    //bool que confere se foi um movimento possivel
    bool completo = true;

    //matriz com todos os movimentos possiveis do cavalo
    int possiveis[8][2] = {
    	{2, 1},
    	{1, 2},
    	{-1, 2},
    	{-2, 1},
    	{-2, -1},
    	{-1, -2},
    	{1, -2},
    	{2, -1}
	};

    //aloca a pilha
    PILHA item[m*n];
    
    //inicia o tabuleiro na primeira casa
    linhateste = n-1; 
    colunateste = 0;
    tabuleiro[linhateste][colunateste] = true;
    topo++;
    andar(topo, linhateste, colunateste, item);
    
    //enquanto nao chegamos ao fim da pilha (sem movimentos possiveis)
    while (topo >= 0){

        //seta completo como true
        completo = true;
        //laço para testar os 8 movimentos possiveis
        for (int i = item[topo].acessados; i < 8; i++){ //i inicia com o valor de acesados, pra nao averiguar possibilidades ja feitas
                //movimento que sera testado nesse momento
                linhateste = item[topo].casa.x + possiveis[i][0];
                colunateste = item[topo].casa.y + possiveis[i][1];
                
                //se for um movimento possivel
                if (testemovimento (linhateste, colunateste, n, m, tabuleiro)){
                    item[topo].acessados++; //acessados atual 
                    tabuleiro[linhateste][colunateste] = true; //tabuleiro na futura casa recebe true
                    topo++; //aumenta um pro topo
                    andar(topo, linhateste, colunateste, item); //anda com o cavalo (empilha o movimento) 
                    completo = false; //seta completo como falso, assim ele sabera que um movimento foi possivel
                    break; //sai do for
                } else { 
                    item[topo].acessados++; //se o movimento nao foi possivel, soma tambem em acessados
                }  
        }

        //quando nao tiver mais casas disponiveis pasa acessar 
        if (completo){
            //se preenchemos todo o tabuleiro
            if (topo == (m*n) - 1){
                int i = 0; //inicia i com 0
                //testa todos os movimentos possiveis, se algum for valido ele marca como fechados
                while (i < 8){
                    if ((item[topo].casa.x + possiveis[i][0] == item[0].casa.x) && (item[topo].casa.y + possiveis[i][1] == item[0].casa.y)){
                        fechados++;
                        break;
                    }
                    i++;
                }
                //se ele testou tudo e nenhum foi fechado, entao e aberto
                if (i == 8){
                    abertos++;
                }
            } 
            //se o topo e maior que 0 (ou seja, nao chegamos ou fim ainda)
            if (topo > 0){
                tabuleiro[item[topo].casa.x][item[topo].casa.y] = false; //a posicao volta a ser disponivel
            }
            topo--;//"desempilha" uma casa 
        }

    }
    //mostra na tela as saidas
    printf("%d\n%d\n", fechados, abertos);
    return;
}

int main(int argc, char* argv[]) {
    ///////////////////////////////////////////////////////////
    /////////////////// Leitor de instâncias //////////////////
    ///////////////// Não deve ser modificado /////////////////
    ///////////////////////////////////////////////////////////
    int instancia_num = -1;
    instancia_num = atoi(argv[1]);
    if (instancia_num <= 0 || instancia_num > 20) {
        printf("Para executar o código, digite ./passeio x\nonde x é um número entre 1 e 20 que simboliza a instância utilizada\n");
        exit(0);
    }
    
    // Tabuleiro do jogo
    bool **tabuleiro = ler_instancia(instancia_num);

    computa_passeios(tabuleiro);

    return (1);
}

bool **ler_instancia(int instancia_num) {
    int i;
    
    // Montando o caminho para a instancia
    char arquivo_instancia[50];
    sprintf(arquivo_instancia, "./instancias/%d", instancia_num);
    
    // Ponteiro para a instância
    FILE* file;
 
    // Abrindo a instância em modo leitura
    file = fopen(arquivo_instancia, "r");
 
    if (NULL == file) {
        printf("Arquivo de instância não encontrado. Verifique se sua estrutura de diretórios está EXATAMENTE igual ao do Github\n");
        exit(0);
    }

    // Lendo o arquivo da instância
    fscanf (file, "%d", &n);
    fscanf (file, "%d", &m);

    // Alocando o tabuleiro dinamicamente
    // Usando calloc ao invés de malloc para inicializar todo o tabuleiro com zeros
    bool** tabuleiro = (bool**)calloc(n, sizeof(bool*));
    for (i = 0; i < n; i++) {
        tabuleiro[i] = (bool*)calloc(m, sizeof(bool));
    }

    return tabuleiro;
}
