compile:
	javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' ../src/com/pitecan/gyaim/LocalDict.java LocalDictTest.java
#	javac -classpath '.:jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' Message.java ../src/com/pitecan/gyaim/LocalDict.java LocalDictTest.java

test:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore LocalDictTest


#	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src/com/pitecan/gyaim" org.junit.runner.JUnitCore LocalDictTest

clean:
	/bin/rm -f *.class

