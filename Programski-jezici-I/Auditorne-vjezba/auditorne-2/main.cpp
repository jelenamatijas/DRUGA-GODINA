#include <stdio.h>
#include <stdlib.h>
#include "IntArray.h"

int main()
{
    IntArray intArray;

    for (int i = 0; i < 10; i++)
    {
        intArray.append(i);
    }

    for (int i = 0; i < intArray.getLength(); i++)
    {
        printf("%d\n", intArray.at(i));
    }

    //intArray.dispose();
}