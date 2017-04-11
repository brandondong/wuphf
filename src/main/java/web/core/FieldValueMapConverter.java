package web.core;

import java.util.Map;

import core.schema.FieldValueMap;
import core.schema.Fields;

class FieldValueMapConverter {

	private final Fields fields;

	public FieldValueMapConverter(Fields fields) {
		this.fields = fields;
	}

	public FieldValueMap createMap(Map<String, String> valueMap) {
		FieldValueMap.Builder builder = FieldValueMap.builder(fields);
		valueMap.keySet().forEach(f -> builder.setField(f, valueMap.get(f)));
		return builder.create();
	}

}
