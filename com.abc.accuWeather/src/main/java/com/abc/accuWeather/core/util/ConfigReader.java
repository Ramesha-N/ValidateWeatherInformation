package com.abc.accuWeather.core.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.tree.OverrideCombiner;

public class ConfigReader {
	
	private static ConfigReader single_instance = null;
	
	private final String propertyFolder = System.getProperty("user.dir")+"/src/test/resources/config";
	
	private CombinedConfiguration comconfig = new CombinedConfiguration(new OverrideCombiner());
	
	public ConfigReader() {
		try {
			loadPropertiesToCombinedConfig(propertyFolder);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigReader getInstance() {
		
		if(single_instance == null)
			single_instance = new ConfigReader();
		
		return single_instance;
	}
	
	public String getStringProperty(String key) {
		if(comconfig.getProperty(key).toString().trim() == null)
			return null;
		
		return comconfig.getProperty(key).toString().trim();
	}
	
	
	private void loadPropertiesToCombinedConfig(String folderPath) {
		Parameters params = new Parameters();
		List<File> fileNames = getFiles(folderPath);
		for(File fileName : fileNames) {
			try {
				FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
					    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
					    .configure(params.fileBased().setFile(fileName));
				
				Configuration config = builder.getConfiguration();
				
				comconfig.addConfiguration(config);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<File> getFiles(String folderPath){
				
		File file = new File(folderPath);
		File[] listOfFiles = file.listFiles();
		
		if(listOfFiles != null)
			return Arrays.stream(listOfFiles).filter(x -> x.isFile()).collect(Collectors.toList());
		else 
			return null;
	}

}
