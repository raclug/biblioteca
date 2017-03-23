package br.com.biblioteca.enums;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public enum NacionalidadeEnum {

	ESTADOS_UNIDOS("Estados Unidos"),
	RUSSIA("Rússia");
	
	private String pais;
	
	
	private NacionalidadeEnum(String pais) {
		this.pais = pais;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public static class NacionalidadeSerializer implements JsonSerializer<NacionalidadeEnum> {
        public JsonElement serialize(final NacionalidadeEnum nacionalidade, final Type type, final JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("id", new JsonPrimitive(nacionalidade.name()));
            result.add("pais", new JsonPrimitive(nacionalidade.getPais()));
            return result;
        }
    }
	
}
