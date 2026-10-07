#pragma once

#include <cstdlib>
#include <ctime>

using namespace std;

class Putnik {
	int godine;
public:
	Putnik();
	void init_putnik();
	int get_godine()const;
	void set_godine(int godine);
};