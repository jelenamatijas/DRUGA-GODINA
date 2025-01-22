#pragma once

#include "IPrintable.h"
#include "IStringConvertable.h"

class Object: public IPrintable, public IStringConvertable
{
protected:
    virtual void print(std::ostream &out) const override{
        out<<this->convertToString();
    }
public:
    virtual bool equals(const Object& other)const {
        return this == &other;
    }
    
    virtual std::string convertToString()const override{
        return "Object";
    }

    virtual Object* clone()const=0;

    virtual ~Object() {}
};