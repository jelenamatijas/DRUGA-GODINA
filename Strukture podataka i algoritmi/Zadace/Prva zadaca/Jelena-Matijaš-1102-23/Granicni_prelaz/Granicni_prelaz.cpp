#include <iostream>
#include "Putnicka_vozila.h"
#include "Teretna_vozila.h"

using namespace std;

void print_zaglavlje() {
    cout << "--------------------------------" << endl;
    cout << "++++++++++++++++++++++++++++++++" << endl;
    cout << "--------------------------------" << endl;
}

int main()
{
    srand((unsigned)time(0));
    int opcija;
    do {
        cout << "Kraj.... 0\nSimulacija putnickih vozila.... 1\nSimulacija teretnih vozila.... 2\n>> ";
        cin >> opcija;
        if (opcija == 1) {
            int broj_putnickih_vozila;
            cout << "Unesi broj putnickih vozila koja trebaju preci granicu: ";
            cin >> broj_putnickih_vozila;
            if (broj_putnickih_vozila > 0) {
                Putnicka_vozila vozila(broj_putnickih_vozila);
                for (int i = 0; i < broj_putnickih_vozila; i++) {
                    cout << "Dolazak novog putnickog vozila u kolonu (podaci): " << endl;
                    Putnicko_vozilo v;
                    v.init();
                    v.printVozilo();
                    vozila.insert(v);
                }

                for (int i = 0; i < broj_putnickih_vozila; i++) {
                    cout << "Pregled pasosa (podaci): " << endl;
                    Putnicko_vozilo v;
                    vozila.del(v);
                    v.printVozilo();
                }
                cout << "++++++++++++++++++++++++++++++++" << endl;
                cout << "--------------------------------" << endl;
            }
            else {
                cout << "Broj automobila koji cekaju da predju granicu je 0." << endl;
                print_zaglavlje();
            }
        }
        else if (opcija == 2) {
            int broj_teretnih_vozila;
            cout << "Unesi broj teretnih vozila koja trebaju preci granicu: ";
            cin >> broj_teretnih_vozila;
            if (broj_teretnih_vozila > 0) {
                Teretna_vozila vozila(broj_teretnih_vozila);
                if (vozila.get_success_open()==true) {
                    vozila.parkirana_vozila();

                    cout << "Prelazak teretnih vozila preko granice(podaci): " << endl;

                    vozila.pregled();

                    cout << "++++++++++++++++++++++++++++++++" << endl;
                    cout << "--------------------------------" << endl;
                }
                else {
                    print_zaglavlje();
                }
            }
            else {
                cout << "Broj teretnih vozila koji cekaju da predju granicu je 0." << endl;
                print_zaglavlje();
            }
        }
        else if (opcija == 0) {
            cout << "KRAJ." << endl;
        }
        else {
            cout << "Pogresan unos." << endl;
            print_zaglavlje();
        }
    } while (opcija != 0);
}

