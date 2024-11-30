#include <iostream>
#include "Stack.h"

int main() {
    Vector3D v1(1.0,2.0,3.0);
    Vector3D v2(2.0,3.0,4.0);
    Vector3D v3 = v1+v2;
    std::cout<<v1<<v2<<v3;
    Vector3D v4 = v3*(-1);
    std::cout<<v4;
    std::cout<<"---------------------------------------------"<<std::endl;
    v4 = 2*v3;
    std::cout<<v4;
    std::cout<<"---------------------------------------------"<<std::endl;

    Stack s1;
    s1.push(v1);
    s1.push(v2);
    s1.push(v3);
    s1.push(v4);

    Stack s2 = s1;
    Stack s3;
    s3 = std::move(s2);
    v4+=v1;
    s3.push(v4);
    Stack s4;
    s4 = s1;

    Vector3D vec;
    while(s3.pop(vec)){
        std::cout<<vec;
    }

    std::cout<<"---------------------------------------------"<<std::endl;
    s3 = std::move(s1);
    while(s3.pop(vec)){
        std::cout<<vec;
    }

    std::cout<<"---------------------------------------------"<<std::endl;
    s4.forEach([] (const Vector3D& vec){
        std::cout<<vec;
    });
    

    return 0;
}
