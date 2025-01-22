#include "TicketsTable.h"

bool TicketsTable::createTable()
{
    std::string ticketsTable= "CREATE TABLE IF NOT EXISTS tickets ("
                  "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                  "user_id INTEGER NOT NULL, "
                  "name TEXT NOT NULL, "
                  "description TEXT NOT NULL, "
                  "status TEXT NOT NULL DEFAULT 'active', "
                  "assigned_to INTEGER, "
                  "operator_comment TEXT NOT NULL DEFAULT 'none',  "
                  " FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, "
                  " FOREIGN KEY (assigned_to) REFERENCES users (id) ON DELETE CASCADE);";


        char* errorMess=nullptr;
        if(sqlite3_exec(db, ticketsTable.c_str(),nullptr,nullptr,&errorMess)!= SQLITE_OK)
        {
            std::cerr<<"Error creating tickets table:"<<errorMess<<std::endl;
            sqlite3_free(errorMess);
            return false;
        }
        return true;

}

bool TicketsTable::addTicket( Ticket& ticket)
{
    std::string sql = 
        "INSERT INTO tickets (user_id, name, description, status, assigned_to, operator_comment) VALUES (?, ?, ?, ?, ?, ?);";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        return false;
    }

    sqlite3_bind_int(stmt, 1, ticket.getUserId());
    sqlite3_bind_text(stmt, 2, ticket.getIssueName().c_str(), -1, SQLITE_STATIC);
    sqlite3_bind_text(stmt, 3, ticket.getIssueDesc().c_str(), -1, SQLITE_STATIC);
    sqlite3_bind_text(stmt, 4, ticket.getTicketStatus().c_str(), -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 5, ticket.getAssignedTo());
    sqlite3_bind_text(stmt, 6, ticket.getOperatorComment().c_str(), -1, SQLITE_STATIC);

    if (sqlite3_step(stmt) != SQLITE_DONE) {
        std::cerr << "Error adding ticket: " << sqlite3_errmsg(db) << std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
  int newId = static_cast<int>(sqlite3_last_insert_rowid(db));
    ticket.setId(newId);

    // Debug output
    std::cout << "Ticket successfully added with ID: " << newId << std::endl;

    sqlite3_finalize(stmt);
    return true;
}

