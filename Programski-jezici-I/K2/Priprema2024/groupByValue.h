#pragma once
#include <map>
#include <vector>

template <typename K, typename V>
auto groupByValue(const std::map<K, V>& M){
    std::map<V, std::vector<K>> _map;
    for(const auto& pair: M){
        _map[pair.second].push_back(pair.first);
    }
    return _map;
}