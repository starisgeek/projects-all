package com.yunfenghui.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class BaseServiceTest extends AbstractJUnit4SpringContextTests {
}
