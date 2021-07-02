package me.austin.queer.event;

import me.austin.queer.util.Globals;
import meteordevelopment.orbit.ICancellable;

public class Event implements ICancellable, Globals {
	int priority;
	boolean cancelled;

	public Event() {
		this(0);
	}

	public Event(int priority) {
		this.priority = priority;
		this.cancelled = false;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
