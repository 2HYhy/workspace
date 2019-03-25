package com.me.hyh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudZuulApplicationTests {

	@Test
	public void contextLoads() {

		stubFor(get(urlEqualTo("/get"))
				.willReturn(aResponse()
						.withBody("{\"headers\":{\"Hello\":\"World\"}}")
						.withHeader("Content-Type", "application/json")));
	}

}
