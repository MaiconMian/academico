#ifndef GERA_MOCHILA_H_   
#define GERA_MOCHILA_H_

typedef struct item_{
    int peso;
    int beneficio;
} Item;

typedef struct mochila_{
    Item * itens;
    int n_itens;
    int CapacidadeMáxima;
} Mochila;

void geraMochila (int * peso_max, int * valor_max, Mochila * mochila) ;

#endif