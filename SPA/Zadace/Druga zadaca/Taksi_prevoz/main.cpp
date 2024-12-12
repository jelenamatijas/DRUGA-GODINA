#include <iostream>
#include <fstream>
#include "City.h"
#include "Taxi.h"

using namespace std;

void getAddresses(const string& input, int* from, int* to){
    ifstream file(input);
    if(!file.is_open()){
        cout<<"ERROR: there is no such file."<<endl;
        return;
    }

    string line;
    int index=0;

    while(getline(file, line)){
        int i=0;
        while(line[i]!=','){
            from[index]*=10;
            from[index]+=line[i]-'0';
            i++;
        }
        i++;
        for(;i<line.length();i++){
            to[index]*=10;
            to[index]+=line[i]-'0';
        }
        index++;
    }
    file.close();
}

int getNumOfLines(const string& input){
    ifstream file(input);
    if(!file.is_open()){
        cout<<"ERROR: there is no such file."<<endl;
        return 0;
    }

    string line;
    int i=0;

    while(getline(file, line)){
        i++;
    }
    file.close();
    return i;
}

int main() {

    City city(getNumOfLines("matricaGrada.csv"), "matricaGrada.csv");
    Taxi taxis("vozila.txt");

    int distances[getNumOfLines("matricaGrada.csv")];
    int previous[getNumOfLines("matricaGrada.csv")];

    int numUsers = getNumOfLines("upitiKorisnika.csv");
    int from[numUsers]{0}, to[numUsers]{0};

    getAddresses("upitiKorisnika.csv", from, to);

    int totalWaitingTime = 0;
    int totalTravelTime = 0;
    int totalRides = 0;
    int totalDistance = 0;

    for (int i=0;i<numUsers;i++) {
        int userAddress = from[i];
        int destination = to[i];

        cout << "Processing user request: " << userAddress << " -> " << destination << endl;

        int minDistance = INT_MAX;
        T* parent = nullptr;
        T* closestTaxi = taxis.closestAvailableTaxi(userAddress-1, minDistance);

        if (!closestTaxi) {
            cout << "All taxis are occupied. Wait a second while we free the nearest one." << endl;
            closestTaxi = taxis.makeFree(userAddress-1, minDistance);
            totalWaitingTime += minDistance;
            if(closestTaxi){

                cout << "Closest taxi: " << closestTaxi->id << ", Address: " << closestTaxi->currAddr << endl;

                city.shortestPath(closestTaxi->currAddr, distances, previous);
                cout << "Taxi path to user: ";
                city.createPath(previous, userAddress-1);
                cout << endl;

                int usersWaitingMinutes = distances[userAddress];
                totalDistance += usersWaitingMinutes;
                cout << "User's waiting time: " << usersWaitingMinutes << " minutes" << endl;

                city.shortestPath(userAddress-1, distances, previous);
                cout << "User path to destination: ";
                city.createPath(previous, destination);
                cout << endl;

                int usersDrivingMinutes = distances[destination];
                totalDistance += usersDrivingMinutes;

                totalTravelTime += usersWaitingMinutes + usersDrivingMinutes;
                totalRides++;
                cout << "User's driving time: " << usersDrivingMinutes << " minutes" << endl;

                taxis.updateBST(closestTaxi, destination);
                closestTaxi->numRides++;
                closestTaxi->available = true;
            }
        }else if(closestTaxi){

            cout << "Closest taxi: " << closestTaxi->id << ", Address: " << closestTaxi->currAddr << endl;

            city.shortestPath(closestTaxi->currAddr, distances, previous);
            cout << "Taxi path to user: ";
            city.createPath(previous, userAddress);
            cout << endl;

            int usersWaitingMinutes = distances[userAddress];
            totalDistance += usersWaitingMinutes;
            cout << "User's waiting time: " << usersWaitingMinutes << " minutes" << endl;

            city.shortestPath(userAddress, distances, previous);
            cout << "User path to destination: ";
            city.createPath(previous, destination);
            cout << endl;

            int usersDrivingMinutes = distances[destination];
            totalDistance += usersDrivingMinutes;

            totalTravelTime += usersWaitingMinutes + usersDrivingMinutes;
            totalRides++;
            cout << "User's driving time: " << usersDrivingMinutes << " minutes" << endl;

            taxis.updateBST(closestTaxi, destination);
            closestTaxi->numRides++;
            closestTaxi->available = true;
        }else{
            cout<<"Sorry, there is no available taxis we could assign for the ride."<<endl;
        }
    }

    cout << "\nSIMULATION ENDED:" << endl;
    cout << "Total waiting time: " << totalWaitingTime << " minutes" << endl;
    cout << "Total travel time: " << totalTravelTime << " minutes" << endl;
    cout << "Total distance traveled: " << totalDistance << " units" << endl;
    taxis.print(); 

    return 0;
}