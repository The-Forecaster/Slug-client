package me.austin.queer.event;

import meteordevelopment.orbit.ICancellable;

public class Event implements ICancellable {
	private int priority;
	private boolean cancelled;

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

	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
