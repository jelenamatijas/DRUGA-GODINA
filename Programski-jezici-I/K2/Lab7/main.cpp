#include <iostream>
#include "Event.h"
#include "Film.h"
#include "Sport.h"
#include "Concert.h"

double computePrice(Event **events){
    double sum = 0.0;
    for(int i=0; i <3; i++){
        sum= sum + events[i]->calculatePrice();
    }
    return sum;
}

int main() {
    Event *events[3];
    events[0] = new Concert("Koncert", "10.1.2025.", 50, "Petar Petrovic");
    events[1] = new Film("Film", "11.1.2025.", 20, 1);
    events[2] = new Sport("Sport", "12.1.2025.", 30, 0);

    for(int i=0; i <3; i++){
        std::cout<< events[i]->getEventName()<<" "<< events[i]->getDate()<< " "<< events[i]->getBasePrice()<<std::endl;
    }

    std::cout << "Total price for these events:\n"<< computePrice(events)<<std::endl;

    for(int i=0; i <3; i++){
        delete events[i];
    }
    return 0;
}