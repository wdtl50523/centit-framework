package com.centit.framework.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by zou_wy on 2017/3/29.
 */
@PropertySource("classpath:system.properties")
@Configuration
public class WebBeanConfig implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Bean
//    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
//        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
//        propertyConfigurer.setLocation(new ClassPathResource("system.properties"));
//        return propertyConfigurer;
//    }

//    @Bean
//    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter(){
//        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter =
//                new FastJsonHttpMessageConverter4();
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setFeatures(Feature.AllowArbitraryCommas,Feature.AllowUnQuotedFieldNames,
//                Feature.DisableCircularReferenceDetect);
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        return fastJsonHttpMessageConverter;
//    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messagesource/base/messages");
        Properties properties = new Properties();
        properties.put("fileEncodings", "utf-8");
        messageSource.setFileEncodings(properties);
        messageSource.setCacheSeconds(120);
        return messageSource;
    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionServiceBean() {
        return new FormattingConversionServiceFactoryBean();
    }

    @Bean
    public ConfigurableWebBindingInitializer webBindingInitializer() {
        ConfigurableWebBindingInitializer webBindingInitializer =
                new ConfigurableWebBindingInitializer();
        return webBindingInitializer;
    }

}
