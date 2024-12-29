#pragma once
#include "Item.h"

class Book:public Item
{
private:
    std::string author;
    std::string isbn;
public:
    Book(std::string name, std::string author, int price, std::string isbn): Item(name, price), 
                                                                            author(author),
                                                                            isbn(isbn){}
    std::string getAuthor()const{
        return author;
    }

    std::string getIsbn()const{
        return isbn;
    }

    std::string toString()const override{
        return Item::toString()+ author + " " + isbn;
    }
};