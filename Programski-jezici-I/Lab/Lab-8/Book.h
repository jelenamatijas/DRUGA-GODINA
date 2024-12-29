#pragma once
#include <string>
#include "Item.h"

class Book : public Item
{
private:
    const std::string author;
    const std::string isbn;
public:
    Book(std::string name, int price, std::string author, std::string isbn) : Item(name, price), author(author), isbn(isbn) {}
    std::string getAuthor() const
    {
        return author;
    }
    std::string getIsbn() const
    {
        return isbn;
    }
    std::string toString() const override
    {
        return Item::toString() + " " + author + " " + isbn;
    }
};