bool TicketsTable::assignTicketToOperator(Ticket &ticket)
{
    std::string findOperatorSql = R"(
        SELECT users.id
        FROM users
        LEFT JOIN tickets ON users.id = tickets.assigned_to
        WHERE users.acc_type = 'Operator'
        GROUP BY users.id
        ORDER BY COUNT(tickets.id) ASC
        LIMIT 1;
    )";

    sqlite3_stmt* stmtFind;
    if (sqlite3_prepare_v2(db, findOperatorSql.c_str(), -1, &stmtFind, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing find operator statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    int operatorId = -1;
    if (sqlite3_step(stmtFind) == SQLITE_ROW) {
        operatorId = sqlite3_column_int(stmtFind, 0);
    }
    sqlite3_finalize(stmtFind);

    if (operatorId == -1) {
        std::cerr << "No operators available to assign the ticket." << std::endl;
        return false; 
    }

  
    std::string assignTicketSql = "UPDATE tickets SET assigned_to = ? WHERE id = ?;";
    sqlite3_stmt* stmtAssign;

    if (sqlite3_prepare_v2(db, assignTicketSql.c_str(), -1, &stmtAssign, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing assign ticket statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    sqlite3_bind_int(stmtAssign, 1, operatorId);
    sqlite3_bind_int(stmtAssign, 2, ticket.getId());

    if (sqlite3_step(stmtAssign) != SQLITE_DONE) {
        std::cerr << "Error updating ticket: " << sqlite3_errmsg(db) << std::endl;
        sqlite3_finalize(stmtAssign);
        return false;
    }

    sqlite3_finalize(stmtAssign);
      ticket.setAssignedTo(operatorId);
    return true;
}

    


bool TicketsTable::updateTicket_client(Ticket &ticket, const std::string &newDescription)
{
    std::string newStatus="updated";
    std::string sql= "UPDATE tickets SET description = ?, status = ? WHERE id = ?;";
    sqlite3_stmt* stmt;
  if(sqlite3_prepare_v2(db,sql.c_str(),-1,&stmt,nullptr)!= SQLITE_OK)
    {
        std::cerr<<"Error preparing statement: "<< sqlite3_errmsg(db)<<std::endl;
        return false;
    }

    sqlite3_bind_text(stmt,1,newDescription.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,2,newStatus.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_int(stmt,3,ticket.getId());

    if(sqlite3_step(stmt)!= SQLITE_DONE)
    {
        std::cerr<<"Error updating ticket: "<<sqlite3_errmsg(db)<<std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
    ticket.setIssueDescription(newDescription);
    ticket.setTicketStatus(newStatus);
    std::cout<<"Ticket updated."<<std::endl;
    sqlite3_finalize(stmt);
    return true;
}

bool TicketsTable::updateTicket_operater(Ticket &ticket, const std::string &newComment, const std::string &newStatus)
{
    std::string sql= "UPDATE tickets SET  operator_comment = ?, status = ? WHERE id = ?;";
    sqlite3_stmt* stmt;
  if(sqlite3_prepare_v2(db,sql.c_str(),-1,&stmt,nullptr)!= SQLITE_OK)
    {
        std::cerr<<"Error preparing statement: "<< sqlite3_errmsg(db)<<std::endl;
        return false;
    }

    sqlite3_bind_text(stmt,1,newComment.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,2,newStatus.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_int(stmt,3,ticket.getId());

    if(sqlite3_step(stmt)!= SQLITE_DONE)
    {
        std::cerr<<"Error updating ticket: "<<sqlite3_errmsg(db)<<std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
    ticket.setOperatorComment(newComment);
    ticket.setTicketStatus(newStatus);
    std::cout<<"Ticket updated."<<std::endl;
    sqlite3_finalize(stmt);
    return true;
}

bool TicketsTable::returnTicket(Ticket& ticket,const std::string &newOperatorComment)
{
    std::string newStatus="returned";
    std::string sql= "UPDATE tickets SET  operator_comment = ?, status = ? WHERE id = ?;";
    sqlite3_stmt* stmt;
  if(sqlite3_prepare_v2(db,sql.c_str(),-1,&stmt,nullptr)!= SQLITE_OK)
    {
        std::cerr<<"Error preparing statement: "<< sqlite3_errmsg(db)<<std::endl;
        return false;
    }

    sqlite3_bind_text(stmt,1,newOperatorComment.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,2,newStatus.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_int(stmt,3,ticket.getId());

    if(sqlite3_step(stmt)!= SQLITE_DONE)
    {
        std::cerr<<"Error returning ticket: "<<sqlite3_errmsg(db)<<std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
    std::cout<<"Ticket returned to client."<<std::endl;
    ticket.setTicketStatus(newStatus);
    ticket.setOperatorComment(newOperatorComment);

    sqlite3_finalize(stmt);
    return true;
    
}

std::vector<int> TicketsTable::getAllTicketIds()
{
    std::vector<int> ids;
    std::string sql= "SELECT id FROM tickets;";
        sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) == SQLITE_OK) {
            while (sqlite3_step(stmt) == SQLITE_ROW) {
                
                int id = sqlite3_column_int(stmt, 0);
                ids.push_back(id);
            }
            sqlite3_finalize(stmt);
        } else {
            std::cerr << "Failed to execute query: " << sqlite3_errmsg(db) << std::endl;
        }

        return ids;
    
}

const Ticket TicketsTable::getTicketById(int userId) const
{
  
    std::string sql = "SELECT id, user_id, name, description, status, assigned_to, operator_comment "
                          "FROM tickets WHERE user_id = ?;";
                
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Failed to prepare statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    sqlite3_bind_int(stmt, 1, userId);

    if (sqlite3_step(stmt) == SQLITE_ROW){
       int ticketId = sqlite3_column_int(stmt, 0);
        int userIdFromDb = sqlite3_column_int(stmt, 1);
        std::string name = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 2));
        std::string description = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 3));
        std::string status = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 4));
        int assignedTo = sqlite3_column_int(stmt, 5);
        std::string operatorComment = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 6));

        sqlite3_finalize(stmt);
        return Ticket(ticketId, userIdFromDb, description, status, name, operatorComment, assignedTo);
       
    
    }else
    {
    sqlite3_finalize(stmt);
        throw std::runtime_error("Ticket not found ");
    }
   
     
    

}

bool TicketsTable::resolveTicket(Ticket &ticket, const std::string &newOperatorComment)
{
    std::string newStatus="resolved";
    std::string sql= "UPDATE tickets SET  operator_comment = ?, status = ? WHERE id = ?;";
    sqlite3_stmt* stmt;
  if(sqlite3_prepare_v2(db,sql.c_str(),-1,&stmt,nullptr)!= SQLITE_OK)
    {
        std::cerr<<"Error preparing statement: "<< sqlite3_errmsg(db)<<std::endl;
        return false;
    }

    sqlite3_bind_text(stmt,1,newOperatorComment.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,2,newStatus.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_int(stmt,3,ticket.getId());

    if(sqlite3_step(stmt)!= SQLITE_DONE)
    {
        std::cerr<<"Error resolving ticket: "<<sqlite3_errmsg(db)<<std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
    std::cout<<"Ticket resolved."<<std::endl;
    ticket.setTicketStatus(newStatus);
    ticket.setOperatorComment(newOperatorComment);
    sqlite3_finalize(stmt);
    return true;
    
}

std::vector<int> TicketsTable::viewAllClientTickets_client(const User &user)
{
    std::vector<int> ticketIds;
     std::string sql = "SELECT id FROM tickets WHERE user_id = ?;";
                
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) == SQLITE_OK) {
        sqlite3_bind_int(stmt, 1, user.getId());

        while (sqlite3_step(stmt) == SQLITE_ROW) {
            int id = sqlite3_column_int(stmt, 0);
            ticketIds.push_back(id);
        }

       
        sqlite3_finalize(stmt);
    } else {
       
        std::cerr << "Failed to execute query: " << sqlite3_errmsg(db) << std::endl;
    }

    return ticketIds;
}



