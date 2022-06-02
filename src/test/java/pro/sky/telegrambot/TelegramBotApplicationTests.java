package pro.sky.telegrambot;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.controller.UserCatController;
import pro.sky.telegrambot.controller.UserDogController;
import pro.sky.telegrambot.model.UserCat;

import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;
import pro.sky.telegrambot.service.UserCatService;
import pro.sky.telegrambot.service.UserDogService;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TelegramBotApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserCatRepository userCatRepository;

	@MockBean
	private UserDogRepository userDogRepository;

	@SpyBean
	private UserCatService userCatService;

	@SpyBean
	private UserDogService userDogService;

	@InjectMocks
	private UserCatController userCatController;

	@InjectMocks
	private UserDogController userDogController;




	@Test
	public void saveUserCatTests() throws Exception  {
		JSONObject userCatObject = new JSONObject();
		userCatObject.put("vasya",1);

		UserCat userCat = new UserCat();
		userCat.setId(1l);
		userCat.setUserName("vasya");
		userCat.setChatId(1l);

		when(userCatRepository.save(any(UserCat.class))).thenReturn(userCat);
		when(userCatRepository.findById(any(Long.class))).thenReturn(Optional.of(userCat));

		mockMvc.perform(MockMvcRequestBuilders
				.post("/cat-user")
				.content(userCatObject.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("id"))
				.andExpect(jsonPath("$.username").value("username"))
				.andExpect(jsonPath("$.chatId").value("chatId"));

	}

	@Test
	public void saveUserDogTests() throws Exception  {
		JSONObject userDogObject = new JSONObject();
		userDogObject.put("vasya",1);

		UserDog userDog = new UserDog();
		userDog.setId(1l);
		userDog.setUserName("vasya");
		userDog.setChatId(1l);

		when(userDogRepository.save(any(UserDog.class))).thenReturn(userDog);
		when(userDogRepository.findById(any(Long.class))).thenReturn(Optional.of(userDog));

		mockMvc.perform(MockMvcRequestBuilders
						.post("/dog-user")
						.content(userDogObject.toString())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("id"))
				.andExpect(jsonPath("$.username").value("username"))
				.andExpect(jsonPath("$.chatId").value("chatId"));

	}




}
