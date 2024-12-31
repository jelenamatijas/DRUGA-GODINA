#pragma once
#include "Node.h"
#include <vector>

using namespace std;

class BTree
{
    Node* root; 
    int degree;  
public:
    BTree(int degree);
    ~BTree();

    void printBTree();

    vector<FileInfo*> search(int wordCount);
    void insert(FileInfo file);
    void deleteFiles(int numWords);
    void sortByCharacter(int way);
private:
    void search(Node* node, int wordCount, vector<FileInfo*>& file);
};
