typedef struct {
    int length;
    int capacity;
    int *array;
} IntArray;

void IntArray__initalize(IntArray* intArray);
void IntArray__append(IntArray* intArray, int value);
void IntArray__free(IntArray* intArray);