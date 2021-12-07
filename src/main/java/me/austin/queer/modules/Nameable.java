package me.austin.queer.modules;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Default implementation of INameable
 * @param Name is the name of the module in question
 * @param Description is the description of the module
 * @author Austin
 */
public abstract class Nameable implements INameable {
	protected final String name, description;
	private final File file;
	protected Writer writer;
	protected Reader reader;

	public Nameable(String name, String description, File file) {
		this.name = name;
		this.description = description;
		this.file = file;
		try {
			this.writer = new FileWriter(file);
			this.reader = new FileReader(file);
		}
		catch (IOException e) {
			writer = FileWriter.nullWriter();
			reader = FileReader.nullReader();
		}
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description != null ? this.description : "";
	}

	public File getFile() {
		return this.file;
	}

	public Writer getWriter() {
		return this.writer;
	}
}
