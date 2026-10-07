#pragma once
#include <memory>
#include <string>
#include <functional>
#include <map>

template <typename I>
class Factory
{
private:
    std::map<std::string, std::function<std::unique_ptr<I>()>> _map;
public:
    void registerFunction(std::string name, std::function<std::unique_ptr<I>()> f){
        _map[name] = f;
    }

    std::unique_ptr<I> create(std::string name)const {
        return _map.at(name)();
    }
};
