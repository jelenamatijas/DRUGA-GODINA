#pragma once
#include <concepts>

template <typename T>
concept Ordered = requires (const T & o1, const T & o2)
{
	{ o1 < o2 } -> std::convertible_to<bool>;
	{ o1 == o2 } -> std::convertible_to<bool>;
};