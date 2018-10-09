package com.star.im.util;

public interface Serializer {
	byte JSON_SERIALIZE = 1;

	Serializer DEFAULT = new JsonSerializer();

	byte[] serialize(Object obj);

	<T> T deserialize(Class<T> clazz, byte[] bytes);
}
