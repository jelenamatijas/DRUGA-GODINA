#pragma once
#include <ostream>

class IPrintable
{
public:
	virtual void print(std::ostream& os) const = 0;
};

std::ostream& operator<<(std::ostream& os, const IPrintable& other);