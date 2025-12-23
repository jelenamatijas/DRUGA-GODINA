Kako startati RMI aplikaciju?

1) Prevedu se svi .java fajlovi 

2) Pokrene se rmic:
rmic Server

3) Startuje se rmiregistry:
Win32: start rmiregistry
Solaris: rmiregistry &

4) Startuje se server:
java Server
//zbog permisija na samom racunaru na kom se server nalazi, potrebno je startati sam server
//navodjenjem policyfile-a (policyfile za server je, u ovom slucaju, server_policyfile.txt, u kom je potrebno
//samo izmjeniti putanju do vaseg servera...)
java -Djava.security.policy=server_policyfile.txt Server

5) Startuje se klijent:
java Client
//zbog permisija na samom racunaru na kom se server nalazi, potrebno je startati klijenta
//navodjenjem policyfile-a (policyfile za server je, u ovom slucaju, client_policyfile.txt, i nisu potrebne
//nikakve modifikacije...)
java -Djava.security.policy=client_policyfile.txt Client
