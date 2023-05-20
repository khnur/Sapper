@echo off
javac -cp . Main.java
java -cp . Main
pause
del /s *.class