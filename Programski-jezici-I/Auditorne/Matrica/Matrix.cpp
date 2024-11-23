#include "Matrix.h"
#include <iostream>

Matrix::Matrix(int i, int j):rows(i), cols(j), data(new double[rows*cols]){}

Matrix::Matrix(const Matrix &other):Matrix(other.rows, other.cols)
{
    for(int i=0;i<rows*cols;i++)
        data[i] = other.data[i];
}

Matrix::Matrix(Matrix &&other):rows(other.rows), cols(other.cols), data(other.data)
{
    other.data = nullptr;
}

Matrix& Matrix::operator=(const Matrix &m)
{
    if(this!=&m){
        delete[] data;
        rows = m.rows;
        cols = m.cols;
        data = new double[rows*cols];
        for(int i=0;i<rows*cols;i++)
            data[i] = m.data[i];
    }
    return *this;
}

Matrix &Matrix::operator=(Matrix &&m)
{
    if(this!=&m){
        delete[] data;
        rows = m.rows;
        cols = m.cols;
        data = m.data;
        m.data=nullptr;
    }
    return *this;
}

Matrix::~Matrix()
{
    delete[] data;
    data=nullptr;
}

//Podrazumijeva se da su dimenzije matrica iste
Matrix Matrix::operator+(const Matrix &other) const
{
    Matrix newMatrix(rows, cols);
    for(int i=0;i<rows*cols;i++)
        newMatrix.data[i] = data[i]+ other.data[i];
    return newMatrix;
}

Matrix Matrix::operator*(const Matrix& other)const{
    Matrix newMatrix(rows, cols);
    for(int i=0;i<rows;i++){
        for(int j=0;j<other.cols;j++){
            for(int k=0;k<cols;k++){
                newMatrix.data[i*other.cols+j] = data[i * cols + k] * other.data[k * other.cols + j];
            }
        }
    }
    return newMatrix;
}

Matrix Matrix::operator*(double c)const{
    Matrix result(rows, cols);
    for (int i = 0; i < rows * cols; i++)
    {
        result.data[i] = data[i] * c;
    }
    return result;
}

Matrix operator*(double c, const Matrix& m){
    return m*c;
}

std::ostream& operator<<(std::ostream &out, const Matrix &m)
{
    for(int i=0;i<m.getRows();i++){
        for(int j=0;j<m.getCols();j++)
            out<<m[i][j]<<" ";
        out<<std::endl;
    }
    return out;
}

std::istream &operator>>(std::istream &in, Matrix &m)
{
    for(int i=0;i<m.getRows();i++)
        for(int j=0;j<m.getCols();j++)
            in>>m[i][j];
    return in;
}

bool Matrix::operator==(const Matrix& other)const{
    if (rows != other.rows || cols != other.cols)
        return false;

    for(int i=0;i<rows*cols;i++)
        if(data[i]!=other.data[i])
            return false;

    return true;
}

bool Matrix::operator!=(const Matrix &other) const
{
    return !(*this==other);
}

double *Matrix::operator[](int i)
{
    return data + i*cols;
}

const double *Matrix::operator[](int i) const
{
    return data + i*cols;
}

double &Matrix::operator()(int i, int j)
{
    return data[i*cols+j];
}

double &Matrix::operator()(int i, int j) const
{
    return data[i*cols+j];
}


Matrix Matrix::identity(int n)
{
    Matrix m(n,n);
    for(int i=0;i<n*n;i++)
         m.data[i]=0;
    for(int i=0;i<n;i++)
        m(i,i)=1;
    return m;
}

Matrix Matrix::transpose()const
{
    Matrix newMatrix(this->cols, this->rows);
    for(int i=0;i<rows;i++)
        for(int j=0;j<cols;j++)
            newMatrix(j,i) = this->data[i*cols + j];
    return newMatrix;
}

Matrix Matrix::transform(double (*f)(double)) const
{
    Matrix result(rows, cols);
    for (int i = 0; i < rows * cols; i++)
    {
        result.data[i] = f(data[i]);
    }
    return result;
}


int Matrix::getRows() const
{
    return rows;
}

int Matrix::getCols() const
{
    return cols;
}
