#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include "gera_mochila.h"

/**
 * Função que gera uma mochila binária com os itens possíveis de serem alocados
 * @param peso_max                      peso máximo que um item pode ter
 * @param valor_max                     valor máximo que um item pode ter
 * @param mochila                       informações da mochila á ser criada
 * 
*/
void geraMochila (int * peso_max, int * valor_max, Mochila * mochila) {

    srand(time(NULL));

    // solicita as entradas para criação da mochila

    printf("Digite o peso máximo da mochila\n> ");
    scanf("%d", &(*mochila).CapacidadeMáxima);

    while ((*mochila).CapacidadeMáxima <= 0){
        printf("O valor da mochila deve estar entre [1..N]! Digite novamente!\n> ");
        scanf("%d", &(*mochila).CapacidadeMáxima);
    }

    printf("Digite o número de itens que deseja gerar\n> ");
    scanf("%d", &(*mochila).n_itens);

    while ((*mochila).n_itens <= 0){
        printf("O número de itens deve estar entre [1..N]! Digite novamente\n> ");
        scanf("%d", &(*mochila).n_itens);
    }
    
    printf("Digite o peso máximo que um item pode ter\n> ");
    scanf("%d", peso_max);

    while (*peso_max <= 0){
        printf("O peso maximo deve estar entre [1..N]! Digite novamente\n> ");
        scanf("%d", peso_max);
    }

    printf("Digite o valor máximo que um item pode ter\n> ");
    scanf("%d", valor_max);

    while (*peso_max <= 0){
        printf("O valor maximo deve estar entre [1..N]! Digite novamente\n> ");
        scanf("%d", valor_max);
    }

    // aloca memória para os itens
    (*mochila).itens = malloc((*mochila).n_itens * sizeof(Item));

    // aleatoriza os mesmos
    for(int i = 0; i < (*mochila).n_itens; i++){
        (*mochila).itens[i].peso = rand() % *peso_max + 1;
        (*mochila).itens[i].beneficio = rand() % *valor_max + 1;
    }

}