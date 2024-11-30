#pragma once
#include <iostream>
#include <cmath>

class Complex
{
private:
    double re, im;
public:
    Complex(double re=0, double im=0):re(re),im(im){}
    double getRe()const{return re;}
    double getIm()const{return im;}

    Complex operator+(const Complex& other)const{
        return Complex(re+other.re,im+other.im);
    }

    Complex& operator+=(const Complex& other){
        im+=other.im;
        re+=other.re;
        return *this;
    }

};


std::ostream& operator<<(std::ostream& out, const Complex& c){
    out<<c.getRe();
    if(c.getIm()>0)
        out<<"+i"<<c.getIm()<<std::endl;
    if(c.getIm()<0)
        out<<"-i"<<std::abs(c.getIm())<<std::endl;
    return out;
}