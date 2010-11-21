package com.camerondevelope.config;

import com.google.appengine.api.utils.SystemProperty;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 12:00:50 AM
 * Singleton pattern is http://en.wikipedia.org/wiki/Singleton_pattern#The_solution_of_Bill_Pugh
 */
public class Configuration {

    private static final Logger log = Logger.getLogger(Configuration.class.getName());

    private final CompositeConfiguration config;

    private Configuration() {
        config = new CompositeConfiguration();
        try {

            // Properties are immutable, so the dev version goes first
            URL pathUrl = this.getClass().getClassLoader().getResource("application.properties");
            if (isDevelopmentMode() && pathUrl != null) {
                String regPath = pathUrl.getPath();
                config.addConfiguration(new PropertiesConfiguration(regPath));
                log.info("Using application.properties settings");
            }

            // In source control, should always exist
            String defaultPath = this.getClass().getClassLoader().getResource("application.properties.default").getPath();
            config.addConfiguration(new PropertiesConfiguration(defaultPath));
        } catch (ConfigurationException e) {
            //TODO:Log
            System.out.println("e = " + e);
        }
    }

    private static class ConfigurationHolder {
        public static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    public String getApplicationId() {
        return config.getString("facebook.application.id");
    }

    public String getApplicationSecret() {
        return config.getString("facebook.application.secret");
    }

    public boolean isDevelopmentMode() {
        return SystemProperty.environment.value() == SystemProperty.Environment.Value.Development;
    }

}
