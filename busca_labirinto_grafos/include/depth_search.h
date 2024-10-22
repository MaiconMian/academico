#ifndef DEPTH_SEARCH_H_   
#define DEPTH_SEARCH_H_
#include "position.h"
#include "movements.h"
#include <stdbool.h>


bool verifyElementsStack(MovementsStack *moves, int top, int newVertex);
void depthSearch (MovementsStack *moves, int *top, bool **adjacentMatrix, Position final);


#endif