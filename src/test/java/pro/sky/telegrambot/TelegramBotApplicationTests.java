package pro.sky.telegrambot;


import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.controller.OwnerController;
import pro.sky.telegrambot.controller.UserCatController;
import pro.sky.telegrambot.controller.UserDogController;
import pro.sky.telegrambot.model.Owner;
import pro.sky.telegrambot.model.UserCat;
import pro.sky.telegrambot.model.UserDog;
import pro.sky.telegrambot.repository.OwnerRepository;
import pro.sky.telegrambot.repository.UserCatRepository;
import pro.sky.telegrambot.repository.UserDogRepository;
import pro.sky.telegrambot.service.OwnerService;
import pro.sky.telegrambot.service.UserCatService;
import pro.sky.telegrambot.service.UserDogService;

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
    @MockBean
    private OwnerRepository ownerRepository;
    @SpyBean
    private UserCatService userCatService;
    @SpyBean
    private UserDogService userDogService;
    @SpyBean
    private OwnerService ownerService;


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

    @Test
    public void saveOwnerTest() throws Exception {
        final String name = "Simson";
        final long id = 1;

        JSONObject userOwnerObject = new JSONObject();
        userOwnerObject.put("name", name);

        Owner owner = new Owner();
        owner.setId(id);
        owner.setOwnerName(name);
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        when(ownerRepository.findById(any(Long.class))).thenReturn(Optional.of(owner));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/owner")
                        .content(userOwnerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.ownerName").value(name))
                .andExpect(jsonPath("$.id").value(id));
    }


        /*
        JSONObject userDogObject = new JSONObject();
        userCatObject.put("vasya", 1);

        UserCat userCat = new UserCat();
        userCat.setId(1l);
        userCat.setUserName("vasya");
        userCat.setChatId(1l);

        when(userDogRepository.save(any(UserDog.class))).thenReturn(userDog);
        when(userDogRepository.findById(any(Long.class))).thenReturn(Optional.of(userDog));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dog-user")
                        .content(userDogObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.username").value("userName"))
                .andExpect(jsonPath("$.chatId").value("chatId"));

    }



    @Test
    public void saveOwnerTests() throws Exception {
        JSONObject ownerObject = new JSONObject();
        ownerObject.put("vasya", 1);
        Owner owner = new Owner();
        owner.setId(1l);
        owner.setOwnerName("vasya");
        owner.setChatId(1l);

        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        when(ownerRepository.findById(any(Long.class))).thenReturn(Optional.of(owner));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/owner")
                        .content(ownerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("id"))
                .andExpect(jsonPath("$.username").value("ownerName"))
                .andExpect(jsonPath("$.chatId").value("chatId"));

    }


     */


}
