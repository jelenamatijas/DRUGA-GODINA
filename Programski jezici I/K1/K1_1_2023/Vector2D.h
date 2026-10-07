class Vector2D{
    double x, y;
public:
    Vector2D(double x=0, double y=0);

    Vector2D operator+=(const Vector2D&);
    Vector2D operator+(const Vector2D&)const;

    void setX(double x);
    void setY(double y);
    double getX()const;
    double getY()const;
    
};