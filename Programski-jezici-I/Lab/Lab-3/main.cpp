#include <iostream>
#include "String.h"

using namespace std;

int main() {
    
    char s1[] = {'M', 'a', 'r', 'k', 'o', '\0'};
    char s2[] = {'J', 'e', 'l','e', 'n', 'a', '\0'};
    
    String str1(s1);
    String str2(s2);
    String str3(str1);

    str1.print();
    str2.print();
    str3.print();

    cout<<str1.at(2)<<endl;
    cout<<str2.equals(str1)<<endl;
    cout<<str1.equals(str3)<<endl;
    cout<<str2.strlength()<<endl;
    
    return 0;
}