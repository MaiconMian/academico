#ifndef RANDOM_GRAPH_H_
#define RANDOM_GRAPH_H_

bool has_edge(int **graph, int from_node, int to_node, int numberVertex);
void add_edge(int **g, int from_node, int to_node, int max_weight, int numberVertex);
int **generate_graph(int ** graph, int number_of_nodes, int max_edges, int max_weight );

#endif