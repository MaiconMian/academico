#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "writefile.h"
#include "heuristica_construtiva.h"
#include "hill_climbing.h"
#include "gera_mochila.h"

/**
 * Função que cria o arquivo com a solução encontrada
 * @param mochila                       informações do problema da mochila atual
 * @param solução                       solução encontrada
 * @param tempo_gasto                   tempo gasto para encontrar a solução
 * @param metrica                       metricas da solução
*/
void writefile(Mochila mochila, char *file_name, bool * solucao, double tempo_gasto, Solucao metrica){

    FILE * f = fopen(file_name, "w");

    if (f == NULL){
        printf("Não foi possível abrir o arquivo \"%s\" para escrita!", file_name);
        exit(1);
    }

    // mostra as informações no cabelaçho
    fprintf(f, "Capacidade da mochila: %d \n", mochila.CapacidadeMáxima);
    fprintf(f, "Solucao com peso %d, valor %d e tempo gasto %fs\n", metrica.peso, metrica.beneficio, tempo_gasto);
    fprintf(f, "item | peso | valor | esta na mochila\n");

    // coloca as informações da solução noa arquivo
    for (int i = 0; i < mochila.n_itens; i++){
        if (solucao[i]){
            fprintf(f, "%d %d %d 1\n", i, mochila.itens[i].peso, mochila.itens[i].beneficio);
        } else {
            fprintf(f, "%d %d %d 0\n", i, mochila.itens[i].peso, mochila.itens[i].beneficio);
        }
    }

    fclose(f);
    printf("Arquivo \"%s\" gerado com sucesso!\n", file_name);
}