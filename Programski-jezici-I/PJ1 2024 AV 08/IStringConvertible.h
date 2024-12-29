#pragma once
#include <string>
#include <typeinfo>

class IStringConvertible
{
public:
	virtual std::string toString() const = 0;
	virtual operator std::string() const final
	{
		return this->toString();
	}
};