//#include <iostream>
#include "Putnicka_vozila.h"

Putnicka_vozila::Putnicka_vozila(int broj_vozila) : broj_vozila(broj_vozila) {
	kolona = new Putnicko_vozilo[broj_vozila];
	front = 0;
	rear = 0;
	//cout << "Kreirano nova putnicka vozila." << endl;
}

bool Putnicka_vozila::empty()const {
	return front == rear;
}

bool Putnicka_vozila::full()const {
	return rear == broj_vozila;
}

bool Putnicka_vozila::del(Putnicko_vozilo &vozilo) {
	if (empty())return false;
	vozilo.copy(kolona[front]);
	front++;
	return true;
}
void Putnicka_vozila::insert(const Putnicko_vozilo& vozilo) {
	if (!full()) {
		int i = rear - 1;
		while (i >= 0 && kolona[i].broj_mladjih_od_10() < vozilo.broj_mladjih_od_10()) {
			kolona[i + 1].copy(kolona[i]);
			i--;
		}
		kolona[i + 1].copy(vozilo);
		rear++;
	}
}

Putnicka_vozila::~Putnicka_vozila() {
	if (kolona != nullptr) {
		delete[] kolona;
		kolona = nullptr;
	}
	//cout << "Obrisana putnicka vozila." << endl;
}
