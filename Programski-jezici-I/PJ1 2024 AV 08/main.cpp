#include <iostream>
#include "Item.h"
#include "Pen.h"
#include "Book.h"
#include "Sale.h"
#include "FinancialReport.h"

int main()
{	
	Sale* sale1 = new Sale;
	sale1->addItem(new Pen("Pen1", 50));
	sale1->addItem(new Pen("Pen2", 75));
	sale1->addItem(new Book("1984", 1000, "George Orwell", "9780451524935"));
	sale1->addItem(new Book("Dune", 1000, "Frank Herbert", "9780441172719"));
	// std::cout << *sale1 << std::endl;

	Sale* sale2 = new Sale;
	sale2->addItem(new Pen("Pen3", 25));
	sale2->addItem(new Pen("Pen4", 25));
	sale2->addItem(new Book("The Man Who Was Thursday", 1000, "G. K. Chesterton", "9780486251219"));
	sale2->addItem(new Book("Charlotte's Web", 1000, "Elwyn Brooks White", "9780064410939"));
	// std::cout << *sale2 << std::endl;

	FinancialReport report;
	report.addSale(sale1);
	report.addSale(sale2);
	std::cout << report << std::endl;
	return 0;
}