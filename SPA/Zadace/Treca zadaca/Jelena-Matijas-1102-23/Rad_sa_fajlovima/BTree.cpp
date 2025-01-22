#include "BTree.h"
#include "iostream"
#include <vector>
#include <algorithm>

using namespace std;

BTree::BTree(int degree) :root(nullptr), degree(degree) {}
BTree::~BTree() {
    delete root;
}

void BTree::printBTree()
{
    if (root != NULL) root->traverse();
}

void BTree::insert(FileInfo file) {
    if (!root)
    {
        root = new Node(degree, true);
        root->keys[0] = file; 
        root->numKeys = 1; 
    }
    else
    {
        if (root->numKeys == 2 * degree - 1)
        {
            Node* s = new Node(degree, false);
            s->children[0] = root;
            s->splitNode(0, root);

            
            int i = 0;
            if (s->keys[0].numWords < file.numWords)
                i++;
            s->children[i]->insertNonFull(file);

            root = s;
        }
        else 
            root->insertNonFull(file);
    }
}

vector<FileInfo*> BTree::search(int wordCount) {
    vector<FileInfo*> files;
    search(root, wordCount, files); 
    return files;
}

void BTree::search(Node* node, int wordCount, vector<FileInfo*>& files) {
    if (!node) return;
    int i = 0;

    while (i < node->numKeys) {
        if (node->keys[i].numWords == wordCount) {
            files.push_back(&node->keys[i]);
        }
        i++;
    }

    if (!node->isLeaf) {
        for (int j = 0; j <= node->numKeys; ++j) {
            search(node->children[j], wordCount, files);
        }
    }
}

void BTree::deleteFiles(int numWords)
{
    if (!root) {
        cout << "The tree is empty." << endl;
        return;
    }

    vector<FileInfo*> duplicates = this->search(numWords);
    if (duplicates.size() == 0) {
        cout << "There is no such files." << endl;
    }
    else
    {
        for (int i = 0;i < duplicates.size();i++) {
            vector<FileInfo*> Duplicates = this->search(numWords);
            FileInfo* duplicate = Duplicates[0];
            cout << "Deleting file: " << duplicate->fileName << endl;
            deleteFile(*duplicate);
        }
    }

}

void BTree::deleteFile(const FileInfo& file) {
    if (!root) {
        cout << "The tree is empty." << endl;
        return;
    }

    root->remove(file);

    if (root->numKeys == 0) {
        Node* temp = root;
        if (root->isLeaf)
            root = nullptr;  
        else
            root = root->children[0]; 
        delete temp;        
    }
}


void BTree::sortByCharacter(int way) {

    vector<FileInfo> files;
    root->getFiles(files);

    if (way == 1) {
        for (int i = 1; i < files.size(); i++) {
            FileInfo key = files[i];
            int j = i;
            while (j > 0 && files[j - 1].numCharacters < key.numCharacters) {
                files[j] = files[j - 1];
                j--;
            }
            files[j] = key;
        }
    }
    else {
        for (int i = 1; i < files.size(); i++) {
            FileInfo key = files[i];
            int j = i;
            while (j > 0 && files[j - 1].numCharacters > key.numCharacters) {
                files[j] = files[j - 1];
                j--;
            }
            files[j] = key;
        }
    }

    for (FileInfo file:files)
    {
        cout << " File name: " << file.fileName << ", number of words: ";
        cout << file.numWords << ", number of characters: ";
        cout << file.numCharacters << endl;
    }
}



