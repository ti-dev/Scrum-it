package ch.bsgroup.scrumit.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

class FormatAnnotationIntrospector extends NopAnnotationIntrospector {

	private final ConversionService conversionService;

	public FormatAnnotationIntrospector(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@Override
	public boolean isHandled(Annotation ann) {
		return ann.toString().startsWith("@org.springframework.format.annotation");
	}

	@Override
	public Object findDeserializer(Annotated a) {
		if (a instanceof AnnotatedMethod) {
			AnnotatedMethod method = (AnnotatedMethod) a;
			TypeDescriptor typeDescriptor = getTypeDescriptorForDeserializer(method);
			for (Annotation ann : typeDescriptor.getAnnotations()){
				if (isHandled(ann)) {
					return new ConvertingDeserializer(this.conversionService, typeDescriptor);
				}
			}
		}
		return null;
	}

	@Override
	public Object findSerializer(Annotated a) {
		// this seems to be called even if the method is not annotated with an actual annotation???
		if (a instanceof AnnotatedMethod) {
			AnnotatedMethod method = (AnnotatedMethod) a;
			TypeDescriptor typeDescriptor = getTypeDescriptorForSerializer(method);
			for (Annotation ann : typeDescriptor.getAnnotations()){
				if (isHandled(ann)) {
					return new ConvertingSerializer(this.conversionService, typeDescriptor);
				}
			}
		}
		return null;
	}

	private TypeDescriptor getTypeDescriptorForDeserializer(AnnotatedMethod method) {
		// TODO - Could handle annotated getter/is/setter method here - those should probably take precedence over field annotations
		Assert.isTrue(method.getName().startsWith("set"), "Expected a setter method, but was " + method.getName());
		String fieldName = StringUtils.uncapitalize(method.getName().substring(3));
		// TODO the field could obviously be named anything, relies on convention		
		Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
		if (field != null) {
			return new TypeDescriptor(field);
		}
		return null;
	}

	private TypeDescriptor getTypeDescriptorForSerializer(AnnotatedMethod method) {
		// TODO - Could handle annotated getter/is methods here - those should probably take precedence over field annotations
		String fieldName;
		if (method.getName().startsWith("get")) {
			fieldName = StringUtils.uncapitalize(method.getName().substring(3));
		} else if (method.getName().startsWith("is")) {
			fieldName = StringUtils.uncapitalize(method.getName().substring(2));			
		} else {
			throw new IllegalArgumentException("Expected a getter method, but was " + method.getName());
		}
		// TODO the field could obviously be named anything, relies on convention
		Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
		if (field != null) {
			return new TypeDescriptor(field);
		}
		return null;
	}
}