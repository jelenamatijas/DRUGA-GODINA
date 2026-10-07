#pragma once
#include <vector>
#include <memory>
#include "IColor.h"
#include <iostream>
#include "Factory.h"

class ColorArray
{
private:
    std::vector<std::unique_ptr<IColor>> colors;
public:
    void add(std::unique_ptr<IColor>&& color){
        colors.push_back(std::move(color));
    }

    std::string toString()const{
        std::string str;
        for(const auto& color: colors){
            str += color->getColorName() + " ";
        }

        return str;
    }

    void serialize(std::ostream& os){
        os<<toString();
    }

    static ColorArray deserialize(const Factory<IColor> &colorFactory, std::istream& is){
        ColorArray colorArray;
        std::string str;
        while(is >> str){
            colorArray.add(colorFactory.create(str));
        }
        return colorArray;
    }
};
