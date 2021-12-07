package me.austin.queer.modules;
/**
 * Meant to make the client modular
 * Name is the name of the module in question
 * Description is the description of the module
 * @author Austin
 */

import java.io.File;

public abstract interface INameable {
    String getName();
    
    String getDescription();

    File getFile();
}
