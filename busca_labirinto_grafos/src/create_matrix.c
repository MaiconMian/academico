#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "create_matrix.h"
#include "position.h"


/**
 * Function that tests if a movement is possible.
 * @param   newLine          new line
 * @param   newColumn        new column
 * @param   booleanMatrix    pointer of pointer to the boolean matrix (true on possible path, false otherwise)
 * @return  true/false
 */
bool testMovement(int newLine, int newColumn, bool **booleanMatrix) {
    return(newLine >= 0 && newLine < 10 && newColumn >= 0 && newColumn < 10 && booleanMatrix[newLine][newColumn]);
}


/**
 * Function that creates the adjacent matrix.
 * @param   booleanMatrix    pointer of pointer to the boolean matrix (true on possible path, false otherwise)
 * @return  pointer of pointer to the adjacent matrix
 */
bool **createAdjacentMatrix(bool **booleanMatrix) {
    bool **ajdacentMatrix = (bool**)calloc(100, sizeof(bool*));
    for (int i = 0; i < 100; i++) {
        ajdacentMatrix[i] = (bool*)calloc(100, sizeof(bool));
    }
    // possible moves on both axes
    int directionX[4] = {0, 0, 1, -1}; 
    int directionY[4] = {1, -1, 0, 0};

    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            if (booleanMatrix[i][j]) { // if there's an edge
                for (int n = 0; n < 4; n++){ // looping through the 4 possible moves
                    int newLine = i + directionX[n];
                    int newColumn = j + directionY[n];
                    if (testMovement(newLine, newColumn, booleanMatrix)) {
                        // connect the vertices
                        ajdacentMatrix[10*i+j][10*newLine + newColumn] = true; 
                        ajdacentMatrix[10*newLine + newColumn][10*i+j] = true;
                    }
                }
            }
        }
    }
    return ajdacentMatrix;
}


/**
 * Function that reads the coosen file and creates the boolean matrix.
 *  It founds and stores the initial and final points on the maze.
 *  It also calls the createAdjacentMatrix function to return the adjacent
 *  matrix.
 * @param   filename    name of the chosen file
 * @param   initial     coordinates of the entrance point on the maze
 * @param   final       coordinates of the exit point on the maze
 * @return  pointer of pointer to the adjacent matrix
 */
bool **readFile(char *filename, Position *initial, Position *final) {
    char character = '\0';
    bool** booleanMatrix = (bool**)calloc(10, sizeof(bool*));
    for (int i = 0; i < 10; i++) {
        booleanMatrix[i] = (bool*)calloc(10, sizeof(bool));
    }

    FILE *f = fopen(filename, "r");
    if (f == NULL) {
        printf("Unable to open file!\n");
        exit(1);
    }

    for(int i = 0; i < 10 && character != EOF; i++) {
        for (int j = 0; j < 10 && character != EOF; j++) {
            character = fgetc(f);
            switch(character) {
                case 'E':
                    booleanMatrix[i][j] = true;
                    (*initial).x = i;
                    (*initial).y = j;
                    break;
                case 'S':
                    booleanMatrix[i][j] = true;
                    (*final).x = i;
                    (*final).y = j;
                    break;
                case 'X':
                    booleanMatrix[i][j] = false;
                    break;
                case '0':
                    booleanMatrix[i][j] = true;
                    break;
                default:
                    j--;
                    break;
            }
        }
    }

    fclose(f);
    return createAdjacentMatrix(booleanMatrix);
}