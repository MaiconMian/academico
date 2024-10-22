#include <stdio.h>
#include <stdlib.h>
#include "writefile.h"

/**
	* Function created the file 
	* @param size                       number of vertex
    * @param file_name                  name of archive
    * @param graph                      graph 
    * @param closeness                  vector with closeness of all vertex 
*/
void writefile(int size, char *file_name, int **graph, float *closeness){

    // create the file
    FILE * f = fopen(file_name, "w");
    if (f == NULL){
        printf("Não foi possível abrir o arquivo \"%s\" para escrita!", file_name);
        exit(1);
    }

    // write the matrix 
    fprintf(f, "%d\n", size);
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            fprintf(f, "%d ", graph[i][j]);
        }
        fprintf(f, "\n");
    }

    //write the closeness 
    fprintf(f, "\n");
    for (int i = 0; i < size; i++){
        fprintf(f, "%f ", closeness[i]);
    }
    
    fclose(f);
    printf("Arquivo \"%s\" gerado com sucesso!\n", file_name);
}