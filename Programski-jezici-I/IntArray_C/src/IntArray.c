#include <stdlib.h>
#include "IntArray.h"

void IntArray__initalize(IntArray *intArray)
{
    intArray->length = 0;
    intArray->capacity = 1;
    intArray->array = (int *)malloc(sizeof(int) * intArray->capacity);
}

void IntArray__append(IntArray *intArray, int value)
{
    if (intArray->length == intArray->capacity)
    {
        intArray->capacity *= 2;
        intArray->array = (int *)realloc(intArray->array, sizeof(int) * intArray->capacity);
    }

    intArray->array[intArray->length] = value;
    intArray->length++;
}

void IntArray__free(IntArray *intArray)
{
    free(intArray->array);
}