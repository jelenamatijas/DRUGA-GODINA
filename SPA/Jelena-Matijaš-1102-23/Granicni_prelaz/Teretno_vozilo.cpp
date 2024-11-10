#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <ctime>
#include "Teretno_vozilo.h"

using namespace std;

int Teretno_vozilo::counter=0;

void promijesaj_indekse(int*indeksi, int broj_robe) {
	for (int i = 0; i < broj_robe; i++) {
		int ri = rand() % broj_robe;
		int temp = indeksi[i];
		indeksi[i] = indeksi[ri];
		indeksi[ri] = temp;
	}
}

void Teretno_vozilo::set_next(Teretno_vozilo* next) {
	this->next = next;
}

Teretno_vozilo::Teretno_vozilo():redni_broj(0), roba(nullptr), next(nullptr), success(false){
	init();
	//cout << "Kreiranje novo vozila" << endl;
}


void Teretno_vozilo::init() {
	ifstream file("Roba_2.txt");
	if (!file) {
		cout << "Greska pri otvaranju fajla." << endl;
		set_success(false);
	}
	else {
		set_success(true);
		int broj_robe = 0;
		string linija;
		while (getline(file, linija))broj_robe++;
		if (!broj_robe) {
			cout << "Fajl je prazan." << endl;
		}
		else {
			file.clear();
			file.seekg(0, ios::beg);
			string* nazivi_robe = new string[broj_robe];
			for (int i = 0; i < broj_robe; i++)getline(file, nazivi_robe[i]);
			file.close();

			int* indeksi = new int[broj_robe];
			for (int i = 0; i < broj_robe; i++)indeksi[i] = i;

			promijesaj_indekse(indeksi, broj_robe);
			for (int i = 0; i < 3; i++) {
				int index = indeksi[i];
				int kolicina = rand() % 100 + 1;
				Roba* nova = new Roba(nazivi_robe[index], kolicina);
				push(nova);
			}
			this->redni_broj = ++counter;
			delete[]nazivi_robe;
			nazivi_robe = nullptr;
		}
	}
}

Teretno_vozilo::~Teretno_vozilo() {
	//cout << "Brisanje robe iz vozila" << endl;
	while(roba){
		Roba* temp = roba;
		roba = roba->get_next();
		delete temp;
	}
	roba = nullptr;
}

Teretno_vozilo* Teretno_vozilo::get_next()const {
	return this->next;
}

Roba* Teretno_vozilo::get_roba()const {
	return this->roba;
}

int Teretno_vozilo::get_redni_broj()const {
	return redni_broj;
}

void  Teretno_vozilo::push(Roba* nova) {
	if (this->roba == nullptr) {
		this->roba = nova;
		this->roba->set_next(nullptr);
	}
	else {
		nova->set_next(this->roba);
		this->roba = nova;
	}
}
Roba* Teretno_vozilo::pop() {
	if (roba!=nullptr) {
		Roba* temp = roba;
		roba = roba->get_next();
		return temp;
	}
	return nullptr;
}

bool Teretno_vozilo::notempty()const {
	return this->roba;
}

void Teretno_vozilo::print_vozilo()const {
	cout << "Redni broj vozila: " << this->redni_broj << endl;
	cout << "Sadrzaj prikolice:" << endl;
	Roba* temp = this->get_roba();
	while (temp) {
		cout << "Naziv robe: " << temp->get_naziv() << " - kolicina: " << temp->get_kolicinu() << endl;
		temp = temp->get_next();
	}
	cout << "\n--------------------------------" << endl;
}

bool Teretno_vozilo::get_success()const {
	return success;
}
void Teretno_vozilo::set_success(bool s) {
	success = s;
}
