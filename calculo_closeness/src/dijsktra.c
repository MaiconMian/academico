#include <stdbool.h>
#include <limits.h>
#include "dijsktra.h"

/**
	* Function starts the intiates structures dad and distance for the dijsktra
	* @param number_vertex              number of vertex for the graph
	* @param inicial_vertex             initial vertex for minimum path
	* @param dad                        vector keep the dad of all vertex 
	* @param distance                   vector keep the distance for the initial vertex of all vertex
*/
void start(int number_vertex, int initial_vertex, int *dad, int *distance) {
    for (int i = 0; i < number_vertex; i++){
        distance[i] = INT_MAX;
        dad[i] = -1;
    }
    distance[initial_vertex] = 0; // the distance of initial vertex is 0 
}

/**
	* Function starts the intiates estrutures dad and distance for the dijsktra
	* @param adjacent_matrix            the matrix adjacent for the graph
	* @param distance                   vector keep the distance for the initial vertex of all vertex
	* @param dad                        vector keep the dad of all vertex 
	* @param v                          adjacent vertex of u
	* @param u                          current vertex analyzed 
	
*/
void relax(int **adjacent_matrix, int *distance, int *dad, int v, int u) {
    // checking if go through the vertex u is more cheap
    if (distance[v] >= distance[u] + adjacent_matrix[u][v]){
        distance[v] = distance[u] + adjacent_matrix[u][v];
        dad[v] = u;
    }
}

/**
	* Function extract the vertex with minimum distance 
	* @param Q                          saves the vertices that have not yet found the shortest path
	* @param distance                   vector keep the distance for the initial vertex of all vertex
	* @param number_vertex              number of vertex of graph

    * @return                           the minimum vertex or the -1 when there are no more vertices
*/
int extract_minimum(bool *Q, int *distance, int number_vertex) {
    int smaller = INT_MAX;
    int number_return = -1;

    // check the Q seaching vertices 
    for (int i = 0; i < number_vertex; i++){
        if (Q[i] && distance[i] < smaller){
            smaller = distance[i];
            number_return = i;
        }
    }

    return number_return;
}

/**
	* Function calculate the dijsktra of graph with initial vertex 
	* @param adjacent_matrix            the matrix adjacent for the graph
	* @param inicial_vertex             initial vertex for minimum path
	* @param distance                   vector keep the distance for the initial vertex of all vertex
	* @param dad                        vector keep the dad of all vertex 
	* @param number_vertex              number of vertex for the graph
*/
void dijsktra(int **adjacent_matrix, int initial_vertex, int *distance, int *dad, int number_vertex) {
    start(number_vertex, initial_vertex, dad, distance); // start the structures
    bool Q[number_vertex];

    for (int i = 0; i < number_vertex; i++){
        Q[i] = true;
    }

    int current_vertex = extract_minimum(Q, distance, number_vertex); // extract the  minimum
    
    // while there are unrelaxed vertex
    while (current_vertex != -1) {
        Q[current_vertex] = false;
        for (int i = 0; i < number_vertex; i++){
            if (adjacent_matrix[current_vertex][i] != 0){ // for all adjacent vertex
                relax(adjacent_matrix, distance, dad, i, current_vertex); // relax 
            }
        }
        current_vertex = extract_minimum(Q, distance, number_vertex); 
    }
}
