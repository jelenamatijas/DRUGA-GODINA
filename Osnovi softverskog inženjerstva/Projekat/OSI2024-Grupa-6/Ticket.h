#pragma once
#include <string>
class Ticket
{
private:
    int id;
    int userId;
    std::string issueName;
    std::string issueDesc;
    std::string ticketStatus;
    std::string operatorComment;
    int assignedTo; 

public:
    Ticket(int id, int userId, std::string issueDesc, 
    std::string ticketStatus,std::string issueName,std::string operatorComment, int assignedTo):
    id(id), userId(userId),issueDesc(issueDesc),ticketStatus(ticketStatus),issueName(issueName), operatorComment(operatorComment),assignedTo(assignedTo){}
    Ticket(){}
    int getId() const;
    void setId(int val);
    int getUserId() const;
    std::string getIssueDesc() const;
    std::string getTicketStatus() const;
    std::string getIssueName() const;
    std::string getOperatorComment() const;
    int getAssignedTo() const;
    void setTicketStatus(const std::string& status);
    void setOperatorComment(const std::string& comm);
    void setIssueDescription(const std::string& desc);
    void setAssignedTo(int operatorId);
};


