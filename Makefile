build: src/Davis.java
	javac -d bin/ -sourcepath src/ src/Davis.java
run: commands.txt
	make build
	java -cp bin/ Davis commands.txt
