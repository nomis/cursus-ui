val: $(patsubst %.xsd,%.val,$(wildcard *.xsd))

%.val: %.xsd
	xml val -q -e -s http://www.w3.org/2001/XMLSchema.xsd $<
