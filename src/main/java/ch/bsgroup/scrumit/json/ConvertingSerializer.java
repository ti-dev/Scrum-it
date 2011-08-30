package ch.bsgroup.scrumit.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

final class ConvertingSerializer extends JsonSerializer<Object> {

	private final ConversionService conversionService;

	private final TypeDescriptor sourceType;

	public ConvertingSerializer(ConversionService conversionService, TypeDescriptor sourceType) {
		this.conversionService = conversionService;
		this.sourceType = sourceType;
	}

	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString((String) this.conversionService.convert(value, sourceType, TypeDescriptor.valueOf(String.class)));
	}
}