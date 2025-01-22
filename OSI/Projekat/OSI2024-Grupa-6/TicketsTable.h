#pragma once
#include "Ticket.h"
#include "sqlite3.h"
#include "User.h"
#include <string>
#include <iostream>
#include <vector>

class TicketsTable
{
    
private:
    sqlite3* db;
public:
    TicketsTable(sqlite3* db):db(db){};
    bool createTable();
    bool addTicket(Ticket& ticket);
    bool assignTicketToOperator(Ticket& ticket);// TODO PROVJERI
    bool updateTicket_client(Ticket& ticket, const std::string& newDescription);
    bool updateTicket_operater(Ticket& ticket, const std::string& newComment, const std::string& newStatus);
    bool returnTicket(Ticket& ticket,const std::string& newOperatorComment);
    std::vector<int> getAllTicketIds();//TODO check
    const Ticket getTicketById(int id) const;// By user Id
    const Ticket getTicketByTicketId(int id) const;// By ticket id
    bool resolveTicket(Ticket& ticket, const std::string& newOperatorComment);
    std::vector<int> viewAllClientTickets_client(const User& user);// TODO PROVJERI
    std::vector<int> viewTicketsByStatus(const std::string& status);// TODO PROVJERI
    std::vector<int> viewAllActiveTickets_operator(const int operatorId);// TODO PROVJERI
    std::vector<int> viewAllReturnedTickets_client(const User& user);// TODO PROVJERI
    int getTotalNumberOfTicketsResolved();
    User getBestOperator();
};

