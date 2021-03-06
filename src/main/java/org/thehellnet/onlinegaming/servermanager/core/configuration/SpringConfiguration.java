package org.thehellnet.onlinegaming.servermanager.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Configuration
@ComponentScan(basePackages = "org.thehellnet.onlinegaming.servermanager.core")
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringConfiguration implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(servletContext);
        context.setConfigLocation("org.thehellnet.onlinegaming.servermanager.core.configuration");
        context.refresh();
        servletContext.addListener(new ContextLoaderListener(context));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("servlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/*");
    }
}
