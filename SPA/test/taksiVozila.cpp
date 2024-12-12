#include <iostream>
#include <vector>
#include <queue>
#include <limits>
#include <fstream>
#include <sstream>
#include <string>
#include <unordered_map>
#include <set>
#include <algorithm>


using namespace std;

const int INF = numeric_limits<int>::max();

struct Taxi {
    int id;
    int currentLocation;
    bool isAvailable;
    int totalDistance;
    int completedRides;

    Taxi(int id, int loc) : id(id), currentLocation(loc), isAvailable(true), totalDistance(0), completedRides(0) {}
};

// Function to read a CSV file into a 2D vector
vector<vector<int>> readAdjacencyMatrix(const string& filename) {
    vector<vector<int>> matrix;
    ifstream file(filename);
    string line;

    while (getline(file, line)) {
        stringstream ss(line);
        vector<int> row;
        string value;

        while (getline(ss, value, ',')) {
            row.push_back(stoi(value));
        }

        matrix.push_back(row);
    }

    return matrix;
}

// Function to read taxi data
vector<Taxi> readTaxis(const string& filename) {
    vector<Taxi> taxis;
    ifstream file(filename);
    string line;

    while (getline(file, line)) {
        stringstream ss(line);
        string id_str, loc_str;
        getline(ss, id_str, ',');
        getline(ss, loc_str, ',');
        int id = stoi(id_str.substr(6)); // Extract numeric ID from "voziloX"
        int location = stoi(loc_str);
        taxis.emplace_back(id, location);
    }

    return taxis;
}

// Function to read user queries
vector<pair<int, int>> readUserQueries(const string& filename) {
    vector<pair<int, int>> queries;
    ifstream file(filename);
    string line;

    while (getline(file, line)) {
        stringstream ss(line);
        string start_str, destination_str;
        getline(ss, start_str, ',');
        getline(ss, destination_str, ',');
        int start = stoi(start_str);
        int destination = stoi(destination_str);
        queries.emplace_back(start, destination);
    }

    return queries;
}

// Dijkstra's algorithm to find the shortest path
vector<int> dijkstra(const vector<vector<int>>& graph, int start) {
    int n = graph.size();
    vector<int> dist(n, INF);
    vector<int> prev(n, -1);
    dist[start] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    pq.emplace(0, start);

    while (!pq.empty()) {
        int d = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        if (d > dist[u]) continue;

        for (int v = 0; v < n; ++v) {
            if (graph[u][v] > 0) {
                int newDist = dist[u] + graph[u][v];
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                    pq.emplace(newDist, v);
                }
            }
        }
    }

    return prev;
}

// Function to reconstruct the shortest path
vector<int> reconstructPath(const vector<int>& prev, int start, int end) {
    vector<int> path;
    for (int at = end; at != -1; at = prev[at]) {
        path.push_back(at);
    }
    reverse(path.begin(), path.end());
    if (path[0] == start) return path;
    return {};
}

// Binary search tree node for managing available taxis
struct BSTNode {
    Taxi* taxi;
    BSTNode* left;
    BSTNode* right;

    BSTNode(Taxi* taxi) : taxi(taxi), left(nullptr), right(nullptr) {}
};

class TaxiManager {
    BSTNode* root;

    BSTNode* insert(BSTNode* node, Taxi* taxi) {
        if (!node) return new BSTNode(taxi);

        if (taxi->currentLocation < node->taxi->currentLocation)
            node->left = insert(node->left, taxi);
        else
            node->right = insert(node->right, taxi);

        return node;
    }

    Taxi* findMin(BSTNode* node) {
        while (node && node->left) {
            node = node->left;
        }
        return node ? node->taxi : nullptr;
    }

public:
    TaxiManager() : root(nullptr) {}

    void addTaxi(Taxi* taxi) {
        root = insert(root, taxi);
    }

    Taxi* findClosest(const vector<Taxi>& taxis, const vector<int>& dist) {
        Taxi* closestTaxi = nullptr;
        int minDistance = INF;

        for (const auto& taxi : taxis) {
            if (taxi.isAvailable && dist[taxi.currentLocation] < minDistance) {
                closestTaxi = const_cast<Taxi*>(&taxi); // Ukazujemo na dostupni taksi
                minDistance = dist[taxi.currentLocation];
            }
    }

    return closestTaxi;
    }
};

int main() {
    string matrixFile = "matricaGrada.csv";
    string taxiFile = "vozila.txt";
    string queriesFile = "upitiKorisnika.csv";

    // Load data
    vector<vector<int>> graph = readAdjacencyMatrix(matrixFile);
    vector<Taxi> taxis = readTaxis(taxiFile);
    vector<pair<int, int>> queries = readUserQueries(queriesFile);

    TaxiManager manager;
    for (auto& taxi : taxis) {
        manager.addTaxi(&taxi);
    }

    int totalWaitTime = 0;
    int totalTransportTime = 0;
    int totalDistance = 0;

    // Process user queries
    for (const auto& query : queries) {
        int userStart = query.first;
        int userDestination = query.second;

        // Find the shortest path to all locations from the user's start location
        vector<int> dist = dijkstra(graph, userStart);
        vector<int> prev = dijkstra(graph, userStart);

        // Find the closest available taxi
        Taxi* closestTaxi = manager.findClosest(taxis, dist);

        if (closestTaxi) {
            closestTaxi->isAvailable = false;

            vector<int> pathToUser = reconstructPath(prev, closestTaxi->currentLocation, userStart);
            vector<int> pathToDestination = reconstructPath(prev, userStart, userDestination);

            int waitTime = dist[userStart];
            int transportTime = dist[userDestination] - dist[userStart];
            int tripDistance = transportTime;

            totalWaitTime += waitTime;
            totalTransportTime += transportTime;
            totalDistance += tripDistance;

            closestTaxi->currentLocation = userDestination;
            closestTaxi->totalDistance += tripDistance;
            closestTaxi->completedRides++;

            cout << "Taxi " << closestTaxi->id << " assigned to user." << endl;
            cout << "Path to user: ";
            for (int loc : pathToUser) cout << loc << " ";
            cout << endl;

            cout << "Path to destination: ";
            for (int loc : pathToDestination) cout << loc << " ";
            cout << endl;

            closestTaxi->isAvailable = true;
        } else {
            cout << "No available taxis for user from " << userStart << " to " << userDestination << endl;
        }
    }

    cout << "\nSimulation Results:" << endl;
    cout << "Total wait time: " << totalWaitTime << " minutes" << endl;
    cout << "Total transport time: " << totalTransportTime << " minutes" << endl;
    cout << "Total distance covered by all taxis: " << totalDistance << " units" << endl;

    for (const auto& taxi : taxis) {
        cout << "Taxi " << taxi.id << ": " << taxi.completedRides << " rides, " << taxi.totalDistance << " units covered." << endl;
    }

    return 0;
}
