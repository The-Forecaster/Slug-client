package me.austin.queer.event.server;

import me.austin.queer.event.Event;
import net.minecraft.network.Packet;

public abstract class PacketEvent extends Event {
    protected Packet<?> packet;

	public Packet<?> getPacket() {
		return this.packet;
	}

	public static class Receive extends PacketEvent {
		public static Receive INSTANCE = new Receive();

		public static Receive get(Packet<?> packet) {
			if (INSTANCE == null) {
				INSTANCE = new Receive();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new Receive();
			}
			INSTANCE.packet = packet;

			return INSTANCE;
		}
	}

	public static class Send extends PacketEvent {
		public static Send INSTANCE = new Send();

		public static Send get(Packet<?> packet) {
			if (INSTANCE == null) {
				INSTANCE = new Send();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new Send();
			}
			INSTANCE.packet = packet;

			return INSTANCE;
		}
	}

	public static class PostReceive extends PacketEvent {
		public static PostReceive INSTANCE = new PostReceive();

		public static PostReceive get(Packet<?> packet) {
			if (INSTANCE == null) {
				INSTANCE = new PostReceive();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new PostReceive();
			}
			INSTANCE.packet = packet;

			return INSTANCE;
		}
	}

	public static class PostSend extends PacketEvent {
		public static PostSend INSTANCE = new PostSend();

		public static PostSend get(Packet<?> packet) {
			if (INSTANCE == null) {
				INSTANCE = new PostSend();
			}
			else if (INSTANCE.isCancelled()) {
				INSTANCE = new PostSend();
			}
			INSTANCE.packet = packet;

			return INSTANCE;
		}
	}
}
