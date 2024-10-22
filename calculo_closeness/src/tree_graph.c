#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include "tree_graph.h"

/**
	* Function add edge for two vertex
	* @param array                      saves vertices already added to the tree
    * @param size                       number of vertex

    * @return                           true if exist vertex don't added 
*/
bool check_remaining_edges(int array[], int size) {
    for (int i = 0; i < size; i ++) {
        if (array[i] == 0) {
            return true;
        }
    }
    return false;
}

/**
	* Function add edge for two vertex
	* @param graph                      the matrix adjacent of the graph
    * @param number_of_edges            number maximum of edges per node
    * @param number_of_nodes            number of vertex 
    * @param max_ewight                 maximum weight a vertex can have

    * @return                            return the matrix adjacent of the graph
*/
int **generate_tree_graph(int ** graph, int number_of_edges, int number_of_nodes, int max_weight) {
    srand(time(NULL));

    //start the tree vector
    int tree[number_of_nodes];
    for (int i = 0; i < number_of_nodes; i++) {
        tree[i] = 0;
    }

    // itera for all vertex
    for (int i = 0; i < number_of_nodes; i++){

        int aleatory_number_edges = rand() % number_of_edges + 1;
        tree[i] = 1;

        for (int j = i; aleatory_number_edges != 0 && check_remaining_edges(tree, number_of_nodes); j++){ // try to connect vertex 
            // if is possible, create the edge
            if (tree[j] == 0 && j != i){
                int weight = rand() % max_weight + 1;
                tree[j] = 1;
                graph[i][j] = weight;
                graph[j][i] = weight;
                aleatory_number_edges--;
            }
        }
        
    }
    return graph;
}
