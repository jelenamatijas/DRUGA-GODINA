#pragma once
#include <string>

using namespace std;

class Roba {
	string naziv;
	int kolicina;
	Roba* next;
public:
	Roba(const string &naziv="", const int& kolicina=0);
	void set_next(Roba* next);
	string get_naziv()const;
	int get_kolicinu()const;
	Roba* get_next()const;
	void print_roba()const;
};