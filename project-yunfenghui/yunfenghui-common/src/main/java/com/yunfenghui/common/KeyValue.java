package com.yunfenghui.common;

import java.io.Serializable;

public class KeyValue<K, V> implements Serializable {
	private static final long serialVersionUID = 1L;
	private K key;
	private V value;

	private static final KeyValue<?, ?> EMPTY = new KeyValue<>();

	@SuppressWarnings("unchecked")
	public static <K, V> KeyValue<K, V> empty() {
		return (KeyValue<K, V>) EMPTY;
	}

	public KeyValue() {
		this(null, null);
	}

	public KeyValue(K key) {
		this(key, null);
	}

	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
