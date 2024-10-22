#include <stdlib.h>
#include "queue.h"


/**
 * Function that initializes the queue.
 * @param   queue   pointer to the queue to be initialized
 * @return
 */
void startQueue(Queue *queue) {
    (*queue).size = 0;
    (*queue).items = malloc(1000 * sizeof(int)); 
    (*queue).first = (*queue).last = (*queue).items; 
}


/**
 * Function that adds a vertex to the queue.
 * @param   queue    queue where the new vertex will be inserted
 * @param   vertex   new vertex
 * @return
 */
void enqueue(Queue *queue, int vertex) {
    *((*queue).last) = vertex; 
    (*queue).last++; 
    (*queue).size++; 
}


/**
 * Function that removes topmost element of the stack.
 * @param   queue    queue where the element will be removed
 * @return  the removed vertex
 */
int dequeue(Queue *queue) {
    int vertexReturn = *((*queue).first); 
    (*queue).first++; 
    (*queue).size++;  
    return vertexReturn;
}