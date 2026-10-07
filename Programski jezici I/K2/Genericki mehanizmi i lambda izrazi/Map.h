#include <iostream>
#include <vector>
#include <functional>

template <typename K, typename V>
class Map
{
    static_assert(std::is_copy_constructible_v<K>, "Key type must be copy constructible.");
    static_assert(std::is_copy_constructible_v<V>, "Value type must be copy constructible.");
private:
    struct Pair
    {
        K key;
        V value;
    };
    
    std::vector<Pair> pairs;

public:
    Map(): pairs() {}

    const V& operator[](const K& key)const{
        for(const auto& pair: pairs){
            if(pair.key == key){
                return pair.value;
            }
        }
        static V error;
        return error;
    }

    V& operator[](const K& key){
        for(auto& pair: pairs){
            if(pair.key == key){
                return pair.value;
            }
        }
        pairs.push_back({key, V()});
        return pairs.back().value;
    }

    bool add(const K& key, const V& value){
        for(const auto& pair: pairs){
            if(pair.key == key){
                return false;
            }
        }
        pairs.push_back({key, value});
        return true;
    }

    bool contains(const K& key) const{
        for(const auto& pair: pairs){
            if(pair.key == key){
                return false;
            }
        }
        return true;
    }

    std::vector<K> keys() const{
        std::vector<K> k;
        for(const auto& pair: pairs){
            k.push_back(pair.key);
        }
        return k;
    }

    template<typename E>
    Map<K,E> transform(std::function<E(V)> f)const{
        Map<K,E> map;
        for(const auto& pair: pairs){
            map.add(pair.key, f(pair.value));
        }
        return map;
    }
};