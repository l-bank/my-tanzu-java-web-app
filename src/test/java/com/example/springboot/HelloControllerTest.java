package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @InjectMocks
    @Autowired
    private HelloController controller;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VetRepository vetRepositoryMock;

    @Test
    void index() throws Exception {
        Vet vet = new Vet();
        vet.setFirstName("Foo");
        vet.setLastName("Bar");
        when(vetRepositoryMock.findById(1005L)).thenReturn(Optional.of(vet));

        String expectedGreeting = "Greetings from " + vet.getFirstName() + " " + vet.getLastName();
        assertEquals(expectedGreeting, controller.index());

        mockMvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedGreeting));
    }
}
