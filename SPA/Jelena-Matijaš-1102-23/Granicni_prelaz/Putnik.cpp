//#include <iostream>
#include "Putnik.h"

void Putnik::init_putnik() {
	godine = rand() % 80 + 1;
	//cout << "Novi putnik kreiran." << endl;
}

Putnik::Putnik() :godine(0) {}

int Putnik::get_godine()const {
	return godine;
}

void Putnik::set_godine(int godine) {
	this->godine = godine;
}