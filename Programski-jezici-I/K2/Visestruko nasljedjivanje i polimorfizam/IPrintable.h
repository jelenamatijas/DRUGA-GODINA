#pragma once

#include <iostream>

class IPrintable
{
public:
    virtual void print(std::ostream &out) const =0;
};

std::ostream& operator<<(std::ostream& out, const IPrintable& other){
    other.print(out);
    return out;
}