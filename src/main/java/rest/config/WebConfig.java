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
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

@Configuration
@EnableWebMvc // enable Spring MVC
@ComponentScan("rest.controller") // enable component scanning
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true)
		.favorPathExtension(true)
		.favorParameter(false)
		.useJaf(false)
		.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(
                new MappingJackson2XmlView(),
                new MappingJackson2JsonView());
    }

	// assumes HTML
/*	@Bean
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}*/

/*	@Bean
	public ViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}*/

	// view of name "users"
	/*
	 * If the logical view name is “users”, then the configured
	 * BeanNameViewResolver resolves the View declared in the users() method.
	 * That’s because the bean name matches the logical view name.
	 */
/*	@Bean
	public View users() {
		return new MappingJackson2JsonView();
	}*/
}
