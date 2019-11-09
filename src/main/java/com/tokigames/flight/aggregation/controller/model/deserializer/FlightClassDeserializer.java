package com.tokigames.flight.aggregation.controller.model.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.tokigames.flight.aggregation.constants.FlightClassEnumerator;

public class FlightClassDeserializer extends JsonDeserializer<FlightClassEnumerator>{

	@Override
	public FlightClassEnumerator deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode jsonNode = oc.readTree(jsonParser);
        
        String flyingClassName = jsonNode.textValue();
        
        for(FlightClassEnumerator flyingClassEnumerator : FlightClassEnumerator.values()) {
            if(flyingClassEnumerator.flyingClass.equals(flyingClassName)) {
                return flyingClassEnumerator;
            }
        }
		return null;
	}

}
