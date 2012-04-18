val: val_scores val_results
val_scores: $(patsubst %.xml,%.scores,$(wildcard spka_????-??.xml))
val_results: $(patsubst %.xml,%.results,$(wildcard spka_????-??_*.xml))

%.scores: %.xml
	xml val -q -e -s ../../../src/main/xml/xsd/scores.xsd $<

%.results: %.xml
	xml val -q -e -s ../../../src/main/xml/xsd/results.xsd $<
