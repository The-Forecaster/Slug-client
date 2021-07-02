package me.austin.queer.module;

import me.zero.alpine.listener.Listenable;

public interface IModule extends Listenable{
    String getName();
    String getDescription();
}
