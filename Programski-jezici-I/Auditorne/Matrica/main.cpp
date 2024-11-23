#include <iostream>
#include "Matrix.h"

using namespace std;

int main()
{
    Matrix m1(3, 3);
    
    // Pristup sa operatorom []
    m1[0][0] = 1;
    m1[0][1] = 2;
    m1[0][2] = 3;

    // Pristup sa operatorom ()
    m1(1, 0) = 4;
    m1(1, 1) = 5;
    m1(1, 2) = 6;

    m1(2, 0) = 7;
    m1(2, 1) = 8;
    m1(2, 2) = 9;

    Matrix m2 = m1.transform([](double x) { return x * x; });

    cout << "M1:" << endl;
    cout << m1;
    cout << endl;

    cout << "M2:" << endl;
    cout << m2;
    cout << endl;

    cout << "M1 + M2:" << endl;
    cout << m1 + m2;
    cout << endl;

    cout << "M1 * M2:" << endl;
    cout << m1 * m2;
    cout << endl;

    cout << "M1 * 2:" << endl;
    cout << m1 * 2;
    cout << endl;

    cout << "2 * M1:" << endl;
    cout << 2 * m1;
    cout << endl;

    // m1 == m2
    cout << "M1 == M2: ";
    cout << (m1 == m2 ? "true" : "false");
    cout << endl;

    // m1 != m2
    cout << "M1 != M2: ";
    cout << (m1 != m2 ? "true" : "false");
    cout << endl;
    cout << endl;

    // jedinična matrica
    cout << "Identity(3):" << endl;
    cout << Matrix::identity(3);
    cout << endl;

    // transponovana matrica
    cout << "Transpose(M1):" << endl;
    cout << m1.transpose();
    cout << endl;
    
    // operator >>
    cout << "Unesite 3x3 matricu M3:" << endl;
    Matrix m3(3, 3);
    cin >> m3;
    cout << endl;

    cout << "M3:" << endl;
    cout << m3;
    cout << endl;
    return 0;
}