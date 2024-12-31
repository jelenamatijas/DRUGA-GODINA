#include "Node.h"
#include <iostream>
#include <vector>

Node::Node(int maxDegree, bool isLeaf) :maxDegree(maxDegree),
                                        isLeaf(isLeaf),
                                        keys(new FileInfo[2 * maxDegree - 1]),
                                        children(new Node* [2 * maxDegree]),
                                        numKeys(0) {}
Node::~Node() {
    delete[] keys;
    delete[] children;
}
void Node::traverse()
{
    int i;
    for (i = 0; i < numKeys; i++)
    {
        if (!isLeaf)
            children[i]->traverse();
        cout << " File name: "<< keys[i].fileName<<", number of words: "<<keys[i].numWords<<", number of characters: "<<keys[i].numCharacters<<endl;
    }

    if (!isLeaf)
        children[i]->traverse();
}

void Node::insertNonFull(FileInfo file)
{
    int i = numKeys - 1;

    if (isLeaf)
    {
        while (i >= 0 && keys[i].numWords > file.numWords)
        {
            keys[i + 1] = keys[i];
            i--;
        }

        keys[i + 1] = file;
        numKeys++;
    }
    else 
    {

        while (i >= 0 && keys[i].numWords > file.numWords)
            i--;

        // See if the found child is full
        if (children[i + 1]->numKeys == 2 * maxDegree - 1)
        {
            splitChild(i + 1, children[i + 1]);

            
            if (keys[i + 1].numWords < file.numWords)
                i++;
        }
        children[i + 1]->insertNonFull(file);
    }
}

void Node::splitChild(int indexOfChild, Node* child)
{
    Node* newNode = new Node(child->maxDegree, child->isLeaf);
    newNode->numKeys = maxDegree - 1;

    for (int j = 0; j < maxDegree - 1; j++)
        newNode->keys[j] = child->keys[j + maxDegree];

    if (child->isLeaf)
    {
        for (int j = 0; j < maxDegree; j++)
            newNode->children[j] = child->children[j + maxDegree];
    }

    child->numKeys = maxDegree - 1;

    
    for (int j = numKeys; j >= indexOfChild + 1; j--)
        children[j + 1] = children[j];

    children[indexOfChild + 1] = newNode;

    for (int j = maxDegree - 1; j >= indexOfChild; j--)
        keys[j + 1] = keys[j];

    keys[indexOfChild] = child->keys[maxDegree - 1];
    numKeys++;
}

Node* Node::search(int key)
{
    int i = 0;
    while (i < maxDegree && key > keys[i].numWords)
        i++;

    if (keys[i].numWords == key)
        return this;

    if (isLeaf == true)
        return NULL;

    return children[i]->search(key);
}

int Node::findKey(int numWords) {
    int index = 0;
    while (index < numKeys && keys[index].numWords < numWords) {
        index++;
    }
    return index;
}

void Node::remove(FileInfo file) {
    int index = findKey(file.numWords);
    if (index < numKeys && keys[index].numWords == file.numWords) {
        if (isLeaf)
            removeFromLeaf(index);
        else
            removefromNonleaf(index);
    }
    else {
        if (isLeaf)
            return;
        bool present = (index == numKeys);
        if (children[index]->numKeys < maxDegree) {
            fill(index);
        }
        
        if (present && index > numKeys) {
            children[index - 1]->remove(file);
        }
        else {
            children[index]->remove(file);
        }
        return;
    }
}

void Node::removeFromLeaf(int index) {
    for (int i = index + 1; i < numKeys;i++) {
        keys[i - 1] = keys[i];
    }
    numKeys--;
    return;
}

void Node::removefromNonleaf(int index) {
    FileInfo file = keys[index];

    if (children[index]->numKeys >= maxDegree) {
        FileInfo prev = getPrev(index);
        keys[index] = prev;
        children[index]->remove(prev);
    }
    else if (children[index + 1]->numKeys >= maxDegree) {
        FileInfo succ = getSucc(index);
        keys[index] = succ;
        children[index + 1]->remove(succ);
    }
    else {
        merge(index);
        children[index]->remove(file);
    }
    return;
}

FileInfo Node::getPrev(int index) {
    Node* temp = children[index];
    while (!temp->isLeaf) {
        temp = temp->children[temp->numKeys];
    }
    return temp->keys[temp->numKeys - 1];
}

FileInfo Node::getSucc(int index) {
    Node* temp = children[index+1];
    while (!temp->isLeaf) {
        temp = temp->children[0];
    }
    return temp->keys[0];
}

void Node::fill(int index) {
    if (index != 0 && children[index - 1]->numKeys >= maxDegree) {
        borrowFromPrev(index);
    }
    else if (index != numKeys && children[index + 1]->numKeys >= maxDegree) {
        borrowFromsucc(index);
    }
    else {
        if (index != numKeys) {
            merge(index);
        }
        else {
            merge(index - 1);
        }
    }
    return;
}

void Node::borrowFromPrev(int index) {
    Node* child = children[index];
    Node* sibling = children[index - 1];

    for (int i = child->numKeys - 1; i >= 0;i--) {
        child->keys[i + 1] = child->keys[i];
    }

    if (!child->isLeaf) {
        for (int i = child->numKeys; i >= 0; i--) {
            child->children[i + 1] = child->children[i];
        }
    }

    child->keys[0] = keys[index - 1];
    if (!child->isLeaf) {
        child->children[0] = sibling->children[sibling->numKeys];
    }

    keys[index - 1] = sibling->keys[sibling->numKeys - 1];
    child->numKeys++;
    sibling->numKeys--;
    return;
}

void Node::borrowFromsucc(int index)
{

    Node* child = children[index];
    Node* sibling = children[index + 1];

    child->keys[(child->numKeys)] = keys[index];

    if (!(child->isLeaf))
        child->children[(child->numKeys) + 1] = sibling->children[0];

    keys[index] = sibling->keys[0];

    for (int i = 1; i < sibling->numKeys; ++i)
        sibling->keys[i - 1] = sibling->keys[i];

    if (!sibling->isLeaf)
    {
        for (int i = 1; i <= sibling->numKeys; ++i)
            sibling->children[i - 1] = sibling->children[i];
    }

    child->numKeys++;
    sibling->numKeys--;

    return;
}

void Node::merge(int index)
{
    Node* child = children[index];
    Node* sibling = children[index + 1];

    child->keys[maxDegree - 1] = keys[index];

    for (int i = 0; i < sibling->numKeys; ++i)
        child->keys[i + maxDegree] = sibling->keys[i];

    if (!child->isLeaf)
    {
        for (int i = 0; i <= sibling->numKeys; ++i)
            child->children[i + maxDegree] = sibling->children[i];
    }

    for (int i = index + 1; i < numKeys; ++i)
        keys[i - 1] = keys[i];

    for (int i = index + 2; i <= numKeys; ++i)
        children[i - 1] = children[i];

    child->numKeys += sibling->numKeys + 1;
    numKeys--;

    delete(sibling);
    return;
}

void Node::getFiles(vector<FileInfo>& files)const {
    this->getFilesArray(files);
}

void Node::getFilesArray(vector<FileInfo>& files)const {
    int i;
    for (i = 0; i < numKeys; i++)
    {
        if (!isLeaf)
            children[i]->getFilesArray(files);
        files.push_back(keys[i]);
    }

    if (!isLeaf)
        children[i]->getFilesArray(files);
}