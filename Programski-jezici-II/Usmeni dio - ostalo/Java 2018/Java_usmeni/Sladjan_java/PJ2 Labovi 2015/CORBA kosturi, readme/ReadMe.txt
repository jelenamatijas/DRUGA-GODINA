Kreira se IDL interfejs,
Generise se Java kod koriscenjem idlj -fall KAnaliza.idl,
Kreira se Servant, Server, pa Klijent,
Iskompajlira se sve... i onda: 

Pokretanje CORBA aplikacije

Pokreni naming service
start tnameserv –ORBInitialPort 900
start orbd –ORBInitialPort 900

Pokreni server
start java KAnalizaServer –ORBInitialHost localhost  –ORBInitialPort 900

Pokreni klijenta
java KAnalizaClient –ORBInitialHost localhost  –ORBInitialPort 900
