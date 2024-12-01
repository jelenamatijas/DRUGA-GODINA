#include <iostream>
using namespace std;
#include "Putnicko_vozilo.h"

int Putnicko_vozilo::counter = 0;

Putnicko_vozilo::Putnicko_vozilo():br_putnika(0), redni_broj(0), putnici(nullptr) {
	//cout << "Kreirano novo putnicko vozilo. (dif. konst.)" << endl;
}

void Putnicko_vozilo::init(){
	br_putnika = rand() % 5 + 1;

	int punoljetni = 0;
	putnici = new Putnik[br_putnika];

	for (int i = 0; i < br_putnika; i++) {
		putnici[i].init_putnik();
		if (putnici[i].get_godine() > 17)punoljetni++;
	}

	if (!punoljetni)
		putnici[0].set_godine(rand() % 20 + 18);

	counter++;
	redni_broj = counter;
	//cout << "Kreirano novo putnicko vozilo." << endl;
}

Putnicko_vozilo::~Putnicko_vozilo() {
	if (putnici != nullptr) {
		//cout << "Brisem putnike...(Putnicko vozilo dest.)" << endl;
		delete[] putnici;
		putnici = nullptr;
	}
	//cout << "Obrisano putnicko vozilo." << endl;
}

int Putnicko_vozilo::broj_mladjih_od_10()const {
	int mladji = 0;
	for (int i = 0; i < br_putnika; i++) {
		if (putnici[i].get_godine() <= 10)
			mladji++;
	}
	return mladji;
}

void Putnicko_vozilo::printVozilo()const {
	cout << "Redni broj vozila: " << redni_broj << endl;
	cout << "Broj putnika: " << br_putnika << endl;
	cout << "Godine putnika: ";
	for (int i = 0; i < br_putnika; i++)
		cout << putnici[i].get_godine()<<" ";
	cout <<"\n--------------------------------"<< endl;
}

void Putnicko_vozilo::copy(const Putnicko_vozilo& v) {
	//cout << "Kopiram objekat..." << endl;
	br_putnika = v.br_putnika;
	redni_broj = v.redni_broj;
	if (putnici != nullptr) {
		delete[] putnici;
		putnici = nullptr;
	}
	putnici = new Putnik[br_putnika];
	for (int i = 0; i < br_putnika; i++) {
		putnici[i].set_godine(v.putnici[i].get_godine());
	}
}