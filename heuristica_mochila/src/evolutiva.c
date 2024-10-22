#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <string.h>
#include "gera_mochila.h"
#include "hill_climbing.h"
#include "heuristica_construtiva.h"
#include "evolutiva.h"
#include <time.h>

/**
 * Função que cria uma solução completamente aleatória
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a solução aleatória criada
*/
bool * solucaoAleatoria (Mochila mochila){

    // criando vetores de solução
    bool * solucao = calloc(mochila.n_itens, sizeof (bool));
    bool * possiveis_analisados = calloc(mochila.n_itens, sizeof (bool));
    
    int peso_temporario = 0, indice = -1, analisados = 0;

    // enquanto a mochila não estiver cheia e ainda tiverem itens a serem colocados
    while (analisados < mochila.n_itens && peso_temporario < mochila.CapacidadeMáxima){
        indice = rand() % mochila.n_itens; // aleatoriza um item a ser colocado

        if(possiveis_analisados[indice] == false){ // verifica se já não está na solução
            possiveis_analisados[indice] = true;
            analisados++;
        } 
        // verifica a possibilidade de por na solução
        if (!solucao[indice] && peso_temporario + mochila.itens[indice].peso <= mochila.CapacidadeMáxima){
            solucao[indice] = true;
            peso_temporario = peso_temporario + mochila.itens[indice].peso;
        }
    }
    
    return solucao;
    
}

/**
 * Função que gera uma população semi aleátoria de indivíduos
 * @param populacao                     populacao a ser criada 
 * @param mochila                       informações do problema da mochila atual
 *
*/
void geraPopulacao (Populacao * populacao, Mochila mochila){

    // aloca o vetor dos individuos
    (*populacao).solucao = malloc(((*populacao).tamanho_populacao + (*populacao).tamanho_populacao/2) * sizeof (Solucao));
    
    // cria todos os indivíduos
    for (int i = 0; i < (*populacao).tamanho_populacao; i++){
        int probabilidade = rand() % 100;
        if (probabilidade < 50){ // 50% de chance de cirar uma solução aleatória
            (*populacao).solucao[i].vetor = solucaoAleatoria(mochila);
        } else {
            (*populacao).solucao[i].vetor = construtivaMenorRazao(&mochila);
        }
    }
    
}

/**
 * Função que seleciona os pais a serem combinados por torneio
 * @param populacao                     populacao a ser criada 
 * @param mochila                       informações do problema da mochila atual
 * @param i                             inicio do conjunto do torneio
 * @param j                             fim do conjunto do torneio
 * @param preenchidos                   guarda quais elementos já tem pai
 * 
 * @return                              retorna a melhor solução do conjunto
 *
*/
bool * torneio (Populacao populacao, Mochila mochila, int i, int j, bool * preenchidos){

    int melhor_aptdao = -1;
    int indice_melhor_aptidao = j;

    for (int k = i; k < j; k++){ // itera sob todos os indivíduos do conjunto
        int aptdao = aptidao(populacao.solucao[k].vetor, mochila); 
        if (aptdao > melhor_aptdao && !preenchidos[k]){ // verifica o melhor
            melhor_aptdao = aptdao;
            indice_melhor_aptidao = k;
        }
    }

    preenchidos[indice_melhor_aptidao] = true; // marca como preenchido

    return populacao.solucao[indice_melhor_aptidao].vetor; // retorna a solução
}

/**
 * Função que cruza duas soluções de forma aleatória
 * @param pai1                          primeiro pai a ser combinado
 * @param pai1                          segundo pai a ser combinado
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a combinação dos pais
 *
*/
bool * cruzamento(bool * pai1, bool * pai2, Mochila mochila) {

    bool * descendente = malloc(mochila.n_itens * sizeof(bool)); // aloca memória para o descendente
    
    for (int i = 0; i < mochila.n_itens; i++) {
        int pai = rand() % 2; //  chance de 50% de herdar os genes de cada pai
        if (pai == 0){
            descendente[i] = pai1[i];
        } else {
            descendente[i] = pai2[i];
        }
    }

    // verifica se o herdeiro é válido
    if (solucaoValida(descendente, mochila)){
        return descendente;
    } else {
        return cruzamento(pai1, pai2, mochila); // busca outro herdeiro
    }
    
}

/**
 * Função que muta uma solução
 * @param individuo                     individuo a ser mutado
 * @param mochila                       informações do problema da mochila atual
 * @param max                           guarda o numero de tentativas de mutacao disponíveis
 * 
 * @return                              retorna a combinação dos pais
 *
*/
bool * mutacao(bool * individuo, Mochila mochila, int max) {
    bool * individuo_mutado = calloc(mochila.n_itens, sizeof(bool));
    memcpy(individuo, individuo_mutado, mochila.n_itens * sizeof(bool));

    // aleatoriza os itens para mutação
    int indice1 = rand() % mochila.n_itens;
    int indice2 = rand() % mochila.n_itens;
    // muta o indivíduo
    individuo_mutado[indice1] = !individuo_mutado[indice1];
    individuo_mutado[indice2] = !individuo_mutado[indice2];

    // verifica se a mutação é valida
    if (!solucaoValida(individuo_mutado, mochila)){
        return individuo_mutado;
    } else if (max == 0) {
        return individuo;
    } else {
        free(individuo_mutado);
        return mutacao(individuo, mochila, max - 1); // busca outra mutação
    }
}


/**
 * Função que retorna a aptdao (beneficio) de um indivíduo
 * @param individuo                     individuo a ser analisado
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a aptdão da solução
 *
*/
int aptidao(bool * individuo, Mochila  mochila) {
    Solucao metricas = avaliaSolucao(individuo, mochila);
    if (metricas.peso > mochila.CapacidadeMáxima) {
        return -1;
    } else {
        return metricas.beneficio;
    }
}

/**
 * Função que realiza a heuristica evolutiva
 * @param mochila                       informações do problema da mochila atual
 * @param populacaoTam                  tamanho da população á ser criada
 * @param geracoes                      quantas gerações devem ser buscadas até o fim
 * 
 * @return                              retorna a aptdão da solução
 *
*/
bool * heuristica_evolutiva (Mochila * mochila, int populacaoTam, int geracoes){

    srand(time(NULL));

    Populacao populacao; 
    populacao.tamanho_populacao = populacaoTam;

    geraPopulacao(&populacao, *mochila); // cria uma população inicial

    for (int geracao = 0; geracao < geracoes; geracao++) { // busca chegar na geração limite

        // vetor para verificar os pais já selecionados
        bool * preenchidos = calloc(populacao.tamanho_populacao, sizeof(bool));

        // itera sob metade do tamanho pois encontra 2 pais a cada iteração
        for (int i = 0; i < (populacao.tamanho_populacao/2); i++){
            // encontra os pais (melhores de cada conjunto)
            bool * pai1 = torneio(populacao, *mochila, 0, populacao.tamanho_populacao/2, preenchidos);
            bool * pai2 = torneio(populacao, *mochila, populacao.tamanho_populacao/2, populacao.tamanho_populacao, preenchidos);
            // cruza
            bool * descendente = cruzamento(pai1, pai2, *mochila);
            // muta
            descendente = mutacao(descendente, *mochila, (*mochila).n_itens);
            // adiciona a solução
            populacao.solucao[populacao.tamanho_populacao + i].vetor = descendente;
        }

        // seleciona os melhores indivíduos
        preenchidos = calloc(populacao.tamanho_populacao, sizeof(bool));
        Populacao temporaria;
        temporaria.solucao = malloc((populacao.tamanho_populacao + populacao.tamanho_populacao/2) * sizeof (Solucao));
        for (int i = 0; i < populacao.tamanho_populacao; i++){
            temporaria.solucao[i].vetor = torneio(populacao, *mochila, 0, populacao.tamanho_populacao/2, preenchidos);
        }

        // coloca na solução
        for (int i = 0; i < populacao.tamanho_populacao; i++){
            populacao.solucao[i] = temporaria.solucao[i];
        }

    }

    bool * preenchidos = calloc(populacao.tamanho_populacao, sizeof(bool));
    return torneio(populacao, *mochila, 0, populacao.tamanho_populacao/2, preenchidos); // faz o torneio no grupo todo pra retornar a melhor solução
}

