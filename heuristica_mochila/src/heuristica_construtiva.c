#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include "gera_mochila.h"
#include "hill_climbing.h"

/**
 * Função que calcula o item de menor razão disponível
 * @param razao                         guarda as razoes dos itens
 * @param mochila                       informações do problema da mochila atual
 * @param pesototal                     peso total da solução atual
 * 
 * @return                              retorna o índice do item de menor razao
*/
int menorRazao (float * razao, Mochila mochila, int pesototal){

    float menor = INT_MAX; // a menor razão inicia infinita
    int indice_menor = -1;
    
    // busca em todos os itens
    for (int i = 0; i < mochila.n_itens; i++){
        // caso encontre um que não esteja na solução e não exploda a mochila
        if (razao[i] != -1 && razao[i] < menor && mochila.itens[i].peso + pesototal <= mochila.CapacidadeMáxima){
            menor = razao[i];
            indice_menor = i;
        }
    }

    // marca que o item já está na mochila
    if (indice_menor != -1){
        razao[indice_menor] = -1;
    }

    return indice_menor;
}

/**
 * Função que encontra uma solução peor uma heusristica construtiva
 * @param mochila                       informações do problema da mochila atual
 * 
 * @return                              retorna a solução encontrada
*/
bool * construtivaMenorRazao(Mochila * mochila) {

    bool * itens_selecionados = calloc((*mochila).n_itens, sizeof (bool)); // inicia  a solução
    float razao[(*mochila).n_itens];
 
    // calcula a razão de todos os itens
    for (int i = 0; i < (*mochila).n_itens; i++){
        razao[i] =( (float)(*mochila).itens[i].peso ) / ((float) (*mochila).itens[i].beneficio );
    }

    int pesoTotal = 0;
    int beneficioTotal = 0;

    while (pesoTotal < (*mochila).CapacidadeMáxima) 
    {
        int indice = menorRazao(razao, *mochila, pesoTotal);  // pega o item de menor razao e coloca na solução

        if (indice != -1 && pesoTotal + (*mochila).itens[indice].peso <= (*mochila).CapacidadeMáxima){
            pesoTotal += (*mochila).itens[indice].peso;
            beneficioTotal += (*mochila).itens[indice].beneficio;
            itens_selecionados[indice] = true;
        } else {
            break;
        }
        
    }

    return itens_selecionados;
}
