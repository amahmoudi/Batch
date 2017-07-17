package com.uff.config.classes.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@SuppressWarnings("rawtypes")
public abstract class EnumCSVPropertyConverter extends AbstractCSVPropertyConverter<Enum> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
    private Map<String, Enum> mappings;
    @XmlTransient
    private Map<Enum, String> reverseMappings;


    public EnumCSVPropertyConverter() {
        this.mappings = getEnumsMap();
        Iterator<Map.Entry<String, Enum>> entries = this.mappings.entrySet().iterator();

        this.reverseMappings = new HashMap<Enum, String>();
        while (entries.hasNext()) {
            Map.Entry<String, Enum> next = entries.next();
            reverseMappings.put(next.getValue(), next.getKey());
        }
    }

    abstract public Map<String, Enum> getEnumsMap();

    public Enum convertFrom(String text) {
        return this.mappings.get(text);
    }

    public String convertTo(Enum object) {
        return this.reverseMappings.get(object);
    }
}
