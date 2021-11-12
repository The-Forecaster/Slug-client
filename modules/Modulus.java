package me.austin.queer.modules;

/**
 * Default implementation of IModulus
 * @param Name is the name of the module in question
 * @param Description is the description of the module
 * @author Austin
 */
public abstract class Modulus implements IModulus {
	private final String name, description;
	
	public Modulus(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description != null ? this.description : "";
	}
}
