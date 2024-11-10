#pragma once

#include <cstdlib>
#include <ctime>
#include "Putnik.h"

using namespace std;

class Putnicko_vozilo {
	int br_putnika;
	int redni_broj;
	static int counter;
	Putnik *putnici;
public:
	Putnicko_vozilo();
	void init();
	void copy(const Putnicko_vozilo &v);
	~Putnicko_vozilo();
	int broj_mladjih_od_10()const;
	void printVozilo()const;
};