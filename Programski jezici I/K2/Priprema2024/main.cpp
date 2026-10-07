#include <iostream>
#include "IColor.h"
#include "ColorArray.h"
#include "groupByValue.h"
#include "Factory.h"
#include <memory>
#include <fstream>

int main() {
    ColorArray colorArray;
    colorArray.add(std::make_unique<Red>());
    colorArray.add(std::make_unique<Blue>());
    colorArray.add(std::make_unique<Green>());
    colorArray.add(std::make_unique<Red>());

    std::cout << colorArray.toString() <<std::endl;

    auto file = std::ofstream("file.txt");
    colorArray.serialize(file);
    file.close();

    Factory<IColor> colorFactory;
    colorFactory.registerFunction("Red", [](){return std::make_unique<Red>();});
    colorFactory.registerFunction("Green", [](){return std::make_unique<Green>();});
    colorFactory.registerFunction("Blue", [](){return std::make_unique<Blue>();});

    auto is = std::ifstream("file.txt");
    auto colorArray2 = ColorArray::deserialize(colorFactory, is);

    std::cout << colorArray2.toString()<<std::endl;

    const std::map<int, std::string> m {{1,"A"}, {2,"B"}, {3,"A"}, {4,"B"}};
    auto x = groupByValue(m);
    for (auto e : x) {
    std::cout << e.first << ": ";
    for (const auto& v : e.second) std::cout << v << " ";
    std::cout << std::endl;
    // Očekivani ispis:
    // A: 1 3
    // B: 2 4
    }

    return 0;
}