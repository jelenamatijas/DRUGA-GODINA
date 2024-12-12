#pragma once
#include <iostream>
#include <fstream>

using namespace std;

typedef struct node{
    string id;
    int currAddr;
    int numRides;
    bool available;
    struct node *left, *right;
    
    node(const string& id, int currAddr):id(id), currAddr(currAddr), numRides(0),available(true), left(nullptr), right(nullptr){}
}T;

class Taxi{
    T *root;
public:
    Taxi(const string& input):root(nullptr){
        initialize(input);
    }
    ~Taxi(){
        deleteTaxis(root);
    }

    void print()const{
        cout<<"Taxis datas:"<<endl;
        visitTree(root);
    }

    T* closestAvailableTaxi(int address, int& minDist)const{
        T* closest = nullptr;
        findClosest(root, address, minDist, closest);
        return closest;
    }

    T* makeFree(int address, int& minDist){
        return makeClosestFree(root, address, minDist);
    }

    void updateBST(T* taxi, int newAddr){
        if(!taxi)
            return;
        cout<<endl<<"--------------------------";
        print();
        cout<<endl<<"--------------------------";
        root = deleteOneTaxi(root, taxi->id);
        cout<<endl<<"--------------------------";
        print();
        cout<<endl<<"--------------------------";
        root = add(root,taxi->id, newAddr);
        cout<<endl<<"--------------------------";
        print();
        cout<<endl<<"--------------------------";
    }

private:
    T* makeClosestFree(T* node, int address, int& minDist){
        if(!node)
            return nullptr;
        
        T* closest = nullptr;
        int dist = abs(node->currAddr - address);
        if(!node->available && dist<minDist){
            minDist = dist;
            closest = node;
        }

        T* lClosest = makeClosestFree(node->left, address, minDist);
        T* rClosest = makeClosestFree(node->right, address, minDist);

        if(lClosest && abs(lClosest->currAddr - address) < minDist)
            closest = lClosest;
        if(rClosest && abs(rClosest->currAddr - address) < minDist)
            closest = rClosest;
        
        if(closest)
            closest->available = true;
        
        return closest;
    }

    T* findClosest(T* node, int address, int& minDist, T*& closest)const{
        if(!node)
            return nullptr;
        
        int dist = abs(node->currAddr - address);
        if(node->available && dist<minDist){
            minDist = dist;
            closest = node;
        }

        if (address < node->currAddr) {
            return findClosest(node->left, address, minDist, closest);
        } else {
            return findClosest(node->right, address, minDist, closest);
        }
    }

    void visitTree(T* node)const{
        if(!node)
            return;
        visitTree(node->left);
        cout<<"ID: "<<node->id<<endl;
        cout<<"Current address: "<<node->currAddr<<" Number of rides: "<<node->numRides<<" Available: "<<node->available<<endl;
        visitTree(node->right);
    }

    T* add(T* node, string id, int addr){
        if (!node) {
            return new T(id, addr);
        }

        if (id < node->id) {
            node->left = add(node->left, id, addr);
        }
        else if (id > node->id) {
            node->right = add(node->right, id, addr);
        } else {
            cout << "WARNING: Duplicate address found for ID= " << id << ", Address= " << addr <<"ID: "<<node->id<<"Address: "<<node->currAddr<< endl;
        }

        return node; 
    }

    void deleteTaxis(T* node){
        if(!node)return;
        deleteTaxis(node->left);
        deleteTaxis(node->right);
        delete node;
    }

    T* deleteOneTaxi(T* node, string id){
    if(!node)
        return nullptr;
    if(id < node->id){
        node->left = deleteOneTaxi(node->left, id);
    } else if(id > node->id){
        node->right = deleteOneTaxi(node->right, id);
    } else {
        if(!node->left && !node->right){
            delete node;
            return nullptr;  
        }
        else if(!node->left){
            T* pom = node->right;
            delete node;
            return pom;  
        }
        else if(!node->right){
            T* pom = node->left;
            delete node;
            return pom;  
        }
        else{
            T* pom = node->left;
            while(pom->right)
                pom = pom->right;
            node->id = pom->id;
            node->currAddr = pom->currAddr;
            node->numRides = pom->numRides;
            node->available = pom->available;
            node->left = deleteOneTaxi(node->left, pom->id);
        }
    }
    return node;
}

    void initialize(const string& input){
        ifstream file(input);
        if(!file.is_open()){
            cout<<"ERROR: there is no such file."<<endl;
            return;
        }

        string line;

        while(getline(file, line)){
            int addr = 0;
            string id="";
            int i=0;
            while(line[i]!=','){
                id+=line[i];
                i++;
            }
            i++;
            for(;i<line.length();i++){
                addr*=10;
                addr+=line[i]-'0';
            }
            root=add(root,id, addr);

        }
        file.close();
    }
};