std::vector<int> TicketsTable::viewTicketsByStatus(const std::string& status)
{
    std::vector<int> ticketIds;
    std::string sql = "SELECT id  FROM tickets WHERE status = ?;";
    sqlite3_stmt* stmt;

  

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) == SQLITE_OK) {
        sqlite3_bind_text(stmt, 1,status.c_str(),-1,SQLITE_STATIC );

        while (sqlite3_step(stmt) == SQLITE_ROW) {
            int id = sqlite3_column_int(stmt, 0);
            ticketIds.push_back(id);
        }

       
        sqlite3_finalize(stmt);
    } else {
       
        std::cerr << "Failed to execute query: " << sqlite3_errmsg(db) << std::endl;
    }

    return ticketIds;

}


std::vector<int> TicketsTable::viewAllActiveTickets_operator(const int operatorId)
{
    std::vector<int> ticketIds;
    //std::string status = "opened";  
    std::string sql = "SELECT id FROM tickets WHERE (status = 'opened' OR status = 'updated') AND assigned_to = ?;";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    //sqlite3_bind_text(stmt, 1, status.c_str(), -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 1, operatorId);

    while(sqlite3_step(stmt)== SQLITE_ROW)
    {
        int ticketId= sqlite3_column_int(stmt,0);
        ticketIds.push_back(ticketId);
    }
    sqlite3_finalize(stmt);
    return ticketIds;
}

std::vector<int> TicketsTable::viewAllReturnedTickets_client(const User &user)
{
    std::vector<int> ticketIds;
    std::string status = "returned";  
    std::string sql = "SELECT id FROM tickets WHERE status = ? AND user_id = ?;";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    sqlite3_bind_text(stmt, 1, status.c_str(), -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, user.getId());

    while(sqlite3_step(stmt)== SQLITE_ROW)
    {
        int ticketId= sqlite3_column_int(stmt,0);
        ticketIds.push_back(ticketId);
    }
    sqlite3_finalize(stmt);
    return ticketIds;
}

int TicketsTable::getTotalNumberOfTicketsResolved()
{
    std::string sql = "SELECT COUNT(*) FROM tickets WHERE status = 'resolved';";
    sqlite3_stmt* stmt;

  
    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    int resolvedCount = 0;


    if (sqlite3_step(stmt) == SQLITE_ROW) {
        resolvedCount = sqlite3_column_int(stmt, 0);
    }

    sqlite3_finalize(stmt);
    return resolvedCount;
}

User TicketsTable::getBestOperator()
{
     std::string sql = R"(
        SELECT users.id, users.username, users.password, users.acc_type, COUNT(tickets.id) AS resolved_count
        FROM users
        JOIN tickets ON users.id = tickets.assigned_to
        WHERE users.acc_type = 'Operator' AND tickets.status = 'resolved'
        GROUP BY users.id
        ORDER BY resolved_count DESC
        LIMIT 1;
    )";

    sqlite3_stmt* stmt;

    
    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statement");
    }

    int operatorId = -1;
    std::string username, password, accType;

    
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        operatorId = sqlite3_column_int(stmt, 0);  // Operator ID
        username = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 1));  // Username
        password = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 2));  // Password
        accType = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 3));  // Account type
    }

    sqlite3_finalize(stmt);

    if (operatorId == -1) {
        throw std::runtime_error("No operator found with resolved tickets");
    }

    
    return User(operatorId, username, password, accType);
   

}

const Ticket TicketsTable::getTicketByTicketId(int id) const
{
    std::string sql = R"(
        SELECT id, user_id, name, description, status, assigned_to, operator_comment
        FROM tickets
        WHERE id = ?;
    )";

    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        throw std::runtime_error("Error preparing statement: " + std::string(sqlite3_errmsg(db)));
    }

    sqlite3_bind_int(stmt, 1, id);

    Ticket ticket;
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        int ticketId = sqlite3_column_int(stmt, 0);
        int userId = sqlite3_column_int(stmt, 1);
        const char* name = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 2));
        const char* description = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 3));
        const char* status = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 4));
        int assignedTo = sqlite3_column_int(stmt, 5);
        const char* operatorComment = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 6));

        ticket = Ticket(
            ticketId,
            userId,
            description ? description : "",
            status ? status : "",
            name ? name : "",
            operatorComment ? operatorComment : "",
            assignedTo
        );
    } else {
        sqlite3_finalize(stmt);
        throw std::runtime_error("Ticket with ID " + std::to_string(id) + " not found.");
    }

    sqlite3_finalize(stmt);
    return ticket;
}