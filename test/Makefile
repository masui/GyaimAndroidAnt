#
# 辞書検索、ローマ字かな変換のテスト
#

all: compile test dir
romakana: compile_romakana test_romakana

compile: compile_localdict compile_romakana
dir:
	-mkdir com
	-mkdir com/pitecan
	-mkdir com/pitecan/gyaim
compile_localdict: dir
	javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' \
		-d . \
		../src/com/pitecan/gyaim/LocalDict.java \
		Message.java \
		Search.java \
		SearchTask.java \
		Gyaim.java \
		LocalDictTest.java
compile_romakana:
	javac -classpath 'jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar' \
		-d . \
		../src/com/pitecan/gyaim/Romakana.java \
		RomakanaTest.java

test: test_localdict test_romakana
test_localdict:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore LocalDictTest
test_romakana:
	java -classpath "jar/junit-4.12.jar:jar/hamcrest-all-1.3.jar:.:../src" org.junit.runner.JUnitCore RomakanaTest

clean:
	/bin/rm -r -f *.class com
#	/bin/rm -f *.class ../src/com/pitecan/gyaim/*.class

