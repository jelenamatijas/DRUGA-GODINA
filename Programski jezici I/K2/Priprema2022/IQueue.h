#pragma once
#include <functional>
#include <vector>

template <typename T>
class IQueue
{
public:
    virtual void enqueue(const T*) = 0;
    virtual T* dequeue() = 0;  
    virtual int size()const noexcept = 0;
    virtual ~IQueue() = default;
};