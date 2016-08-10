all:
	java MakeMatrix
	java Main matrixFile.txt

run:
	java Main matrixFile.txt
	
build:
	javac *.java

matrix:
	java MakeMatrix