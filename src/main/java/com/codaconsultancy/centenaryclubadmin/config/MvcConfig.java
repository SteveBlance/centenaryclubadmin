package com.codaconsultancy.centenaryclubadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/members").setViewName("members");
        registry.addViewController("/member").setViewName("member");
        registry.addViewController("/add-member").setViewName("add-member");
        registry.addViewController("/edit-member").setViewName("edit-member");
        registry.addViewController("/payments").setViewName("payments");
        registry.addViewController("/payment").setViewName("payment");
        registry.addViewController("/payment-references").setViewName("payment-references");
        registry.addViewController("/add-payment").setViewName("add-payment");
        registry.addViewController("/edit-payment").setViewName("edit-payment");
        registry.addViewController("/member-payments").setViewName("member-payments");
        registry.addViewController("/add-address").setViewName("add-address");
        registry.addViewController("/edit-address").setViewName("edit-address");
        registry.addViewController("/prepare-draw").setViewName("prepare-draw");
        registry.addViewController("/make-draw").setViewName("make-draw");
        registry.addViewController("/draws").setViewName("draws");
        registry.addViewController("/draw-result").setViewName("draw-result");
        registry.addViewController("/change-password").setViewName("change-password");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/error").setViewName("error");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }

}
