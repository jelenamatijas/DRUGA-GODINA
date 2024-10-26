class IntArray
{
private:
    int length;
    int capacity;
    int *array;
public:
    IntArray();
    void append(int value);
    void dispose();
    int getLength() const;
    
    // Assume valid index
    int at(int index) const;
};