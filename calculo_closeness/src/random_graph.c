#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <stdbool.h>
#include "random_graph.h"

/**
	* Function that ckecks if there is an edge 
	* @param graph                      the matrix adjacent of the graph
	* @param from_node                  starting vertex
    * @param to_node                    destination vertex 
	* @param numberVertex               number vertex of graph

    * @return                           return true if exist edge 
*/
bool has_edge(int **graph, int from_node, int to_node, int numberVertex) {
    return (graph != NULL) && (from_node < numberVertex) && (to_node < numberVertex) && graph[from_node][to_node] != 0 && from_node != to_node;
}

/**
	* Function add edge for two vertex
	* @param graph                      the matrix adjacent of the graph
	* @param from_node                  starting vertex
    * @param to_node                    destination vertex 
    * @param max_weight                 maximum weight possible
	* @param numberVertex               number vertex of graph
*/
void add_edge(int **graph, int from_node, int to_node, int max_weight, int numberVertex) {
    if (to_node < numberVertex) {
        int weight = rand() % max_weight + 1;
        graph[from_node][to_node] = weight;
        graph[to_node][from_node] = weight;
    }
}

/**
	* Function add edge for two vertex
	* @param graph                      the matrix adjacent of the graph
	* @param number_of_nodes            number nodes of graph
    * @param max_edges                  maximum number of edges of a vertex
	* @param max_weight                 maximum weight possible

    * @return                            return the matrix adjacent of the graph
*/
int **generate_graph(int ** graph, int number_of_nodes, int max_edges, int max_weight ) {
    srand(time(NULL));

    for(int i = 0; i < number_of_nodes; i++) { // itera of all vertex
        int total_edges = rand() % max_edges + 1; // randomizes number of edges
        while(total_edges > 0){
            if(total_edges == 1) { // conect the vertex with your successor
                add_edge(graph, i, i+1,  max_weight, number_of_nodes);
                total_edges--;
            } else {
                int conected_node = rand() % (number_of_nodes);
                if(!has_edge(graph, i, conected_node, number_of_nodes)){ // if don't exist edge 
                    add_edge(graph, i, conected_node,  max_weight, number_of_nodes);
                }
                total_edges--;
            }
        }
    }
    return graph;
}