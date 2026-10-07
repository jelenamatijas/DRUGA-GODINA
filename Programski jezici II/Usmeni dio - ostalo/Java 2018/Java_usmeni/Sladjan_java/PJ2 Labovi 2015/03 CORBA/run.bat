start tnameserv –ORBInitialPort 900
start orbd –ORBInitialPort 900

start java NaucneKonferencijeServer –ORBInitialHost localhost  –ORBInitialPort 900
start java NaucneKonferencijeClient –ORBInitialHost localhost  –ORBInitialPort 900
