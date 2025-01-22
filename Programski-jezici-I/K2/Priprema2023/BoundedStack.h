#pragma once
#include "Stack.h"
#include "IStack.h"
#include "StackFullExeption.h"

template <typename T, int N>
class BoundedStack: public IStack<T>
{
private:
    Stack<T> stek;

    BoundedStack(Stack<T>&& stek): stek(std::move(stek)){
        if(this->stek.size() > N)
            throw StackFullException();
    }
public:
    BoundedStack() = default;
    void push(T* t) override{
        if(stek.size() == N){
            throw StackFullException();
        }
        stek.push(t);
    }

    T* pop() override{
        return stek.pop();
    }

    int size()const noexcept override{
        return stek.size();
    }

    auto begin(){ return stek.begin();}
    auto end(){ return stek.end();}
    auto cbegin(){ return stek.begin();}
    auto cend(){ return stek.cend();}

    BoundedStack transform(std::function<T(const T&)> f)const{
        return BoundedStack(std::move(stek.transform(f)));
    }
};