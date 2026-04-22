package com.mborodin.javacourse;

import com.mborodin.javacourse.model.Cat;
import com.mborodin.javacourse.model.CatBehaviour;
import com.mborodin.javacourse.repository.CatRepository;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestJpaConfig.class, Main.class})
@EnableJpaRepositories(basePackages = "com.mborodin.javacourse")
@TestPropertySource(locations = "classpath:application.properties")
@WebAppConfiguration
public class CatControllerTest {

    // За допомогою анотації Inject (інколи Autowired) можна додавати (інжектити) потрібні біни в тест.
    // Це використання патерну інʼєкція залежностей (Dependency injection).
    @Inject
    private WebApplicationContext webApplicationContext;
    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private CatRepository catRepository;

    // Використовуватиметься для надсилання http запитів в програму
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    // Перевіряє чи контекст правильно ініціалізувався і чи присутні всі потрібні біни
    @Test
    public void getServletContext_contextHasCatController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertInstanceOf(MockServletContext.class, servletContext);
        // Перевіряємо чи в контексті є бін catController (обʼєкт класу catController)
        assertNotNull(webApplicationContext.getBean("catController"));
    }

    @Test
    public void getCats_returnsOneCat2() throws Exception {
        // Готуємо дані для тесту
        Cat cat = new Cat("Tom", CatBehaviour.NORMAL);
        catRepository.save(cat);

        // Виконуємо http запит, який тестуємо
        MvcResult result = mockMvc.perform(get("/cats")).andDo(print())
                                  .andExpect(status().isOk())
                                  .andExpect(content().contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                                  .andReturn();

        // Перевіряємо чи повернулась правильна відповідь
        List<Cat> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Cat.class));
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(cat, response.getFirst());

        // Очищуємо дані
        // В unit тестах очищувати нічого не потрібно, бо все що створено в тестовому класі
        // перестворюється для кожного тесту. В інтергаційних тестах все створюється один раз
        // на всі тести в класі, бо повна ініціалізація додатку і бази даних займає багато часу.
        // Перестворення всього для кожного тесту зробило б ці тести дуже повільними.
        // Через це все що створено в тесті потрібно видаляти в кінці тесту,
        // інакше ці дані будуть доступні в наступних тестах.
        catRepository.delete(cat);
    }

    @Test
    public void getCat_returnsCat() throws Exception {
        // Готуємо дані для тесту
        Cat cat = new Cat("Tom", CatBehaviour.NORMAL);
        catRepository.save(cat);

        // Виконуємо http запит, який тестуємо
        MvcResult result = mockMvc.perform(get("/cats/Tom")).andDo(print())
                                  .andExpect(status().isOk())
                                  .andExpect(content().contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                                  .andReturn();

        // Перевіряємо чи повернулась правильна відповідь
        Cat response = objectMapper.readValue(result.getResponse().getContentAsString(), Cat.class);
        assertNotNull(response);
        assertEquals(cat, response);

        // Очищуємо дані
        catRepository.delete(cat);
    }
}
