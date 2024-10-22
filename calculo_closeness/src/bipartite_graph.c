#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <stdbool.h>
#include "bipartite_graph.h"

/**
	* Function that checks if there are vertices avaliable in the other set
	* @param graph                      the matrix adjacent of the graph
	* @param vertex                     vertex to be analyzed 
    * @param max                        the maximum size of analized set
    * @param min                        the minimum size of analized set

    * @return                           return true if exist possible edge
*/
bool check_remaining_edgess(int **graph, int vertex, int max, int min) {
    for (int i = 0; i < max; i ++){
    	int confere = i + min;
        if (graph[vertex][confere] == 0){ // there are no vertices
            return true;
        }
    }
    return false;
}

/**
	* Function add edges for one vertex 
	* @param graph                      the matrix adjacent of the graph
    * @param max                        the maximum size of analized set
    * @param min                        the minimum size of analized set
    * @param max_ewight                 maximum weight a vertex can have
    * @param edge_per_node              number maximum of edges per node
    * @param current_vertex             vertex to be analyzed 
*/
void add_edge_bipartite(int **graph, int max, int min, int edge_per_node, int max_ewight, int current_vertex) {
    srand(time(NULL));
    int newVertex, aleatory_number_edges = rand() % edge_per_node + 1;
    // make sure that the number of edges is possible
    while (aleatory_number_edges > max && aleatory_number_edges >= 1){
        aleatory_number_edges = rand() % edge_per_node + 1;
    }
    
    // while exist edges to add
    while (aleatory_number_edges != 0){
        int peso = rand() % max_ewight + 1; // random the weight
        newVertex = rand() % max + min;
        if (check_remaining_edgess(graph, current_vertex, max, min)){ // check the existence of vertices in another set
            while (graph[current_vertex][newVertex] != 0 ){
                newVertex = rand() % max + min;
            }
            graph[newVertex][current_vertex] = peso;
            graph[current_vertex][newVertex] = peso;
        }
        aleatory_number_edges--;
    }
}

/**
	* Function add edge for two vertex
	* @param graph                      the matrix adjacent of the graph
    * @param edge_per_node              number maximum of edges per node
    * @param vertex_number              number of vertex 
    * @param max_ewight                 maximum weight a vertex can have
    * @param set_size                   size of a fisrt set

    * @return                            return the matrix adjacent of the graph
*/
int **generate_bipartite_graph(int ** graph, int edge_per_node, int vertex_number, int max_weight, int set_size) {
    srand(time(NULL));
    int set1 = set_size, set2 = vertex_number - set1;   

    // create the edges for a first set 
    for (int i = 0; i < set1; i++){
        add_edge_bipartite(graph, set2, set1, edge_per_node, max_weight, i);
    }

    // create edges for a second set
    for (int i = set1; i < vertex_number; i++){
        add_edge_bipartite(graph, set1, 0, edge_per_node, max_weight, i);
    }
    
    return graph;
}
