#pragma once
#include "IArray.h"
#include "Array.h"
#include <memory>
#include "ArrayFullException.h"

template <typename T, int N>
class BoundedStack
{
    static_assert(std::is_copy_constructible_v<T>, "T must be copy constructible!");
    Array<std::shared_ptr<T>> array;
public:
    void append(const T& t){
        if(array.count() >= N){
            throw ArrayFullException();
        }
        array.append(std::make_shared<T>(t));
    }
};