package pro.sky.telegrambot;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.controller.UserCatController;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TelegramBotApplicationTests {

    @Autowired
    private UserCatController userCatController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserCatRepository userCatRepository;
    @MockBean
    private UserDogRepository userDogRepository;


    @Test
    public void contextLoads() throws Exception {
        assertThat(userCatController).isNotNull();

    }


    @Test
    public void saveUserCatTest() throws Exception {
        final String name = "Simson";
        final long id = 1;

        JSONObject userCatObject = new JSONObject();
        userCatObject.put("name", name);

        UserCat userCat = new UserCat();
        userCat.setId(id);
        userCat.setUserName(name);
        when(userCatRepository.save(any(UserCat.class))).thenReturn(userCat);
        when(userCatRepository.findById(any(Long.class))).thenReturn(Optional.of(userCat));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cat-user")
                        .content(userCatObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userName").value(name))
                .andExpect(jsonPath("$.id").value(id));
    }


    @Test
    public void saveUserDogTest() throws Exception {
        final String name = "Simson";
        final long id = 1;

        JSONObject userDogObject = new JSONObject();
        userDogObject.put("name", name);

        UserDog userDog = new UserDog();
        userDog.setId(id);
        userDog.setUserName(name);
        when(userDogRepository.save(any(UserDog.class))).thenReturn(userDog);
        when(userDogRepository.findById(any(Long.class))).thenReturn(Optional.of(userDog));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dog-user")
                        .content(userDogObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userName").value(name))
                .andExpect(jsonPath("$.id").value(id));
    }

}
