#ifndef BREADTH_SEARCH_H_   
#define BREADTH_SEARCH_H_
#include "position.h"
#include "movements.h"
#include "queue.h"
#include <stdbool.h>


void breadthsearch(MovementsStack *moves, int *top, bool **adjacentMatrix, Position final, Position initial);


#endif