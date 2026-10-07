#pragma once
#include "IArray.h"
#include <vector>
#include <stdexcept>
#include <functional>

template <typename T>
class Array: public IArray<T>
{
protected:
    std::vector<T> data;
public:
    virtual void append(const T& t) override{
        data.push_back(t);
    }

    virtual void erase(int i) override{
        if(i<0 || i>=count())
            throw std::out_of_range("Index out of range!");
        data.erase(data.begin()+i);
    }

    virtual T& at(int i) override{
        if(i<0 || i>=count())
            throw std::out_of_range("Index out of range!");
        return data[i];
    }

    virtual const T& at(int i) const override{
        if(i<0 || i>=count())
            throw std::out_of_range("Index out of range!");
        return data[i];
    }

    virtual int count() const noexcept override{
        return data.size();
    }

    template <typename R>
    Array<R> transform(std::function<R(const T&)> f)const{
        Array<R> arr;
        for(auto i: data){
            arr.append(f(i));
        }
        return arr;
    }
};
