#include <stdlib.h>
#include <stdio.h>
#include "readfile.h"

/**
	* Function read the file with graph
	* @param size                       number of vertex
    * @param file_name                  name of archive

    * @return                            return the matrix adjacent of the graph
*/
int **read_file(int *size, char *file_name) {

    // open the file
    FILE *f = fopen(file_name, "r");
    if (f == NULL) {
        printf("Não foi possível abrir o arquivo \"%s\" para leitura!", file_name);
        exit(1);
    }

    char character = '\0';
    int value;
    // read the number of vertex in first line
    fscanf(f, "%d\n", size);

    // create the adjacent matrix
    int **adjacent_matrix = (int **)malloc((*size) * sizeof(int *));
    for (int i = 0; i < (*size); i++) {
        adjacent_matrix[i] = (int *)malloc((*size) * sizeof(int));
    }

    // read the file
    for (int i = 0; character != EOF && i < (*size); i++) {
        for (int j = 0; j < (*size); j++) {
            character = fscanf(f, "%d ", &value);
            if (character != EOF){
                adjacent_matrix[i][j] = value;
            }
        }
    }

    fclose(f);
    return adjacent_matrix;
}