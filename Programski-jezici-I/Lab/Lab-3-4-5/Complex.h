#pragma once
#include <ostream>
#include <cmath>
#include <climits>

class Complex
{
private:
    double re, im;
public:
    Complex(double re=0, double im=0):re(re), im(im){}
    double getRe()const{return re;}
    double getIm()const{return im;}

    bool operator==(const Complex& other)const{
        return (re==other.re && im==other.im);
    }
    bool operator!=(const Complex& other)const{
        return !(*this==other);
    }

    Complex operator+(const Complex& other)const{
        return Complex(re+other.re, im+other.im);
    }
    Complex& operator+=(const Complex& other){
        re+=other.re; 
        im+=other.im;
        return *this;
    }

    Complex operator*(double scalar)const{
        return Complex(re*scalar, im*scalar);
    }
};

Complex operator+(double scalar, const Complex& c){
    return c*scalar;
}

std::ostream& operator<<(std::ostream& out, const Complex& c){
    out<<c.getRe();
    if(c.getIm()<0)
        out<<"-i"<<std::abs(c.getIm())<<std::endl;
    if(c.getIm()>0)
        out<<"+i"<<c.getIm()<<std::endl;
    return out;
}

