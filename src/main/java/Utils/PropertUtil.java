package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class PropertUtil {

	private static Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<String, String>();
	private static final String propertiesFile_Path = System.getProperty("user.dir") + 
			"\\src\\main\\resources\\frameworkProperties\\config.properties";
	
	
	private PropertUtil() {}

	static {
		System.out.println(propertiesFile_Path);
		try(FileInputStream file = new FileInputStream(propertiesFile_Path)) {
			property.load(file);
			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} 
	}

	public static String get(String key)  {
		return CONFIGMAP.get(key);
	}

}
