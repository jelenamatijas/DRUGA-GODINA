#include "UserTable.h"
#include "User.h"
#include <iostream>
#include <sqlite3.h>
#include "TicketsTable.h"
#include "Ticket.h"
#include "crow/app.h"
#include <filesystem>
#include <vector>
#include "Global.h"

bool isCommercialVersion=false;

bool upgradeToCommercial(const std::string &key)// Funkcija koja se poziva kada zeli da upgraduje, takodje u UserTable u add se provjerava da li je commercial prije nego sto doda korisnika
{
    std::string filename="random_keys.txt";
    std::ifstream file(filename);
    if(!file.is_open())
    {
        std::cerr<<"Error opening file.";
        return false;
    }

    std::string line;
    
    while(std::getline(file,line))
    {
        if(line==key)
        {
            isCommercialVersion=true;
            std::cout<<"Key authentification succesfull.Upgraded to the commercial version."<<std::endl;
            return true;
        }
    }
    file.close();
    std::cout<<"Key authentification unsuccesfull. Upgrading to the commercial version failed."<<std::endl;
   
    return false;
}

bool ends_with(const std::string& str, const std::string& suffix) {
    return str.size() >= suffix.size() &&
           str.compare(str.size() - suffix.size(), suffix.size(), suffix) == 0;
}

void initializeDefaultUsers(UserTable& userTable) {
    User admin(1, "admin", "adminpass", "Administrator");
    userTable.addUser(admin);

    //User operatorUser(2, "operator", "oppass", "Operator");
    //userTable.addUser(operatorUser);

    //User client(3, "client", "clipass", "Client");
   // userTable.addUser(client);
}

