package rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc // enable Spring MVC
@ComponentScan("rest.controller") // enable component scanning
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true)
		.favorPathExtension(true)
		.useJaf(false)
		.defaultContentType(MediaType.TEXT_HTML)
		.mediaType("html", MediaType.TEXT_HTML)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("json", MediaType.APPLICATION_JSON);
	}

	// assumes HTML
	@Bean
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}

	@Bean
	public ViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}

	// view of name "users"
	/*
	 * If the logical view name is “users”, then the configured
	 * BeanNameViewResolver resolves the View declared in the users() method.
	 * That’s because the bean name matches the logical view name.
	 */
	@Bean
	public View users() {
		return new MappingJackson2JsonView();
	}
}
