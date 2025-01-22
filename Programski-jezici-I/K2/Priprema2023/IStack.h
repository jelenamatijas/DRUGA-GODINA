#pragma once

template <typename T>
class IStack
{
public:
    virtual void push(T*) = 0;
    virtual T* pop() = 0;
    virtual int size()const noexcept = 0;
};