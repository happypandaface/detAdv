if "%1" == "build" javac -cp *.jar -sourcepath . %2
if "%1" == "run" java -classpath ".;*" %2