package ch.bsgroup.scrumit.json;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * A custom BeanPostProcessor for Spring MVC environments that configures Jackson
 * with awareness of Spring-specific format annotations such as @DateTimeFormat and @NumberFormat.
 * 
 * This is an example of extending Spring to plug-in unique functionality not yet available
 * in a released Spring distribution.
 */
@Component
public class JacksonConversionServiceConfigurer implements BeanPostProcessor {

	private final ConversionService conversionService;

	@Autowired
	public JacksonConversionServiceConfigurer(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof AnnotationMethodHandlerAdapter) {
			AnnotationMethodHandlerAdapter adapter = (AnnotationMethodHandlerAdapter) bean;
			HttpMessageConverter<?>[] converters = adapter.getMessageConverters();
			for (HttpMessageConverter<?> converter : converters) {
				if (converter instanceof MappingJacksonHttpMessageConverter) {
					MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
					jsonConverter.setObjectMapper(new ConversionServiceAwareObjectMapper(this.conversionService));
				}				
			}
		}
		return bean;
	}
}