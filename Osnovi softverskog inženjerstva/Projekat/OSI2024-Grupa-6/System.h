#pragma once
#include "UserTable.h"
#include "TicketsTable.h"
#include <fstream>

class System
{
private:
    bool commercialVersion;
    std::string companyInfo;// bice set pomocu sablona 
    UserTable usertable;
    TicketsTable ticketstable;

public:
    System( bool commercialVersion=false, std::string companyInfo="", sqlite3* db=nullptr):
    commercialVersion(commercialVersion), companyInfo(companyInfo),usertable(db), ticketstable(db)
    {
        usertable.createTable();
        ticketstable.createTable();
    }

    bool isCommercialVersion() const
    {
        return commercialVersion;
    }

    bool upgradeToCommercial(const std::string& key);
  
  
   //~System(); TODO : da li je potreban destruktor ovdje???
};