int main() {
   
   
   sqlite3* db;
    if (sqlite3_open("FinalTest.db", &db) != SQLITE_OK) {
        std::cerr << "Failed to open database: " << sqlite3_errmsg(db) << std::endl;
        return 1;
    }

    UserTable userTable(db);
    TicketsTable ticketsTable(db);
    
    //initializeDefaultUsers(userTable);
    
    crow::SimpleApp app;

    const std::string basePath = std::filesystem::current_path().string() + "/frontend/";

    CROW_ROUTE(app, "/").methods(crow::HTTPMethod::GET)([&basePath]() {
        std::ifstream file(basePath + "index.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open: " << basePath + "index.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf();
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str();
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/upgrade").methods(crow::HTTPMethod::GET)([&basePath]() {
        std::ifstream file(basePath + "upgrade.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "upgrade.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/upgrade").methods(crow::HTTPMethod::POST)([](const crow::request& req) {
        auto body = crow::json::load(req.body);

        std::cout << "Received body: " << req.body << std::endl;

        if (!body || !body.has("password")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        std::string password = body["password"].s();

        bool upgrade = upgradeToCommercial(password);
        if(!upgrade){
            return crow::response(500, "Wrong password.");
        }
        return crow::response(200, "Upgraded successfully.");
    });


    CROW_ROUTE(app, "/sign-in").methods(crow::HTTPMethod::GET)([&basePath]() {
        std::ifstream file(basePath + "sign-in.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "sign-in.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

     CROW_ROUTE(app, "/sign-in").methods(crow::HTTPMethod::POST)([&userTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        std::cout << "Received body: " << req.body << std::endl;

        if (!body || !body.has("username") || !body.has("password") || !body.has("typeOfUser")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        std::string username = body["username"].s();
        std::string password = body["password"].s();
        std::string typeOfUser = body["typeOfUser"].s();

        User user(0, username, password, typeOfUser);

        int userId = userTable.getUserId(user);

         if (userId > 0) {
            if (userTable.userAuthentication(user)) {
                crow::json::wvalue response;
                response["status"] = "success";
                response["message"] = "Sign in successful!";
                response["redirectUrl"] = user.determineRedirectUrl() + std::to_string(userId); 
                return crow::response(200, response);
            } else {
                crow::json::wvalue response;
                response["status"] = "failure";
                response["message"] = "Invalid password.";
                return crow::response(401, response);
            }
        } else {
            crow::json::wvalue response;
            response["status"] = "failure";
            response["message"] = "User not found.";
            return crow::response(404, response);
        }
    });

    CROW_ROUTE(app, "/admin-page").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        auto query_params = crow::query_string(req.url_params);

        std::string username = query_params.get("username") ? query_params.get("username") : "Unknown";
        std::string userType = query_params.get("userType") ? query_params.get("userType") : "Unknown";
        std::string id = query_params.get("id") ? query_params.get("id") : "0";

        const std::string path = basePath + "admin-page.html"; 
        std::ifstream file(path);
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << path << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf();
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str();
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/client-page").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        auto query_params = crow::query_string(req.url_params);

        std::string username = query_params.get("username") ? query_params.get("username") : "Unknown";
        std::string userType = query_params.get("userType") ? query_params.get("userType") : "Unknown";
        std::string id = query_params.get("id") ? query_params.get("id") : "0";

        std::cout << "Client page accessed by: " << username << " (Type: " << userType << ", ID: " << id << ")" << std::endl;

        const std::string path = basePath + "client-page.html"; 
        std::ifstream file(path);
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << path << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf();
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str();
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/<string>").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req, std::string filename) {
        std::string path = basePath + filename; 
        std::ifstream file(path, std::ios::binary);
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << path << std::endl;
            return crow::response(404, "File not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf();
        file.close();

        crow::response res;
        res.code = 200;

        if (ends_with(filename, ".png")) {
            res.add_header("Content-Type", "image/png");
        } else if (ends_with(filename, ".jpg") || ends_with(filename,".jpeg")) {
            res.add_header("Content-Type", "image/jpeg");
        } else if (ends_with(filename, ".gif")) {
            res.add_header("Content-Type", "image/gif");
        }

        res.body = buffer.str();
        return res;
    });


    CROW_ROUTE(app, "/logout").methods(crow::HTTPMethod::POST)([](const crow::request& req) {
        return crow::response(200, "Logged out successfully");
    });

    CROW_ROUTE(app, "/make-user").methods(crow::HTTPMethod::POST)([&userTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        std::cout << "Received body: " << req.body << std::endl;

        if (!body || !body.has("username") || !body.has("password") || !body.has("typeOfUser")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        std::string username = body["username"].s();
        std::string password = body["password"].s();
        std::string typeOfUser = body["typeOfUser"].s();

        User user(0, username, password, typeOfUser);

        if (userTable.addUser(user)) {
            crow::json::wvalue response;
            response["status"] = "success";
            response["message"] = "Sign in successful!";
            return crow::response(200, response);
        } else {
            std::cerr << "Failed to add User 1.\n";

            crow::json::wvalue response;
            response["status"] = "failure";
            response["message"] = "There is same user in database.";
            return crow::response(401, response);
        }
    });

    CROW_ROUTE(app, "/make-ticket-form").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "make-ticket-form.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "make-ticket-form.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/make-ticket-form").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("name") || !body.has("description") || !body.has("userId")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        std::string name = body["name"].s();
        std::string description = body["description"].s();
        int userId = body["userId"].i();

        Ticket ticket(0,userId, description, "opened", name, " ", 0);
        
        bool inserted = ticketsTable.addTicket(ticket)&&ticketsTable.assignTicketToOperator(ticket);  

        if (inserted) {
            return crow::response(200, "Ticket created successfully.");
        } else {
            return crow::response(500, "Failed to create ticket.");
        }
    });

    CROW_ROUTE(app, "/show-tickets-admin").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        //auto body = crow::json::load(req.body);

        crow::json::wvalue response;

        crow::json::wvalue::list jsonArray;
        try{
            std::vector<int> ticketIDs = ticketsTable.getAllTicketIds();
            for (int num : ticketIDs) {
                jsonArray.push_back(num);
            }
        }
        catch(const std::exception& e){
            return crow::response(500, "Failed to load tickets.");
        }

        response["ticketIDs"] = std::move(jsonArray);
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/show-tickets-admin").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "show-tickets-admin.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "show-tickets-admin.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/show-tickets").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "show-tickets.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "show-tickets.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });


    CROW_ROUTE(app, "/show-tickets").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("userId")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int userId = body["userId"].i();

        crow::json::wvalue::list jsonArray;
        User user(userId, "username", "password", "user type");
        try{
            std::vector<int> ticketIDs = ticketsTable.viewAllClientTickets_client(user);
            for (int num : ticketIDs) {
                jsonArray.push_back(num);
            }
        }
        catch(const std::exception& e){
            return crow::response(500, "Failed to load tickets.");
        }
        response["ticketIDs"] = std::move(jsonArray);
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/show-ticket").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "show-ticket.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "show-ticket.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    //TODO: vraca ok podatke za tiket sa id 1 i 2 ali ne i za ostale
    // isto vazi i za show-ticket-admin
    CROW_ROUTE(app, "/show-ticket").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("id")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int id = body["id"].i();
        if(id<0){
             return crow::response(401, "Invalid id value.");
        }

        try{
            Ticket ticket = ticketsTable.getTicketByTicketId(id);

            response["id"] = id;
            response["name"] = ticket.getIssueName();
            response["description"] = ticket.getIssueDesc();
            response["status"] = ticket.getTicketStatus();
            response["operator_comment"] = ticket.getOperatorComment();
            response["assigned_to"] = ticket.getAssignedTo();
            return crow::response(200, response);
        }catch(std::exception &e){
             return crow::response(500, "Ticket not found.");
        }
        
    });


    CROW_ROUTE(app, "/show-ticket-admin").methods(crow::HTTPMethod::GET)([&basePath]() {
        std::ifstream file(basePath + "show-ticket-admin.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "show-ticket-admin.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    //TODO: vraca ok podatke za tiket sa id 1 i 2 ali ne i za ostale
    CROW_ROUTE(app, "/show-ticket-admin").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("id")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int id = body["id"].i();
        if(id<0){
             return crow::response(401, "Invalid id value.");
        }
        Ticket ticket = ticketsTable.getTicketByTicketId(id);

        response["id"] = ticket.getId();
        response["name"] = ticket.getIssueName();
        response["description"] = ticket.getIssueDesc();
        response["status"] = ticket.getTicketStatus();
        response["operator_comment"] = ticket.getOperatorComment();
        response["assigned_to"] = ticket.getAssignedTo();
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/operator-page").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
         std::ifstream file(basePath + "operator-page.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "operator-page.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/operator-page").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
         auto body = crow::json::load(req.body);

        if (!body || !body.has("id")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int userId = body["id"].i();

        crow::json::wvalue::list jsonArray;
        try{
            std::vector<int> ticketIDs = ticketsTable.viewAllActiveTickets_operator(userId);
            for (int num : ticketIDs) {
                jsonArray.push_back(num);
            }
        }
        catch(const std::exception& e){
            return crow::response(500, "Failed to load tickets.");
        }

        //jsonArray.push_back(1);
        response["ticketIDs"] = std::move(jsonArray);
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/manage-ticket").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "manage-ticket.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "manage-ticket.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/manage-ticket").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("id")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int id = body["id"].i();
        if(id<0){
             return crow::response(401, "Invalid id value.");
        }
        Ticket ticket = ticketsTable.getTicketByTicketId(id);

        response["clientId"] = ticket.getUserId();
        response["name"] = ticket.getIssueName();
        response["description"] = ticket.getIssueDesc();
        response["status"] = ticket.getTicketStatus();
        response["operator_comment"] = ticket.getOperatorComment();
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/update-ticket").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("id") || !body.has("userId") || !body.has("name") || !body.has("description") || 
            !body.has("status") || !body.has("operator_comment") || !body.has("assigned_to")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        int id = body["id"].i();
        int userId = body["userId"].i();
        std::string name = body["name"].s();
        std::string description = body["description"].s();
        std::string status = body["status"].s();
        std::string operator_comment = body["operator_comment"].s();
        int assignedTo = body["assigned_to"].i();

        Ticket t(id, userId, description, status, name, operator_comment, assignedTo);
        std::cout<<operator_comment<<std::endl;
        bool updated = ticketsTable.updateTicket_operater(t, operator_comment, status);
        crow::json::wvalue response;
        if (updated) {
            response["message"] = "Ticket updated successfully.";
            return crow::response(200, response);
        } else {
            response["message"] = "Failed to update the ticket.";
            return crow::response(500, response);
        }
    });

    
    CROW_ROUTE(app, "/alter-tickets").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "alter-tickets.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "alter-tickets.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/alter-tickets").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("userId")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int userId = body["userId"].i();

        crow::json::wvalue::list jsonArray;
        User user(userId, "username", "password", "user type");
        try{
            std::vector<int> ticketIDs = ticketsTable.viewAllReturnedTickets_client(user);
            for (int num : ticketIDs) {
                jsonArray.push_back(num);
            }
        }
        catch(const std::exception& e){
            return crow::response(500, "Failed to load tickets.");
        }
        response["ticketIDs"] = std::move(jsonArray);
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/alter-ticket").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "alter-ticket.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "alter-ticket.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });

    CROW_ROUTE(app, "/alter-ticket").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
       auto body = crow::json::load(req.body);

        if (!body || !body.has("id")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        crow::json::wvalue response;
        int id = body["id"].i();
        if(id<0){
             return crow::response(401, "Invalid id value.");
        }
        try{
            Ticket ticket = ticketsTable.getTicketByTicketId(id);

            std::cout<<"Ticked id try: "<<ticket.getId()<<" "<<ticket.getOperatorComment()<<std::endl;

            response["id"] = ticket.getId();
            response["name"] = ticket.getIssueName();
            response["description"] = ticket.getIssueDesc();
            response["status"] = ticket.getTicketStatus();
            response["operator_comment"] = ticket.getOperatorComment();
            response["assigned_to"] = ticket.getAssignedTo();
        }catch(std::exception& e){
            response["id"] = 0;
            response["name"] = " ";
            response["description"] = " ";
            response["status"] = " ";
            response["operator_comment"] = " ";
            response["assigned_to"] = " ";
        }
        
        return crow::response(200, response);
    });

    CROW_ROUTE(app, "/alter-ticket-client").methods(crow::HTTPMethod::POST)([&ticketsTable](const crow::request& req) {
        auto body = crow::json::load(req.body);

        if (!body || !body.has("id") || !body.has("userId") || !body.has("description")) {
            return crow::response(400, "Invalid request. Missing fields.");
        }

        int id = body["id"].i();
        std::string description = body["description"].s();
        int userId = body["userId"].i();

        Ticket T(id, userId, description, "status", "name", "comment", 0);
        bool updated = ticketsTable.updateTicket_client(T, description);

        crow::json::wvalue response;
        if (updated) {
            response["message"] = "Ticket updated successfully.";
            return crow::response(200, response);
        } else {
            response["message"] = "Failed to update the ticket.";
            return crow::response(500, response);
        }
    });

    CROW_ROUTE(app, "/statistics").methods(crow::HTTPMethod::OPTIONS)([]() {
        crow::response res;
        res.add_header("Access-Control-Allow-Origin", "*");
        res.add_header("Access-Control-Allow-Methods", "POST, OPTIONS");
        res.add_header("Access-Control-Allow-Headers", "Content-Type");
        return res;
    });

    CROW_ROUTE(app, "/statistics").methods(crow::HTTPMethod::GET)([&basePath](const crow::request& req) {
        std::ifstream file(basePath + "statistics.html");
        if (!file.is_open()) {
            std::cerr << "Failed to open file: " << basePath + "statistics.html" << std::endl;
            return crow::response(404, "Page not found.");
        }

        std::stringstream buffer;
        buffer << file.rdbuf(); 
        file.close();

        crow::response res;
        res.code = 200;
        res.body = buffer.str(); 
        res.add_header("Content-Type", "text/html");
        return res;
    });
    
    CROW_ROUTE(app, "/statistics").methods(crow::HTTPMethod::POST)([&ticketsTable]() {
        crow::json::wvalue response;
        
        std::vector<int> t = ticketsTable.getAllTicketIds();    
        
        try{
            User u = ticketsTable.getBestOperator();
            response["resolved"] = ticketsTable.getTotalNumberOfTicketsResolved();
            response["bestOperator"] = u.getUsername();
            
        }catch(std::exception& e){
            std::cout<<e.what()<<std::endl;
            response["resolved"] = 0;
            response["bestOperator"] = " ";
        }
        response["numberOfTickets"] = t.size();
        return crow::response(200, response);
    });
    
    app.port(18080).multithreaded().run();
    sqlite3_close(db);
}
