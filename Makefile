# in make use "#" to put in comments, like this one!

# -cp and -classpath are the same and specify where the .class files are
# -sourcepath specifies where the .java files are and is only used with javac
# The weird part of these commands is: "$(filter-out $@,$(MAKECMDGOALS))". Don't be alaermed. It is simply all the parameters after make <cmd>

build:
	javac -cp *.jar -sourcepath . $(filter-out $@,$(MAKECMDGOALS))

run:
	java -classpath ".;*" $(filter-out $@,$(MAKECMDGOALS))

# If you don't put this last line in make will try to execute the parameters and throw weird confusing messages
%:
	@: