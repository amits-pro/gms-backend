package com.bv.gms.dto;

import java.util.Objects;

public class NameValue {
	String name;
	String value;
	
	public NameValue(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	// Override equals() and hashCode() for correct comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NameValue nv = (NameValue) obj;
        //return name == nv.name && Objects.equals(value, nv.value);
        return name == nv.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
