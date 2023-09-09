package com.ssu.commerce.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssu.commerce.book.dto.request.RegisterImageRequestDto;
import com.ssu.commerce.book.dto.response.RegisterImageResponseDto;
import com.ssu.commerce.book.model.Image;
import com.ssu.commerce.book.service.ImageService;
import com.ssu.commerce.book.supplier.ImageControllerTestDataSupplier;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
@WithMockUser(username = "tester", roles = "USER")
class ImageControllerTest implements ImageControllerTestDataSupplier {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Test
    void registerImage() {

        List<Image> imageList = ImageControllerTestDataSupplier.getImageList();

        MockMultipartFile multipartFile1 = new MockMultipartFile("fileList", "image1.jpg", "image/jpeg", "sample-image-data".getBytes());
        MockMultipartFile multipartFile2 = new MockMultipartFile("fileList", "image2.jpg", "image/jpeg", "sample-image-data".getBytes());

        assertDoesNotThrow(() -> {
            when(imageService.registerImage(any(), any())).thenReturn(imageList);

            MockMultipartFile jsonFile = new MockMultipartFile("registerImageRequestDto",
                    "registerImageRequestDto", "application/json",
                    new ObjectMapper().registerModule(new JavaTimeModule())
                            .writeValueAsString(ImageControllerTestDataSupplier.getRegisterImageRequestDto()).getBytes());

            mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/image")
                            .file(multipartFile1)
                            .file(multipartFile2)
                            .file(jsonFile)
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                            .with(csrf()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", equalTo(String.valueOf(imageList.get(0).getId().toString()))))
                    .andExpect(jsonPath("$[1].id", equalTo(String.valueOf(imageList.get(1).getId().toString()))));
        });
    }

    @Test
    void downloadRepresentData() throws Exception {
        UUID uuid = UUID.randomUUID();
        String mockData = "This is mock data for testing purposes.";

        when(imageService.downloadImage(String.valueOf(uuid)))
                .thenReturn(new InputStreamResource(new ByteArrayInputStream(mockData.getBytes())));

        mockMvc.perform(get("/v1/image/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(mockData));
    }
}