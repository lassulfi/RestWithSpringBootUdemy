package br.com.lassulfi.converter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.lassulfi.converter.mocks.MockPerson;
import br.com.lassulfi.data.model.Person;
import br.com.lassulfi.data.vo.PersonVO;

public class DozerConverterTest {

	MockPerson inputObject;

	@Before
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);

		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("First Name Test 0", output.getFirstName());
		assertEquals("Last Name Test 0", output.getLastName());
		assertEquals("Address Test 0", output.getAddress());
		assertEquals("male", output.getGender());
	}

	@Test
	public void parseEntityListToVoListTest() {
		List<PersonVO> outputList = DozerConverter.parseListOfObjects(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("First Name Test 0", outputZero.getFirstName());
		assertEquals("Last Name Test 0", outputZero.getLastName());
		assertEquals("Address Test 0", outputZero.getAddress());
		assertEquals("male", outputZero.getGender());

		PersonVO outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getId());
		assertEquals("First Name Test 7", outputSeven.getFirstName());
		assertEquals("Last Name Test 7", outputSeven.getLastName());
		assertEquals("Address Test 7", outputSeven.getAddress());
		assertEquals("female", outputSeven.getGender());
		
		PersonVO outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getId());
		assertEquals("First Name Test 12", outputTwelve.getFirstName());
		assertEquals("Last Name Test 12", outputTwelve.getLastName());
		assertEquals("Address Test 12", outputTwelve.getAddress());
		assertEquals("male", outputTwelve.getGender());
	}

	@Test
	public void parseVOToEntityTest() {
		Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);

		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("First Name Test 0", output.getFirstName());
		assertEquals("Last Name Test 0", output.getLastName());
		assertEquals("Address Test 0", output.getAddress());
		assertEquals("male", output.getGender());
	}

	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = DozerConverter.parseListOfObjects(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("First Name Test 0", outputZero.getFirstName());
		assertEquals("Last Name Test 0", outputZero.getLastName());
		assertEquals("Address Test 0", outputZero.getAddress());
		assertEquals("male", outputZero.getGender());
		
		Person outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getId());
		assertEquals("First Name Test 7", outputSeven.getFirstName());
		assertEquals("Last Name Test 7", outputSeven.getLastName());
		assertEquals("Address Test 7", outputSeven.getAddress());
		assertEquals("female", outputSeven.getGender());
		
		Person outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getId());
		assertEquals("First Name Test 12", outputTwelve.getFirstName());
		assertEquals("Last Name Test 12", outputTwelve.getLastName());
		assertEquals("Address Test 12", outputTwelve.getAddress());
		assertEquals("male", outputTwelve.getGender());
	}
}
