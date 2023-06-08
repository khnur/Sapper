@echo off
javac -cp . Main.java
java -cp . Main
del /s *.class