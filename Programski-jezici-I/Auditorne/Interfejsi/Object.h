#pragma once
#include "IPrintable.h"
#include "IStringConvertible.h"

class Object : public IPrintable, public IStringConvertible
{
protected:
    virtual void print(std::ostream& out)const override{
        out<<this->toString();
    }
public:
    virtual bool equals(const Object& other)const{
        return this == &other; 
    }
    virtual std::string toString()const override{
        return "Object";
    }

    virtual ~Object(){}
};