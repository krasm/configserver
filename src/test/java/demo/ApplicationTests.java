package demo;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes=demo.ConfigServerApplication.class)
public class ApplicationTests {

	@Value("${local.server.port}")
	private int port = 0;

	@Autowired 
	private TestRestTemplate testTemplate;
	
	@Ignore
	@Test
	public void configurationAvailable() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testTemplate.getForEntity(
				"http://localhost:" + port + "/app/cloud", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Ignore
	@Test
	public void envPostAvailable() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testTemplate.postForEntity(
				"http://localhost:" + port + "/admin/env", form, Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
