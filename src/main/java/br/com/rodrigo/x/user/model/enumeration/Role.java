package br.com.rodrigo.x.user.model.enumeration;

public enum Role {

	ADMIN("ADMIN"),
	COMMON("COMMON");
	
	private final String value;
	
	private Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
