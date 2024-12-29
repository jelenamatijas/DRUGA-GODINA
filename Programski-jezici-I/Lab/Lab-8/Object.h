#pragma once
#include "IPrintable.h"
#include "IStringConvertible.h"

class Object : virtual public IPrintable, virtual public IStringConvertible
{
protected:
	virtual void print(std::ostream& os) const override
    {
        os << this->toString();
    }
public:	
	virtual bool equals(const Object& other) const
	{
		return this == &other;
	}
	virtual std::string toString() const override
	{
		return "Object";
	}

	virtual Object* clone() = 0;
	virtual ~Object() {}
};