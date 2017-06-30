#
# 辞書検索、ローマ字かな変換のテスト
#

all: compile test
romakana: compile_romakana test_romakana

compile: compile_localdict compile_romakana
compile_localdict:
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
compile_romakana:
	javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' \
		../src/com/pitecan/gyaim/Romakana.java \
		RomakanaTest.java

test: test_localdict test_romakana
test_localdict:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore LocalDictTest
test_romakana:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore RomakanaTest

clean:
	/bin/rm -f *.class ../src/com/pitecan/gyaim/*.class

