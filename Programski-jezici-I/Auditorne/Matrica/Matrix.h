#include <istream>
#include <ostream>

class Matrix{
    int rows, cols;
    double *data;
public:
    Matrix(int i=0, int j=0);
    Matrix(const Matrix&);
    Matrix(Matrix&&);
    Matrix& operator=(const Matrix&);
    Matrix& operator=(Matrix&&);
    ~Matrix();

    Matrix operator+(const Matrix&)const;
    Matrix operator*(const Matrix&)const;
    Matrix operator*(double c)const;
    bool operator==(const Matrix&)const;
    bool operator!=(const Matrix&)const;
    double* operator[](int i);
    const double* operator[](int i)const;
    double& operator()(int i, int j);
    double& operator()(int i, int j)const;

    static Matrix identity(int);
    Matrix transpose()const;
    Matrix transform(double (*f)(double)) const;

    int getRows()const;
    int getCols()const;

};


Matrix operator*(double c, const Matrix&);
std::ostream &operator<<(std::ostream &out, const Matrix &m);
std::istream &operator>>(std::istream &in, Matrix &m);