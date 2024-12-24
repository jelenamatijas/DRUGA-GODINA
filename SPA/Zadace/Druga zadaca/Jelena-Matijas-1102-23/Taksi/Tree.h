#pragma once
#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

typedef struct node{
    string id;
    int currAddr;
    int numRides;
    bool available;
    int distance;
    struct node *left, *right;
    
    node(const string& id="", int currAddr=0, int numRides=0, bool available=0,int distance=0):id(id), 
                                                                                currAddr(currAddr), 
                                                                                numRides(numRides),
                                                                                available(available), 
                                                                                distance(distance),
                                                                                left(nullptr), 
                                                                                right(nullptr){}
}TAXI;

class Tree {
    TAXI* root;

public:
    Tree() : root(nullptr) {}
    ~Tree() {
        deleteTaxis(root);
    }

    void add(int distance, string id, int addr, int numRides, bool available) {
        root = add(root, distance, id, addr, numRides, available);
    }

    TAXI* closestTaxi() {
        return minimalDistancedTaxi(root);
    }

    void visitTree()const{
        visit(root);
    }

private:
    void visit(TAXI* node)const{
        if(node){
            visit(node->left);
            cout<<node->id<<" on address "<<node->currAddr+1<<". Distance: "<<node->distance<<endl;
            visit(node->right);
        }
    }
    TAXI* add(TAXI* node, int distance, string id, int addr, int numRides, bool available) {
        if (node == nullptr) {
            return new TAXI(id, addr, numRides, available, distance);
        }
        if (distance < node->distance) {
            node->left = add(node->left, distance, id, addr, numRides, available);
        } else if (distance >= node->distance) {
            node->right = add(node->right, distance, id, addr, numRides, available);
        }
        return node;
    }

    void deleteTaxis(TAXI* node) {
        if (!node) return;
        deleteTaxis(node->left);
        deleteTaxis(node->right);
        delete node;
    }

    TAXI* minimalDistancedTaxi(TAXI* node) {
        if (node == nullptr) {
            return nullptr;
        }

        TAXI* current = node;
        TAXI* smallestAvailable = nullptr;


        while (current) {
            if (current->available) {
                smallestAvailable = current;
            }
            current = current->left;
        }
        return smallestAvailable;
    }
};

