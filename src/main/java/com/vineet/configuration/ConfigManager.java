package com.vineet.configuration;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationBuilder;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

public class ConfigManager {
	private static final String CONFIG_FILE = "config.xml";
	private static Configuration config = null;

	private static void init() {
		if (config == null) {
			try {
				AbstractConfiguration.setDefaultListDelimiter(';');
				ConfigurationBuilder configBuilder = new DefaultConfigurationBuilder(CONFIG_FILE);
				config = configBuilder.getConfiguration();
			} catch (Exception e) {
				System.err.println("Error Reading Config File: " + CONFIG_FILE);
				e.printStackTrace();
			}
		}
	}

	public static Configuration getConfiguration() {
		if (config == null) {
			init();
		}
		return config;
	}

	public static String getString(String key, Object... args) {
		if (config != null) {
			return MessageFormat.format(config.getString(key), args);
		} else {
			init();
			return config.getString(key);
		}
	}

	public static void printString() {
		Properties p = System.getProperties();
		Enumeration keys = p.keys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) p.get(key);
			System.out.println(key + ": " + value);
		}
		return;
	}

}
