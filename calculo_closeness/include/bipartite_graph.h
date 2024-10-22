#ifndef BIPARTITE_GRAPH_H_
#define BIPARTITE_GRAPH_H_

bool check_remaining_edgess(int **graph, int vertex, int max, int min);
void add_edge_bipartite(int **graph, int max, int min, int edge_per_node, int max_ewight, int current_vertex);
int **generate_bipartite_graph (int ** graph, int edge_per_node, int vertex_number, int max_weight, int set_size);

#endif
