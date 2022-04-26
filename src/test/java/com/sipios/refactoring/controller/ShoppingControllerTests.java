package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.service.RoutingService;
import com.sipios.refactoring.service.dto.Body;
import com.sipios.refactoring.service.dto.Item;
import com.sipios.refactoring.service.dto.TypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class ShoppingControllerTests {


    private static MockMvc mockMvc;


    @BeforeEach
    public void prepare() {
        mockMvc = MockMvcBuilders
            .standaloneSetup()
            .setControllerAdvice()
            .build();
    }

    @Test
    void should_not_throw() throws Exception {

        Assertions.assertDoesNotThrow(
            () -> mockMvc
                .perform(
                    post("/shopping").content(new Body(new Item[] {}, TypeEnum.STANDARD_CUSTOMER).toString())
                )
        );
    }
}
