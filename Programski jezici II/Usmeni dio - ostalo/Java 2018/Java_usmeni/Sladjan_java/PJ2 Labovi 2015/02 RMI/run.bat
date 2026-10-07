rmic TuristickaAgencijaServer
start rmiregistry
::start java TuristickaAgencijaServer
start java -Djava.security.policy=server_policyfile.txt TuristickaAgencijaServer
::start java TuristickaAgencijaClient
start java -Djava.security.policy=client_policyfile.txt TuristickaAgencijaClient

