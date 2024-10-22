#include <stdbool.h>
#include <stdlib.h>
#include "position.h"
#include "movements.h"
#include "queue.h"


/**
 * Function that implements the Breadth-First Search. It uses a queue as
 *  the implementation.
 * @param   moves            pointer to the movements' stack
 * @param   top              pointer to the top of movements' stack
 * @param   adjacentMatrix   pointer of pointer to the adjacent matrix
 * @param   final            coordinates of the exit point on the maze
 * @param   initial          coordinates of the entrance point on the maze
 * @return
 */
void breadthsearch(MovementsStack *moves, int *top, bool **adjacentMatrix, Position final, Position initial) {
    Queue * queue = malloc(sizeof(Queue));
    int dad[1000], index, level[1000], verticeFinal = 10*final.x + final.y;

    startQueue(queue); // initializing the queue
    level[10*initial.x + initial.y] = 0; // initializing the start level
    index = 10*initial.x + initial.y; // putting the first point of the maze into the queue
    (*queue).size++;

    while ((*queue).size != 0 && index != verticeFinal){
        // walking through the adjacent points
        for (int i = 0; i < 100; i++){
            // if the vertex is adjacent and not the father
            if (adjacentMatrix[index][i] == true && i != dad[index]){
                dad[i] = index;
                level[i] = level[index] + 1;
                enqueue(queue, i);
            }
        }   
        index = dequeue(queue);
        
    }
    // putting the found path into a stack
    (*top) = level[index];
    int temp = level[index];
    while(temp != 0){
        moves[temp].vertex = index;
        index = dad[index];
        temp--;
    }
}
