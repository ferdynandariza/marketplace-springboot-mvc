package io.github.ferdynandariza.marketplace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var session = new SessionLocaleResolver();
        var indonesia = new Locale("id", "ID");
        session.setDefaultLocale(indonesia);
        return session;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Fungsi memanggil fungsi, bukan path
        registry.addViewController("/").setViewName("layout");
        registry.addViewController("/shop").setViewName("forward:/shop/index");
        registry.addViewController("/shipment").setViewName("forward:/shipment/index");
        registry.addViewController("/history").setViewName("forward:/history/index");
        registry.addViewController("/admin").setViewName("forward:/admin/index");

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }
}
