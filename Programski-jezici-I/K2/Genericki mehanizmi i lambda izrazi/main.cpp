#include <iostream>
#include "Map.h"

int main() {
    Map<std::string, std::string> map1;
    map1.add("A", "Auto");
    map1.add("B", "Banana");
    map1.add("C", "Cipela");
    map1.add("C", "Cvrcak");

    for(const auto &key: map1.keys()){
        std::cout<<key<<": "<<map1[key]<<std::endl;
    }

    Map<std::string, size_t> map2 = map1.transform<size_t>([](const std::string &value){
        return value.size();
    });

    for(const auto &key: map2.keys()){
        std::cout<<key<<": "<<map2[key]<<std::endl;
    }

    return 0;
}