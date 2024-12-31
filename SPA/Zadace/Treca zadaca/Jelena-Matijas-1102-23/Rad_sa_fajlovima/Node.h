#pragma once
#include <string>
#include <vector>

using namespace std;

struct FileInfo {
    int numWords;
    int numCharacters;
    string fileName;
};

class Node
{
    int maxDegree;
    bool isLeaf;
    FileInfo* keys;
    Node** children; // numChildren = numKeys+1 
    int numKeys;     
public:
    Node(int maxDegree, bool leaf);
    ~Node();

    void getFiles(vector<FileInfo>& files)const;

    void insertNonFull(FileInfo file);
    void splitChild(int indexOfChild, Node* child);
    void traverse();

    Node* search(int key);  

    void remove(FileInfo file);
    void removeFromLeaf(int index);
    void removefromNonleaf(int index);
    FileInfo getPrev(int index);
    FileInfo getSucc(int index);
    void fill(int index);
    void borrowFromPrev(int index);
    void borrowFromsucc(int index);
    void merge(int index);
    int findKey(int numWords);

    friend class BTree;
private:
    void getFilesArray(vector<FileInfo>& files)const;
};
