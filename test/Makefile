compile:
	cp -f Message.java ../src/com/pitecan/gyaim
	chmod 444 ../src/com/pitecan/gyaim/Message.java
	cp -f SearchTask.java ../src/com/pitecan/gyaim
	chmod 444 ../src/com/pitecan/gyaim/SearchTask.java
	cp -f Search.java ../src/com/pitecan/gyaim
	chmod 444 ../src/com/pitecan/gyaim/Search.java
	cp -f Gyaim.java ../src/com/pitecan/gyaim
	chmod 444 ../src/com/pitecan/gyaim/Gyaim.java
	javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' \
		../src/com/pitecan/gyaim/LocalDict.java \
		../src/com/pitecan/gyaim/Message.java \
		../src/com/pitecan/gyaim/Search.java \
		../src/com/pitecan/gyaim/SearchTask.java \
		../src/com/pitecan/gyaim/Gyaim.java \
		LocalDictTest.java

#	javac -classpath '.:jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' Message.java ../src/com/pitecan/gyaim/LocalDict.java LocalDictTest.java

test:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore LocalDictTest

#	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src/com/pitecan/gyaim" org.junit.runner.JUnitCore LocalDictTest
#	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore ../src/com/pitecan/gyaim/LocalDictTest

clean:
	/bin/rm -f *.class ../src/com/pitecan/gyaim/*.class

