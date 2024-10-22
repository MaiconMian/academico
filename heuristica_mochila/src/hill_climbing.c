#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>
#include "gera_mochila.h"
#include "heuristica_construtiva.h"
#include "hill_climbing.h"

/**
 * Função que retorna o peso e o benefício da solução
 * @param solução                       solução a ser avaliada
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              informações da solução
*/
Solucao avaliaSolucao(bool *solucao, Mochila mochila) {
    Solucao resultado = {0, 0, NULL}; // inicia a solução
    for (int i = 0; i < mochila.n_itens; i++) { // itera sobre todos os itens 
        if (solucao[i]) {
            resultado.peso += mochila.itens[i].peso;
            resultado.beneficio += mochila.itens[i].beneficio;
        }
    }
    return resultado;
}

/**
 * Função que compara duas soluções 
 * @param solucao1                      solucao 1 a ser avaliada
 * @param solucao2                      solução 2 a ser avaliada
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a melhor solução
*/
bool * melhorSolucao (bool * solucao1, bool * solucao2, Mochila mochila){

    Solucao solucao01 = avaliaSolucao(solucao1, mochila);
    Solucao solucao02 = avaliaSolucao(solucao2, mochila);

    if (solucao01.beneficio > solucao02.beneficio){ // confere qual o melhor benefício
        return solucao1;
    } else if (solucao02.beneficio > solucao01.beneficio){
        return solucao2;
    } else {
        if(solucao01.peso < solucao02.peso){ // em caso de empate, desempata pelo peso
            return solucao1;
        } else {
            return solucao2;
        }
    }
}

/**
 * Função que encontra o vizinho de uma solução (coloca e retira um item da mochila)
 * @param solucao                      solução a ser buscado o vizinho
 * @param i                            indice a ser trocado
 * @param j                            indice a ser trocado
*/
void vizinhanca (bool * solucao, int i, int j){
    solucao[i] = !solucao[i];
    solucao[j] = !solucao[j];
}

/**
 * Função que verifica se uma solução é válida (seu peso não ultrapassa a mochila)
 * @param solução                       solução a ser avaliada
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              true : solucao valida | false : invalida
*/
bool solucaoValida (bool * solucao, Mochila mochila){
    Solucao solucao_atual = avaliaSolucao(solucao, mochila);
    return solucao_atual.peso <= mochila.CapacidadeMáxima;
}

/**
 * Função que executa a busca local por hill climbing
 * @param solução                       solução a ser desenvolvida
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a melhor solução encontrada
*/
bool * hill_climbing (bool * solucao, Mochila mochila){

    // soluções temporarias
    bool * temporaria = malloc(mochila.n_itens * sizeof (bool)),* tmp = malloc(mochila.n_itens * sizeof (bool));
    memcpy(temporaria, solucao, mochila.n_itens * sizeof(bool));

    // iteram sobre os itens para encontrar os vizinhos
    for (int i = 0; i < mochila.n_itens; i++){ 
        for (int j = i + 1; j < mochila.n_itens; j++){  
            memcpy(tmp, solucao, mochila.n_itens * sizeof(bool));
            vizinhanca(tmp, i, j);  // encontra um vizinho da solução

            if (solucaoValida(tmp, mochila)) { // verifica se a solução é válida
                bool *atual = melhorSolucao(tmp, temporaria, mochila); // verifica qual a melhor solução
                if (atual != temporaria) { 
                    memcpy(temporaria, atual, mochila.n_itens * sizeof(bool));
                }
            }

        }
    }

    // verifica se o esquema de vizinhança encontrou um vizinho melhor 
    bool * retorno = melhorSolucao(temporaria, solucao, mochila); 

    // se o melhor é a solução que foi iniciada, ele é otimo local
    if(retorno == solucao){
        return temporaria;
    } else {
        // busca os vizinhos da melhor solução vizinha 
        return hill_climbing (temporaria, mochila);
    }

}



