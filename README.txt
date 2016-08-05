DETECTIVE ADVENTURE!
you have to javac the things in tholdem and then cp the class files back
also *.jar doesn't work as a cp for java
also ':' is the delimited for linux and ';' is the delimiter for windows also you have to put it in quotes.
javac -cp *.jar -sourcepath . -sourcepath tholdem/ *.java
java -cp "gson-2.2.4.jar:." DetAdv
