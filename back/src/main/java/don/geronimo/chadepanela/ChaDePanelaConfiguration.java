package don.geronimo.chadepanela;

import don.geronimo.chadepanela.filter.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ChaDePanelaConfiguration implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean jwtFilter(){
        System.out.println("registro do JWT FILTER");
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(getFilter());
        registrationBean.addUrlPatterns("/secure/*");
        return registrationBean;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("registro do CORS");
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public JWTFilter getFilter(){
        return new JWTFilter();
    }
}