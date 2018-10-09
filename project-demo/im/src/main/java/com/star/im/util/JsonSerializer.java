package com.star.im.util;

import com.alibaba.fastjson.JSON;

public class JsonSerializer implements Serializer {

	@Override
	public byte[] serialize(Object obj) {
		return JSON.toJSONBytes(obj);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, byte[] bytes) {
		return JSON.parseObject(bytes, clazz);
	}

}
