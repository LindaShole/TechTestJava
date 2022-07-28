package za.co.anycompany.service;

import java.util.HashMap;
import java.util.Map;

public class ConfigServiceImpl {
    
    private static final Map<String, String> vatMap = new HashMap<String, String>() {{ 
        put("UK", "0.2"); 
        put("OTHER", "0.0"); 
    }};

    private ConfigServiceImpl() {

    }

    public static String getConfigurationItem(ConfigTypeEnum configType, String configKey) {
        if(configType == ConfigTypeEnum.Vat)
            return getVatConfig(configKey);

        return null;
    }

    private static String getVatConfig(String configKey) {
        String defaultVat = "0";

        if (configKey.equals("")) 
            configKey = "OTHER";

        String vat = vatMap.get(configKey);

        if (vat == null)
            return defaultVat;

        return vat;
    }
}
