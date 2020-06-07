JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GMCase.java \
	GMGrid.java \
	GMHelp.java \
	GMImport.java \
	GMNew.java \
	GMTest.java \
	GMUI.java \
	Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java Main
