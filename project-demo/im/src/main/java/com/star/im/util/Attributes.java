package com.star.im.util;

import io.netty.util.AttributeKey;

public interface Attributes {
	AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
