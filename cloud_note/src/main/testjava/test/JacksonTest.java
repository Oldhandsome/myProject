package test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	@Test
	public void test() {
		String str = new String("[\"123456789\",\"987654321\",\"abcdefghi\"]");
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<String> array =  mapper.readValue(str, new TypeReference<List<String>>(){});
			System.out.println(array.get(2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
}
