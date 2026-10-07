#pragma once
#include "PriorityQueue.h"
#include "QueueFullException.h"

template <typename T, int N>
class BoundedPriorityQueue: public IQueue<T>
{
private:
    PriorityQueue<T> bpq;
public:
    BoundedPriorityQueue(std::function<bool(const T*, const T*)> sortingCriteria):bpq(sortingCriteria){}
    void enqueue(const T* t) override{
        if(bpq.size() > N){
            throw QueueFullException();
        }
        bpq.enqueue(t);
    }

    T* dequeue() override{
        return bpq.dequeue();
    }
    int size()const noexcept override{
        return bpq.size();
    }
};
