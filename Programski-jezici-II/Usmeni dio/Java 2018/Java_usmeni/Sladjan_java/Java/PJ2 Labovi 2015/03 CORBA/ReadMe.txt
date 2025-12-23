Kreira se IDL interfejs,
Generise se Java kod koriscenjem idlj -fall naziv.idl,
Kreira se Servant, Server, pa Klijent,
Iskompajlira se sve... i onda: 

Pokretanje CORBA aplikacije

Pokreni naming service
start tnameserv –ORBInitialPort 900
start orbd –ORBInitialPort 900

Pokreni server
start java KlasaServer –ORBInitialHost localhost  –ORBInitialPort 900

Pokreni klijenta
java KlasaClient –ORBInitialHost localhost  –ORBInitialPort 900
