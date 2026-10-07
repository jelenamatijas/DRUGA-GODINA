#pragma once
#include "IQueue.h"
#include <fstream>

template <typename T>
class PriorityQueue: public IQueue<T>
{
private:
    std::vector<T*> queue;
    std::function<bool(const T*, const T*)> sortingCriteria;
public:
    PriorityQueue(decltype(sortingCriteria) sortingCriteria): sortingCriteria(sortingCriteria){}
    void enqueue(const T* t) override{
        auto index = std::find_if(queue.cbegin(), queue.cend(),
        [*this, t](auto e){
            return sortingCriteria(t, e);
        });

        queue.insert(index, t);
    }

    T* dequeue() override{
        T* t= queue.back();
        queue.pop_back();
        return t;
    }  

    int size()const noexcept override{
        return queue.size();
    }

    void serialize(std::ofstream& out){
        for(auto t: queue){
            out<<*t<<" "<<std::endl;
        }
    }

    auto begin(){ return queue.begin();}
    auto end(){ return queue.end();}
    auto cbegin(){ return queue.cbegin();}
    auto cend(){ return queue.cend();}
};