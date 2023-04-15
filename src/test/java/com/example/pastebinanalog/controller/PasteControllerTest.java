package com.example.pastebinanalog.controller;

import com.example.pastebinanalog.docker.DockerConfig;
import com.example.pastebinanalog.dto.PastebinCreateDTO;
import com.example.pastebinanalog.enums.ExpirationTime;
import com.example.pastebinanalog.enums.Status;
import com.example.pastebinanalog.model.Pastebin;
import com.example.pastebinanalog.repository.PastebinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PasteControllerTest extends DockerConfig {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PastebinRepository pastebinRepository;
    @Autowired
    ObjectMapper objectMapper;

    Pastebin pastebin;
    Status pasteStatus = Status.PUBLIC;
    ExpirationTime expirationTime = ExpirationTime.TEN_MIN;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() {
        pastebin = new Pastebin();
        pastebin.setLink(UUID.randomUUID().toString());
        pastebin.setTitle("Title");
        pastebin.setBody("Body");
        pastebin.setStatus(pasteStatus);
        pastebin.setCreationTime(Instant.now());
        pastebin.setExpiredTime(Instant.now().plus(expirationTime.getDuration()));
        pastebinRepository.save(pastebin);
    }

    @AfterEach
    void tearDown() {
        pastebinRepository.deleteAll();
    }

    @Test
    void createPaste() throws Exception {
        mockMvc.perform(post("/my-awesome-pastebin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PastebinCreateDTO.fromPaste(pastebin)))
                        .param("expirationTime", expirationTime.toString())
                        .param("pasteStatus", pasteStatus.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString());
    }

    @Test
    void findByLink() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin/" + pastebin.getLink())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PastebinCreateDTO.fromPaste(pastebin))))
                .andExpect(status().isOk());

    }

    @Test
    void findAllPublic() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin/last-ten"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void findByTitleOrBody() throws Exception {
        mockMvc.perform(get("/my-awesome-pastebin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
