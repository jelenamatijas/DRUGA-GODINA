#include <iostream>
#include "Array.h"

int main() {
    Complex c1(1,2);
    Complex c2(2,3);
    Complex c3 = c1+c2;
    Complex c4;
    c4+=c1;

    std::cout<<c1;
    std::cout<<c2;
    std::cout<<c3;
    std::cout<<c4;

    std::cout<<"--------------------------------------------------"<<std::endl;

    Array a1;
    a1.append(c1);
    a1.append(c2);
    a1.append(c3);
    a1.append(c4);

    std::cout<<a1;
    std::cout<<"--------------------------------------------------"<<std::endl;

    a1.at(1) = c1+c2;
    std::cout<<a1;
    std::cout<<"--------------------------------------------------"<<std::endl;

    Array a2 = std::move(a1);
    a2.append(c1+c1);

    std::cout<<a2;
    std::cout<<"--------------------------------------------------"<<std::endl;

    Array a3;
    a3 = std::move(a2);

    std::cout<<a3;
    std::cout<<"--------------------------------------------------"<<std::endl;

    Array a4 = a3.transform([] (Complex c){
        return Complex(c.getRe()*(-1), c.getIm()*(-1));
        }
    );

    std::cout<<a4;
    std::cout<<"--------------------------------------------------"<<std::endl;

    const Array a5 = std::move(a4);
    //a5.at(0) = c2;
    std::cout<<a5;
    std::cout<<"--------------------------------------------------"<<std::endl;

    return 0;
}