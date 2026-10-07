#include <iostream>
#include "IArray.h"
#include "Array.h"
#include "BoundedStack.h"
#include "ArrayFullException.h"
#include <fstream>

class IBase{
public:
    virtual std::string name()const = 0;
};

class Child1: public IBase{
public:
    std::string name()const{ return "Child 1";};
};

class Child2: public IBase{
public:
    std::string name()const{ return "Child 2";};
};

std::ostream& operator<<(std::ostream& os, const Array<std::string> &data){
    for(int i=0; i<data.count(); i++){
        std::string str = data.at(i);
        os<<"Duzina rijeci: ";
        os<<str.length();
        os<<" ";
        os<<str;
        os<<std::endl;
    }
    return os;
}

int main() {

    Array<IBase*> array;
    Child1 c1;
    Child2 c2;
    array.append(&c1);
    array.append(&c2);

    for(int i=0; i<array.count(); i++){
        std::cout << array.at(i)->name()<<std::endl;
    }

    try{
        BoundedStack<int, 2> bs;
        bs.append(1);
        bs.append(2);
        bs.append(3);
    }catch(const ArrayFullException& a){
        std::cout << "Array is full."<<std::endl;
    }

    Array<std::string> array1;
    array1.append("Marko");
    array1.append("Zarko");

    std::ofstream file("File.txt");
    file<<array1;
    file.close();

    int x =10;

    auto array2 = array1.transform<int>([x](auto s){
        return x + s.size();
    });


    return 0;
}