package me.austin.queer.module.setting;

import java.util.ArrayList;
import java.util.List;

import me.austin.queer.module.Module;

public class Setting<T> extends Module {
	private T defaultValue, currentValue, minValue, maxValue;
	private List<T> values;
	
	public Setting(String name, String description, Boolean defaultValue) {
		super(name, description);
		this.defaultValue = (T)defaultValue;
		this.currentValue = (T)defaultValue;
		this.values = new ArrayList<>();
		((List<Boolean>)(values)).add(true);
		((List<Boolean>)(values)).add(false);
	}

	public Setting(String name, String description, Enum defaultValue, Enum[] values) {
		super(name, description);
		this.defaultValue = (T)defaultValue;
		this.currentValue = (T)defaultValue;
		this.values = new ArrayList<>();
		for (T value : (T[])values) {
			this.values.add(value);
		}
	}

	public Setting(String name, String description, T defaultValue, T minValue, T maxValue) {
		super(name, description);
		this.defaultValue = defaultValue;
		this.currentValue = defaultValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public T getMaxValue() {
		return this.maxValue;
	}

	public T getMinValue() {
		return this.minValue;
	}
	
	public T getValue() {
		return this.currentValue;
	}

	public void setValue(T value) {
		this.currentValue = value;
	}

	public void cycle() {
		currentValue = (currentValue == values.get(-1) ? values.get(0) : values.get(values.indexOf(currentValue) + 1));
	}
}
