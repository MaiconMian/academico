#ifndef CREATE_MATRIX_H_   
#define CREATE_MATRIX_H_
#include "position.h"
#include <stdbool.h>


bool testMovement(int newLine, int newColumn, bool **booleanMatrix);
bool **createAdjacentMatrix(bool **booleanMatrix);
bool **readFile(char *filename, Position *initial, Position *final);


#endif