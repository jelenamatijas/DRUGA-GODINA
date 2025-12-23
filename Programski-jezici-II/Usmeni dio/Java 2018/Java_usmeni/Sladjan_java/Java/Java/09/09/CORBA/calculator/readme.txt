idlj -fall calculator.idl
javac calculator/*.java
javac *.java
start orbd -ORBInitialPort 1050 -ORBInitialHost localhost
start java CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost
java CalculatorClient -ORBInitialPort 1050 -ORBInitialHost