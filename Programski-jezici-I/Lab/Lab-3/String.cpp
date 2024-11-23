#include <iostream>
#include <cstring>
#include "String.h"


String::String(const char *s) : length(strlen(s)), data(new char[length + 1]) 
{ 
    strcpy((char*)data, s); 
}

String::String(const String &other):String(other.data){}

String::String(String &&other):length(other.length), data(other.data)
{
    other.data = nullptr;
}

String::~String()
{
    delete[] data;
}

int String::strlength() const
{
    return strlen(data);
}

char String::at(int index) const
{
    if(index>=0 && index<=length)
        return data[index];
    return '\0';
}

bool String::equals(const String &other) const
{
    if(length != other.length)
        return false;
    for(int i=0;i<length;i++)
        if(data[i]!=other.data[i])
            return false;
    return true;
}

void String::print() const
{
    int i=0;
    while(i<=length)std::cout<<data[i++];
    std::cout<<std::endl;
}
