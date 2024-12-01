#pragma once
#include "Putnicko_vozilo.h"

class Putnicka_vozila {
	int broj_vozila, front, rear;
	Putnicko_vozilo *kolona;
public:
	Putnicka_vozila(int broj_vozila = 0);
	~Putnicka_vozila();
	bool empty()const;
	bool full()const;
	bool del(Putnicko_vozilo &vozilo);
	void insert(const Putnicko_vozilo &vozilo);
};