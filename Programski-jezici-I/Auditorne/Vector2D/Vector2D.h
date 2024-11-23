class Vector2D{
    double x, y;
public:
    Vector2D(double x=0, double y=0);
    double getX()const;
    double getY()const;
    void setX(double x);
    void setY(double y);

    bool equals(const Vector2D&)const;
    void add(const Vector2D&);
    Vector2D add_(const Vector2D&)const;
    void subtract(const Vector2D&);
    Vector2D subtract_(const Vector2D&)const;

    void print()const;

};