if "%1" == "build" javac -cp "gson-2.2.4.jar" -sourcepath . %2
if "%1" == "run" java -classpath ".;*" %2