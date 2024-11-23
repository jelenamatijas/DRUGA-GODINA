#include "Complex.h"

Complex::Complex(double re, double im):re(re),im(im){}
Complex& Complex::operator=(const Complex& c){
    this->re = c.re;
    this->im = c.im;
    return *this;
}

bool Complex::equalCoordinates(const Complex& c)const{
    if(re!=c.get_re() || im!=c.get_im())return false;
    return true;
}

double Complex::get_re()const{
    return re;
}
double Complex::get_im()const{
    return im;
}