#ifndef HILL_CLIMBING_H_   
#define HILL_CLIMBING_H_
#include "gera_mochila.h"

typedef struct {
    int beneficio;
    int peso;
    bool * vetor;
} Solucao;

Solucao avaliaSolucao(bool *solucao, Mochila mochila);
bool * melhorSolucao (bool * solucao1, bool * solucao2, Mochila mochila);
void vizinhanca (bool * solucao, int i, int j);
bool solucaoValida (bool * solucao, Mochila mochila);
bool * hill_climbing (bool * solucao, Mochila mochila);

#endif