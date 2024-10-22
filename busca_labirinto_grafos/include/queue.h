#ifndef QUEUE_H_   
#define QUEUE_H_


typedef struct queue {
    int *first, *last;
    int size;
    int *items;
} Queue;


void startQueue(Queue *queue);
void enqueue(Queue *queue, int vertex);
int dequeue(Queue *queue);

#endif