#include <iostream>
#include "Set.h"

int main() {

    Complex c1(1,1);
    Complex c2(2,2);
    Complex c3(1,3);
    Complex c4(3,2);

    Vector v1(2);
    v1.addElement(c1);
    v1.addElement(c2);
    Vector v2(2);
    v2.addElement(c3);
    v2.addElement(c4);
    Vector v3(2);
    v3.addElement(c3);
    v3.addElement(c2);
    Vector v4(2);
    v4.addElement(c4);
    v4.addElement(c1);

    Set s1;
    s1.add(v1);
    s1.add(v2);
    std::cout<<"Set 1: "<<std::endl;
    std::cout<<s1;
    std::cout<<"------------------------------------"<<std::endl;
    Set s2;
    s2.add(v3);
    s2.add(v4);
    std::cout<<"Set 2: "<<std::endl;
    std::cout<<s2;
    std::cout<<"------------------------------------"<<std::endl;

    if(s2.equals(s1))
        std::cout<<"S1==S2"<<std::endl;
    else    
        std::cout<<"S1!=S2"<<std::endl;

    Set s3 = std::move(s1);
    s3.add(v4);
    std::cout<<"Set 3: "<<std::endl;
    std::cout<<s3;
    std::cout<<"------------------------------------"<<std::endl;
    std::cout<<"Set 1: "<<std::endl;
    std::cout<<s1;
    std::cout<<"------------------------------------"<<std::endl;

    Vector v5;
    v5 = v2;
    Set s4;
    s4 = s3;
    s4.add(v5);

    Vector v6 = v2;
    Set s5 = s2;
    s5.add(v6);

    std::cout<<"Set 4: "<<std::endl;
    std::cout<<s4;
    std::cout<<"------------------------------------"<<std::endl;

    std::cout<<"Set 5: "<<std::endl;
    std::cout<<s5;
    std::cout<<"------------------------------------"<<std::endl;

    Set s6 = s4+s5;
    std::cout<<"Set 6: "<<std::endl;
    std::cout<<s6;
    std::cout<<"------------------------------------"<<std::endl;

    Vector v7(2);
    v7.addElement(c1);
    v7.addElement(c4);
    s5.add(v7);
    s5.add(v7*2);
    s5.add(-1*v7);

    s6+=s5;
    std::cout<<"Set 6: "<<std::endl;
    std::cout<<s6;
    std::cout<<"------------------------------------"<<std::endl;

    return 0;
}