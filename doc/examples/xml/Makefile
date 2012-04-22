val: val_scores val_results
val_scores: $(patsubst %.xml,%.scores,$(wildcard spka_????-??.xml)) $(patsubst %.xml,%.scores,$(wildcard fisly_????c8?.xml))
val_results: $(patsubst %.xml,%.results,$(wildcard spka_????-??_*.xml)) $(patsubst %.xml,%.results,$(wildcard fisly_????c8??_*.xml))

%.scores: %.xml
	xml val -q -e -s ../../../src/main/xml/xsd/scores-v1.xsd $<

%.results: %.xml
	xml val -q -e -s ../../../src/main/xml/xsd/results-v1.xsd $<
