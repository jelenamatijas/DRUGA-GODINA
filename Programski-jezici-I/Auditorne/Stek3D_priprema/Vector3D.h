#pragma once
#include <iostream>

class Vector3D
{
private:
    double x,y,z;
public:
    Vector3D(double x=0, double y=0, double z=0):x(x),y(y),z(z){}

    Vector3D operator+(const Vector3D& other)const{
        return Vector3D(x+other.x,y+other.y, z+other.z);
    }

    Vector3D& operator+=(const Vector3D& other){
        x+=other.x;
        y+=other.y;
        z+=other.z;
        return *this;
    }

    Vector3D operator*(double scalar)const{
        return Vector3D(x*scalar,y*scalar, z*scalar);
    }

    double get_x()const{
        return x;
    }

    double get_z()const{
        return z;
    }

    double get_y()const{
        return y;
    }
    
};

Vector3D operator*(double skalar, const Vector3D& vec){
    return vec*skalar;
}

std::ostream& operator<<(std::ostream& out, const Vector3D& v){
    out<<"("<<v.get_x()<<","<<v.get_y()<<","<<v.get_z()<<")"<<std::endl;
    return out;
}
