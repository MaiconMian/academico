#ifndef DIKSTRA_H_
#define DIKSTRA_H_
#include <stdbool.h>

void start(int number_vertex, int initial_vertex, int *dad, int *distance);
void relax(int **adjacent_matrix, int *distance, int *dad, int v, int u);
int extract_minimum(bool *Q, int *distance, int number_vertex);
void dijsktra(int **adjacent_matrix, int initial_vertex, int *distance, int *dad, int number_vertex);

#endif