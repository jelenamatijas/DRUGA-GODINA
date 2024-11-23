#pragma once

class Complex{
    double re, im;
    public:
    Complex(double re=0, double im=0);
    Complex& operator=(const Complex& c);
    Complex(Complex&& c)=delete;
    Complex& operator=(Complex&&)=delete;

    bool equalCoordinates(const Complex&)const;
    double get_re()const;
    double get_im()const;
};