#pragma once

#include "Teretno_vozilo.h"

class Teretna_vozila {
	Teretno_vozilo* front, * rear;
	bool success_open;
public:
	Teretna_vozila(int broj_vozila=0);
	~Teretna_vozila();
	void vrati_u_prikolicu(Teretno_vozilo* v, Roba* top);
	void pregled();
	void parkirana_vozila()const;
	bool get_success_open()const;
};