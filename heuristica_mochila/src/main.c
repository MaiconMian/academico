#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include <string.h>
#include "gera_mochila.h"
#include "heuristica_construtiva.h"
#include "writefile.h"
#include "hill_climbing.h"
#include "evolutiva.h"

int main(){

    // variaveis para criação da mochila
    int peso_max, valor_max, opcao = 1;
    Mochila mochila;
    Solucao metricas;

    printf("=== GERADOR DO PROBLEMA DA MOCHILA BINÁRIA ===\n");
    geraMochila(&peso_max, &valor_max, &mochila);

    printf("Digite o algoritmo que deseja usar pra encontrar a solução:\n1. Busca Local (Hill Climbing)\n2. Evolutiva\n0. Sair\n> ");
    scanf("%d", &opcao);

    while (opcao < 0 || opcao > 2){
        printf("Opção inválida! Digite novamente!\n> ");
        scanf("%d", &opcao);
    }

    // seleciona opcao para encontrar solução
    while(opcao != 0){

        bool * solucao; // guarda a melhor solução encontrada 

        clock_t inicio, fim; // variáveis para contar o tempo de solução
        double tempo_gasto;

        if(opcao == 1){

            inicio = clock(); // inicia contagem
            solucao = construtivaMenorRazao(&mochila);
            solucao = hill_climbing(solucao, mochila);
            fim = clock(); // finaliza contagem

            tempo_gasto = ((double) (fim - inicio)) / CLOCKS_PER_SEC;
            metricas = avaliaSolucao(solucao, mochila);
            printf("Solução gerada por Hill Climbing (busca local). Tempo: %f, com peso %d/%d e benefício %d\n", tempo_gasto, metricas.peso, mochila.CapacidadeMáxima, metricas.beneficio);

        } else if (opcao == 2){
            inicio = clock(); // inicia contagem
            solucao = heuristica_evolutiva(&mochila, 8, 10);
            fim = clock(); // finaliza contagem

            tempo_gasto = ((double) (fim - inicio)) / CLOCKS_PER_SEC;
            metricas = avaliaSolucao(solucao, mochila);
            printf("Solução gerada pela evolutiva. Tempo: %f, com peso %d/%d e benefício %d\n", tempo_gasto, metricas.peso, mochila.CapacidadeMáxima, metricas.beneficio);
        }

        char name_exit_path[1000] = "../resultados/";
        char name[1000];

        printf("Digite o nome do arquivo de saida (com .txt):\n> ");
        scanf("%s", name);

        // cria um arquivo de saída com a solução encontrada
        strcat(name_exit_path, name);
        writefile(mochila, name_exit_path, solucao, tempo_gasto, metricas); 

        printf("\n\n\nDigite o algoritmo que deseja usar pra encontrar a solução:\n1. Busca Local (Hill Climbing)\n2. Evolutiva\n0. Sair\n> ");
        scanf("%d", &opcao);

        while (opcao < 0 || opcao > 2){
            printf("Opcao inválida! Digite novamente>");
            scanf("%d", &opcao);
        }
        
    }

    return 0;
    
}