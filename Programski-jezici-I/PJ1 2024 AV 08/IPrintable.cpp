#include "IPrintable.h"

std::ostream& operator<<(std::ostream& os, const IPrintable& other)
{
    other.print(os);
    return os;
}