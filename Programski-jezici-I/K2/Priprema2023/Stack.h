#pragma once
#include <vector>
#include "IStack.h"
#include <iostream>
#include <fstream>
#include <string>
#include <functional>

template <typename T>
class Stack;

template <typename T> 
class StackImpl: public IStack<T>
{
protected:
    std::vector<T*> stack;
public:
    void push(T* t) override{
        stack.push_back(t);
    }

    T* pop() override{
        if(stack.empty())
            return nullptr;
        T* t= stack.back();
        stack.pop_back();
        return t;
    }

    int size()const noexcept override{
        return stack.size();
    }

    auto begin() { return stack.rbegin(); }
    auto end() { return stack.rend(); }
    auto cbegin() const { return stack.crbegin(); }
    auto cend() const { return stack.crend(); }

    Stack<T> transform(std::function<T(const T&)> f)const{
        static_assert(std::is_copy_constructible<T>::value, "Must be copy constructible.");
        Stack<T> s;
        for(auto data: stack){
            s.push(new T(f(*data)));
        }
        return s;
    }

};

template <typename T>
class Stack : public StackImpl<T>{};

template<>
class Stack<int>: public StackImpl<int>
{
public:
    static Stack<int> deserialize(const std::string& str){
        Stack<int> s;
        std::string value;
        for(auto c: str){
            if(c == ' '){
                s.push(new int(std::stoi(value)));
            }else{
                value+=c;
            }
        }
        return s;
    }

    std::string serialize()const{
        std::string result;
        for(const auto& r: stack){
            result += std::to_string(*r);
            result += ' ';
        }
        return result;
    }
};