package tacos;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @RunWith : a JUnit annotation, providing a test runner that 
 * guides JUnit in running a test.
 */
@RunWith(SpringRunner.class)
/**
* @SpringBootTest : Tell JUnit to bootstrap the test with Spring 
* Boot capabilities
*/
@SpringBootTest
class TacoCloudApplicationTests {

	@Test
	void contextLoads() {
	}

}
