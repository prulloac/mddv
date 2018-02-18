package edu.usach.mddvaccesscontrol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MddvAccessControlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccessControlServerIntegrationTest {

	@Test
	public void whenLoadApplication_thenSuccess() {
	}

}
