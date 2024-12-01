#include <iostream>
#include "Roba.h"

Roba::Roba(const string& naziv, const int& kolicina) :naziv(naziv), kolicina(kolicina), next(nullptr) {}
string Roba::get_naziv()const {
	return naziv;
}
int Roba::get_kolicinu()const {
	return kolicina;
}
void Roba::set_next(Roba* next){
	this->next = next;
}

Roba* Roba::get_next()const {
	return next;
}

void Roba::print_roba()const {
	cout << "Naziv robe: " << get_naziv() << " - kolicina: " << get_kolicinu() << endl;
}
