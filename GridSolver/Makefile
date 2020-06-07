JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GSCase.java \
	GSGrid.java \
	GSImport.java \
	GSTest.java \
	GSMenu.java \
	GSPlay.java \
	GSWin.java \
	GSSolver.java \
	GSBackGround.java \
	Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	java Main
