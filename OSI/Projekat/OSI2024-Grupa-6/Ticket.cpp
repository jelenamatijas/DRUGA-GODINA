#include "Ticket.h"


int Ticket::getId() const
{
    return id;
}

void Ticket::setId(int val)
{
    id=val;
}

int Ticket::getUserId() const
{
    return userId;
}

std::string Ticket::getIssueDesc() const
{
    return issueDesc;
}

std::string Ticket::getTicketStatus() const
{
    return ticketStatus;
}

std::string Ticket::getIssueName() const
{
    return issueName;
}

std::string Ticket::getOperatorComment() const
{
    return operatorComment;
}

int Ticket::getAssignedTo() const
{
    return assignedTo;
}

void Ticket::setTicketStatus(const std::string &status)
{
    ticketStatus=status;
}

void Ticket::setOperatorComment(const std::string &comm)
{
    operatorComment=comm;
}

void Ticket::setIssueDescription(const std::string &desc)
{
    issueDesc=desc;
}

void Ticket::setAssignedTo(int operatorId)
{
   assignedTo=operatorId;
}
