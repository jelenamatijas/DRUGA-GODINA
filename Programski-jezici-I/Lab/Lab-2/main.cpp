#include <iostream>
#include "Set.h"

using namespace std;

int main() {
    Complex c1(1,1), c2(2,2), c3(3,3), c4(1,2);
    Vector v1(2), v2(2), v3(2);
    v1.push(c1);
    v1.push(c2);
    v2.push(c3);
    v3.push(c1);
    v3.push(c4);

    Set s1(2);
    s1.add(v1);
    s1.add(v2);

    Set s2(2);
    s2.add(v3);
    s2.add(v2);
    
    cout << "Are vectors equal? " << (s1.equal(s2) ? "Yes" : "No") << endl;

    return 0;
}