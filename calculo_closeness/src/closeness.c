#include "closeness.h"
#include "dijsktra.h"

/**
	* Function calculate the closeness of vertex
	* @param adjacent_matrix            the matrix adjacent for the graph
	* @param number_vertex              number of vertex for the graph
	* @param inicial_vertex             vertex for calculate the closeness
*/
double closeness(int **adjacent_matrix, int number_vertex, int initial_vertex) {
    int dad[number_vertex], distance[number_vertex];
    float total = 0;
    // caal the dikstra, the distance return with minimum path of all vertices
    dijsktra(adjacent_matrix, initial_vertex, distance, dad, number_vertex); 
    for (int i = 0; i < number_vertex; i++){
        total = total + distance[i]; // sum distance
    }
    return total/(number_vertex-1); // calculate the average 
}
