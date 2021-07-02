package me.austin.queer.event;

import me.austin.queer.util.Globals;
import me.zero.alpine.event.type.Cancellable;

public class Event extends Cancellable implements Globals {
	int priority;

	public Event() {
		this(0);
	}

	public Event(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
