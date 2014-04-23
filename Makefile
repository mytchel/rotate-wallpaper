JAVAC=javac

BINDIR?=/usr/local/bin

FILES=rotatewallpaper conkytextcolor imagebrightness setconkytextcolor

all: $(FILES) ImageProperties.java
	$(JAVAC) ImageProperties.java

install: all
	install -Dm 755 $(FILES) $(BINDIR)/
	install -Dm 755 ImageProperties.class $(BINDIR)/
