package ch.bsgroup.scrumit.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

final class ConvertingDeserializer extends JsonDeserializer<Object> {

	private final ConversionService conversionService;

	private final TypeDescriptor targetType;

	public ConvertingDeserializer(ConversionService conversionService, TypeDescriptor targetType) {
		this.conversionService = conversionService;
		this.targetType = targetType;
	}

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Object value = jp.getText();
		TypeDescriptor sourceType = TypeDescriptor.forObject(value);
		return this.conversionService.convert(value, sourceType, targetType);
	}
}