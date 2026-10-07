#pragma once
#include "Roba.h"

class Teretno_vozilo {
	Roba* roba;
	int redni_broj;
	static int counter;
	Teretno_vozilo* next;
	bool success;
public:
	Teretno_vozilo();
	~Teretno_vozilo();
	void init();
	void set_next(Teretno_vozilo* next);
	Teretno_vozilo* get_next()const;
	int get_redni_broj()const;
	Roba* get_roba()const;
	void push(Roba* roba);
	Roba* pop();
	bool notempty()const;
	void print_vozilo()const;
	bool get_success()const;
	void set_success(bool s);
};