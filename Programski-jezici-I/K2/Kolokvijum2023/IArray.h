#pragma once

template <typename T>
class IArray
{
    static_assert(std::is_copy_constructible_v<T>, "T must be copy constructible.");
public:
    virtual void append(const T&) = 0;
    virtual void erase(int) = 0;
    virtual T& at(int) = 0;
    virtual const T& at(int) const = 0;
    virtual int count() const noexcept = 0;
};