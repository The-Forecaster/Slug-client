package me.austin.queer.module;

/*
 * default implementation of IModule
 */
public abstract class Module implements IModule {
	String name;
	String description;
	Module instance;
	
	public Module(String name, String description) {
		this.name = name;
		this.description = description;
		this.instance = this;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
