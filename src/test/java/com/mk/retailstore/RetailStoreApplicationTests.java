package com.mk.retailstore;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetailStoreApplication.class)
public class RetailStoreApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
	}

}
