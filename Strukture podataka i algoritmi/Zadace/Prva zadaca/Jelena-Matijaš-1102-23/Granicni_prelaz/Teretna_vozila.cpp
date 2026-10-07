#include <iostream>
#include "Teretna_vozila.h"

Teretna_vozila::Teretna_vozila(int broj_vozila) :front(nullptr), rear(nullptr), success_open(false){
	//cout << "Kreiranje kolone vozila" << endl;
	for (int i = 0; i < broj_vozila; i++) {
		Teretno_vozilo* novo = new Teretno_vozilo();
		success_open = novo->get_success();
		if (success_open) {
			if (front == nullptr && rear == nullptr) {
				front = rear = novo;
			}
			else {
				rear->set_next(novo);
				rear = novo;
			}
		}
		else {
			novo = nullptr;
			break;
		}
		//cout << "Kreirano ovo vozilo" << endl;
	}
}

void Teretna_vozila::parkirana_vozila()const {
	cout << "Teretna vozila koja su parkirana u koloni:" << endl;
	Teretno_vozilo* temp = front;
	while (temp) {
		temp->print_vozilo();
		temp = temp->get_next();
	}
}

Teretna_vozila::~Teretna_vozila() {
	//cout << "Brisanje kolone vozila" << endl;
	front = rear = nullptr;
}

void Teretna_vozila::vrati_u_prikolicu(Teretno_vozilo* v, Roba* top) {
	cout << "Vracanje robe u prikolicu:" << endl;
	while (top!=nullptr) {
		Roba *pom = top->get_next();
		v->push(top);
		top->print_roba();
		top = pom;
	}
}
void Teretna_vozila::pregled() {
	while (front != nullptr) {
		Teretno_vozilo* v = front;
		cout << "Pregled teretnog vozila: " << v->get_redni_broj() << endl;
		Roba* top = nullptr;
		while (v->notempty()) {
			Roba * pom = v->pop();
			if (pom == nullptr)break;
			if (top == nullptr) { 
				top = pom;
				top->set_next(nullptr);
			}
			else {
				pom->set_next(top);
				top = pom;
			}
			top->print_roba();
		}
		vrati_u_prikolicu(v, top);
		cout<<"Pregledano vozilo: " << v->get_redni_broj() << endl;
		cout << "--------------------------------" << endl;
		front = front->get_next();
		delete v;
	}
}

bool Teretna_vozila::get_success_open()const {
	return success_open;
}
