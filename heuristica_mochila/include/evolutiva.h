#ifndef EVOLUTIVA_H_   
#define EVOLUTIVA_H_
#include "hill_climbing.h"
#include "heuristica_construtiva.h"

typedef struct populacao_ {
    Solucao * solucao;
    int tamanho_populacao;
} Populacao;

bool * solucaoAleatoria (Mochila mochila);
void geraPopulacao (Populacao * populacao, Mochila mochila);
bool * torneio (Populacao  populacao, Mochila mochila, int i, int j, bool * preenchidos);
bool * heuristica_evolutiva (Mochila * mochila, int populacaoTam, int geracoes);
bool * cruzamento (bool * pai1, bool * pai2, Mochila mochila);
bool * mutacao(bool * individuo, Mochila mochila, int max);
int aptidao(bool * individuo, Mochila  mochila);

#endif