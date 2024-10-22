/*+-------------------------------------------------------------+
 | UNIFAL – Universidade Federal de Alfenas. 		        |
 | BACHARELADO EM CIENCIA DA COMPUTACAO. 			|
 | Trabalho..: Calculo do Closeness                             |
 | Disciplina: Algoritmos e Estrutura de Dados III              |
 | Professor.: Iago Carvalho                                    |
 | Aluno(s)..: Gustavo Rodrigues RA: 2023.1.08.034              |
 |             Laonardo Arantes RA: 2023.1.08.010               |
 |             Maicon Mian RA: 2023.1.08.013                    |
 |             Nycole Paulino RA: 2023.1.08.044                 |
 |             Pedro H. Botelho RA: 2023.1.08.027               |
 |             Victor H Tozzo RA: 2020.1.08.018                 |
 +-------------------------------------------------------------+*/

#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <limits.h> 
#include <string.h>
#include "closeness.h"
#include "dijsktra.h"
#include "readfile.h"
#include "tree_graph.h"
#include "bipartite_graph.h"
#include "writefile.h"
#include "random_graph.h"

int main() {
    int size = 0, option = 0;
    
    // choice options
    printf("O que você deseja fazer:\n1. Criar grafo e calcular o closeness\n2. Calcular o closeness de um grafo já existente\n0. Sair\n> ");
    scanf("%d", &option);
    while (option < 0 || option > 3) {
        printf("Não existe essa opção, digite novamente!\n > ");
        scanf("%d", &option);
    }

    while(option != 0) {
        int **adjacent_matrix; // create the adjacent matrix
        if (option == 1) {
            // collect informations for random graph
            int number_max_of_edges, weight_max, number_of_set;
            printf("Digite o tipo de grafo aleatório que você deseja gerar:\n1. Padrao\n2. Bipartido\n3. Arvore\n> ");
            scanf("%d", &option);
            while (option < 0 || option > 4) {
                printf("Não existe essa opção, digite novamente!\n> ");
                scanf("%d", &option);
            }
            printf("Digite o número de vértices do grafo [25-75]\n> ");
            scanf("%d", &size);
            while (size < 25 || size > 75) {
                printf("Valor inválido, digite um valor entre 25 e 75\n> ");
                scanf("%d", &size);
            }
            printf("Digite o número máximo de arestas que um vértice pode ter [1-%d]\n> ", size);
            scanf("%d", &number_max_of_edges);
            while (number_max_of_edges < 1 || number_max_of_edges > size) {
                printf("Valor inválido, digite um valor entre 0 e %d\n> ", size);
                scanf("%d", &number_max_of_edges);
            }

            printf("Digite o peso máximo que uma aresta pode ter [1-]\n> ");
            scanf("%d", &weight_max);
            while (weight_max <= 0) {
                printf("Valor inválido, digite novamente\n> ");
                scanf("%d", &weight_max);
            }

            // start the graph for a adjacent matrix 
            int ** graph = (int **)malloc(size * sizeof(int *));
            for(int i = 0; i < size; i++){
                graph[i] = (int *)malloc(size * sizeof(int));
                for (int j = 0; j <size;j++){
                    graph[i][j] = 0;
                }
            }

            // the tipe of graph
            switch (option) {
                case 1:
                    adjacent_matrix = generate_graph(graph, size, number_max_of_edges, weight_max); // create random graph
                    break;
                case 2:
                    // select set size of bipartite graph
                    printf("Digite o tamanho do primeiro conjunto do grafo\n> ");
                    scanf("%d", &number_of_set);
                    while ((number_of_set <= 0) || (number_of_set >= size)) {
                        printf("Valor inválido, digite novamente\n> ");
                        scanf("%d", &number_of_set);
                    }
                    adjacent_matrix = generate_bipartite_graph(graph, number_max_of_edges, size, weight_max, number_of_set); // create the bipartite graph
                    break;
                case 3:
                    adjacent_matrix = generate_tree_graph(graph, number_max_of_edges, size, weight_max); //create tree graph
                    break;
            }
        } else if (option == 2) { // for the already existing graph
            // keep the name of graph
            char filepath[1000] = "grafos/custom/";
            char filename[1000];
            printf("Digite o nome do arquivo do grafo (verifique se ele está presente em grafos/custom/) (com .txt):\n> ");
            scanf("%s", filename);
            strcat(filepath, filename);
            adjacent_matrix = read_file(&size, filepath); //read file with the graph
        } else {
            return 2;
        }

        float closeness_number[size];
        // calculate the closeness for all vertex in graph
        for (int i = 0; i < size; i++){
            closeness_number[i] = closeness(adjacent_matrix, size, i);
        }

	    // keep the name of file with graph and closeness
        char name_exit_path[1000] = "grafos/arquivos/";
        char name[1000];
        printf("Digite o nome do arquivo de saida (com .txt):\n> ");
        scanf("%s", name);
        strcat(name_exit_path, name);
        writefile(size, name_exit_path, adjacent_matrix, closeness_number); // create the file with closeness
        
        // select new option or exit
        printf("O que você deseja fazer:\n1. Criar grafo e calcular o closeness\n2. Calcular o closeness de um grafo já existente\n0. Sair\n> ");
        scanf("%d", &option);
        while (option < 0 || option > 3) {
            printf("Não existe essa opção, digite novamente!\n > ");
            scanf("%d", &option);
        }
    }
    return 0;
}
