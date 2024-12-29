#pragma once
#include <iostream>

class IPrintable
{
public:
    virtual void print(std::ostream&)const = 0;
};

std::ostream& operator<<(std::ostream& out, const IPrintable& p){
    p.print(out);
    return out;
}