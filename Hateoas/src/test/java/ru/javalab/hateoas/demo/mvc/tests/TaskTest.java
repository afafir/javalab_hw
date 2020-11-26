package ru.javalab.hateoas.demo.mvc.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.javalab.hateoas.demo.model.Product;
import ru.javalab.hateoas.demo.model.Status;
import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Consumer;
import ru.javalab.hateoas.demo.model.user.Volunteer;
import ru.javalab.hateoas.demo.service.TaskService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class TaskTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        when(taskService.acceptTask(1L, 1L)).thenReturn(acceptedTask());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/task/1/accept/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(acceptedTask().getStatus().toString()))
                .andDo(document("accept_task", responseFields(
                        fieldWithPath("status").description("Статус задания"),
                        subsectionWithPath("consumer").description("Исполнитель"),
                        subsectionWithPath("productList").description("Список продуктов"),
                        subsectionWithPath("performer").description("Исполнитель"),
                        subsectionWithPath("id").description("Идентификатор"),
                        subsectionWithPath("_links.confirm").description("Подтвердить"),
                        subsectionWithPath("_links.addProduct").description("Добавить продукт")
                )));
    }

    private Task acceptedTask() {
        return Task.builder()
                .id(1L)
                .consumer(Consumer.builder()
                        .id(1L)
                        .name("name")
                        .disease("disease")
                        .build())
                .performer(Volunteer.builder()
                        .id(1L)
                        .rang("rang")
                        .name("name")
                        .build())
                .status(Status.ON_EXECUTION)
                .productList(List.of(
                        new Product(1L, "name"),
                        new Product(2L, "name2")
                ))
                .build();

    }

}
