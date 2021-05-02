package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import Contactos.ContactManager;

class ContactosTestTest2 {

private ContactManager contactManager;
	
	@BeforeAll
	public static void setupAll() {
		System.out.println("Should Print Before All Tests");
	}
	
	@BeforeEach
	public void setup() {
		System.out.println("Instantiating Contact Manager");
		contactManager = new ContactManager();
	}
	
	@Test
	@DisplayName("Deve criar um Contact")
	public void shouldCreateContact() {
		contactManager.addContact("John", "Doe", "0123456789");
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1, contactManager.getAllContacts().size());
	}
	
	@Test
	@DisplayName("Não deve criar um contacto quando o primeiro nome é nulo")
	public void shouldThrowRunTimeExceptionWhenFirstNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact(null, "Doe", "0123456789");
		});
	}
	
	@Test
	@DisplayName("Não deve criar um contacto quando o ultimo nome é nulo")
	public void shouldThrowRunTimeExceptionWhenLastNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("John", null, "0123456789");
		});
	}
	
	@Test
	@DisplayName("Não deve criar um contacto quando o numero é nulo")
	public void shouldThrowRunTimeExceptionWhenNumberIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			contactManager.addContact("John", "Doe", null);
		});
	}
	
	@Test
	@DisplayName("Num de tele deve comecar com 0")
	public void shouldTestPhoneNumberFormat() {
		contactManager.addContact("John", "Doe", "0123456789");
		assertEquals(1, contactManager.getAllContacts().size());
	}
	
	@Nested
	class RepeatedTests{
		@DisplayName("Repetir o teste de criação 5 vezes")
		@RepeatedTest(value=5)
		public void shouldTestContactCreationRepeatedly() {
			contactManager.addContact("John", "Doe", "0123456789");
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}
	}
	
	@Nested
	class ParameterizedTests{
		@DisplayName("Numero deve corresponder ao formato exigido")
		@ParameterizedTest
		@ValueSource(strings = {"0123456789","0123456798","0123456897"})
		public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber) {
			contactManager.addContact("John", "Doe", phoneNumber);
			assertFalse(contactManager.getAllContacts().isEmpty());
			assertEquals(1, contactManager.getAllContacts().size());
		}
	}
	
	@DisplayName("Metodo Source Case - Numero de telemóvel deve ter o formato requerido")
	@ParameterizedTest
	@MethodSource("phoneNumberList")
	public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
		contactManager.addContact("John", "Doe", phoneNumber);
		assertFalse(contactManager.getAllContacts().isEmpty());
		assertEquals(1,contactManager.getAllContacts().size());
	}
	
	private static List<String> phoneNumberList(){
		return Arrays.asList("0123456789","0123456798","0123456897");
	}
	
	@Test
	@DisplayName("Teste deve ser desativado")
	@Disabled
	public void shouldBeDisable() {
		throw new RuntimeException("Teste não deve ser executado");
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("Deve executar depois de cada teste");
	}
	
	@AfterAll
	public static void tearDownAll() {
		System.out.println("Deve ser executado até ao fim do Teste");
	}
	

}
