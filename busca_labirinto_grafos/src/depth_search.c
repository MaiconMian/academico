#include <stdbool.h>
#include "position.h"
#include "movements.h"
#include "depth_search.h"


/**
 * Function that verifies if is possible to access a vertex (if he wasn't accessed before).
 * @param   moves            pointer to the movements's stack
 * @param   top              top of movements' stack
 * @param   newVertex        new vertex to be verified
 * @return  true/false
 */
bool verifyElementsStack(MovementsStack *moves, int top, int newVertex) {
    for (int i = 0; i < top; i++){
        if (moves[i].vertex == newVertex){
            return false;
        }
    }
    return true;
}


/**
 * Function that implements the Depth-First search. It uses a queue as
 *  the implementation.
 * @param   moves            pointer to the movements' stack
 * @param   top              pointer to the top of movements' stack
 * @param   adjacentMatrix   pointer of pointer to the adjacent matrix
 * @param   final            coordinates of the exit point on the maze
 * @return  
 */
void depthSearch(MovementsStack *moves, int *top, bool **adjacentMatrix, Position final) {
    // while we aren't on the last vertex 
    while ((*top) != -1 && moves[(*top)].vertex != (final.x*10 + final.y)){
        for (int i = moves[(*top)].accessed_vertices; i < 100; i++){ // looping through the possible adjacent vertices
            moves[(*top)].accessed_vertices++; 
            // if the vertex is adjacent and wasn't accessed before (to prevent infinite loop)
            if((adjacentMatrix[moves[(*top)].vertex][i] == true) && verifyElementsStack(moves, *top, i)){ 
                (*top)++;
                moves[(*top)].vertex = i;
                moves[(*top)].accessed_vertices = 0;
                depthSearch(moves, top, adjacentMatrix, final);
                return;
            } 
        }
        // popping the vertex
        (*top)--; 
    }
}

