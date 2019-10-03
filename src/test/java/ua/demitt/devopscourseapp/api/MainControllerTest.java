package ua.demitt.devopscourseapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ua.demitt.devopscourseapp.configuration.TestContext;
import ua.demitt.devopscourseapp.dto.HelloResponseDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestContext.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MainControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldGetHello() throws Exception {
        String name = "Dmytro";
        String responseAsStringExpected = mapper.writeValueAsString(new HelloResponseDto("Hello, Dmytro!"));

        performGetHello(name)
            .andExpect(status().isOk())
                .andExpect(content().string(responseAsStringExpected));
    }

    @Test
    public void shouldGetBadRequestForNullableName() throws Exception {
        String name = null;

        performGetHello(name)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetBadRequestForEmptyName() throws Exception {
        String name = "";

        performGetHello(name)
                .andExpect(status().isBadRequest());
    }

    private ResultActions performGetHello(String name) throws Exception {
        return mvc.perform(get("/api/v1/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", name))
                .andDo(MockMvcResultHandlers.print());
    }
}