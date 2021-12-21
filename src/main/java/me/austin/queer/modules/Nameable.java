package me.austin.queer.modules;

import java.io.File;

/**
 * Default implementation of INameable
 * @param Name is the name of the module in question
 * @param Description is the description of the module
 * @author Austin
 */
public abstract class Nameable implements INameable {
	protected final String name, description;
	private final File file;

	public Nameable(String name, String description, File file) {
		this.name = name;
		this.description = description;
		this.file = file;
	}

	@Override
	public final String getName() {
		return this.name;
	}
	
	@Override
	public final String getDescription() {
		return this.description != null ? this.description : "";
	}

	@Override
	public final File getFile() {
		return this.file;
	}
}
