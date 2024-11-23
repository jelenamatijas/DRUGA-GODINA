class String{
    const int length;
    const char *data;
public:
    String(const char *s);
    String(const String&);
    String(String&&);
    ~String();

    int strlength()const;
    char at(int index)const;
    bool equals(const String&)const;
    void print()const;
};