package me.austin.queer.event;

public abstract class Event {
	private int priority;
	private boolean cancelled;

	protected Event() {
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

	public void cancel() {
		this.cancelled = true;
	}
}
