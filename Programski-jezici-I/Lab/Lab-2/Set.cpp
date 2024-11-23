#include "Set.h"

Set::Set(int n):numberOfVectors(n), index(0){
    if(numberOfVectors>0){
        vectors = new Vector[numberOfVectors];
    }else{
        vectors = nullptr;
        numberOfVectors = 0;
    }
}

Set::~Set()
{
    delete []vectors;
}

void Set::add(const Vector& v){
    if(index == numberOfVectors){
        Vector *newArray = new Vector[2*numberOfVectors];
        numberOfVectors*=2;
        for(int i=0;i<index;i++){
            newArray[i] = vectors[i];
        }
        delete []vectors;
        vectors = newArray;
    }
    vectors[index++] = v;
}
bool Set::equal(const Set& s){
    if(this->index != s.index)return false;
    else{
        for(int i=0;i<this->index;i++){
            if(this->vectors[i].get_index() != s.vectors[i].get_index())return false;
            else {
                for(int j=0;j<s.vectors[i].get_index();j++){
                    if(this->vectors[i].equalVectors(s.vectors[i])==false)return false;
                }
            }
        }
        return true;
    }
}