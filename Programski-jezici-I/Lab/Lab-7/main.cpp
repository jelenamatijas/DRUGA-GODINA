#include <iostream>
#include <cstdint>
#include "Tickets.h"
#include "Concert.h"
#include "SportEvent.h"
#include "MovieEvent.h"

double computeTotalPrice(Tickets* tickets[]){
    double totalPrice=0;
    for(int i=0;i<3;i++){
        totalPrice+= tickets[i]->computePrice();
    }
    return totalPrice;
}

int main() {
    Tickets* tickets[3];
    tickets[0] = new Concert("Concert", "Date of Concert", "Name of artist", 100);
    tickets[1] = new MovieEvent("MovieEvent", "Date of MovieEvent", true, 100);
    tickets[2] = new SportEvent("SportEvent", "Date of SportEvent", false, 200);

    std::cout<<"Total price: "<<computeTotalPrice(tickets)<<std::endl;

    for(int i=0;i<3;i++){
        delete tickets[i];
    }
    return 0;
}