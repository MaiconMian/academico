#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "depth_search.h"
#include "breadth_search.h"
#include "create_matrix.h"
#include "movements.h"
#include "position.h"


int main() {
    int option;
    char filepath[1000] = "../instancias/";
    char filename[1000];
    printf("Digite o nome do arquivo do labirinto (com .txt): ");
    scanf("%s", filename);
    strcat(filepath, filename);

    Position initial, final;
    bool **adjacentMatrix = readFile(filepath, &initial, &final);

    Position vertices[100];
    for (int i = 0; i < 10; i++){
        for (int j = 0; j < 10; j++){
            vertices[i*10+j].x = i;
            vertices[i*10+j].y = j;
        }
    }

    MovementsStack path[100];
    int top = 0;
    // first element of the stack is the initial vertex
    path[0].vertex = 10*initial.x + initial.y;
    path[0].accessed_vertices = 0; 

    printf("Selecione o algoritmo a ser rodado:\n1 - Busca em profundidade\n2 - Busca em largura\n> ");
    scanf("%1d", &option);
    while(option < 1 || option > 2) {
        printf("Opção inválida! Selecione o algoritmo a ser rodado:\n1 - Busca em profundidade\n2 - Busca em largura\n> ");
        scanf("%1d", &option);
    }

    if (option == 1) {
        depthSearch(path, &top, adjacentMatrix, final);
    } else if (option == 2){
        breadthsearch(path, &top, adjacentMatrix, final, initial);
    }

    for (int i = 0; i <= top; i++){
        printf("%d,%d\n", vertices[path[i].vertex].y, 9 - vertices[path[i].vertex].x);
    }

    return 0;
}