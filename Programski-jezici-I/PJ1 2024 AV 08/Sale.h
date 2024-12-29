#pragma once
#include <string>
#pragma once
#include <functional>
#include "Item.h"
#include "Array.h"

// Sa final naznačavamo da se iz ove klase ne mogu izvoditi druge klase.
class Sale final : public Object
{
private:
	Array items;

public:
    // NOTE: Sale takes ownership of the item, so it will be deleted when Sale is deleted.
	void addItem(Item* item)
	{
		items.append(item);
	}
	int totalPrice() const
	{
		int sum = 0;
		for (int i = 0; i < items.size(); i++)
		{
			Item *item = (Item *)items[i];
			sum += item->getPrice();
		}
		return sum;
	}
	std::string toString() const override
	{
		std::string str = "Sale: \n";
		for (int i = 0; i < items.size(); i++)
		{
			Item *item = (Item *)items[i];
			str += "    " + std::to_string(i+1) + ". " + item->toString() + "\n";
		}
		str += "    Total price: " + std::to_string(totalPrice());
		return str;
	}
};