package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.file.ImageStore;
import com.ssu.commerce.book.model.Image;
import com.ssu.commerce.book.persistence.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

    @Spy
    @InjectMocks
    private ImageService imageService;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageStore imageStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void registerImage() {
        RegisterImageParamDto dto = RegisterImageParamDto.builder().bookId(UUID.randomUUID()).build();

        List<MultipartFile> fileList = Arrays.asList(
                new MockMultipartFile("file1", "file1.jpg", "image/jpeg", new byte[0]),
                new MockMultipartFile("file2", "file2.jpg", "image/jpeg", new byte[0])
        );

       List<Image> imageList = Arrays.asList(
               Image.builder().bookId(UUID.randomUUID()).build(),
               Image.builder().bookId(UUID.randomUUID()).build()
       );

        assertDoesNotThrow(() -> {
            when(imageStore.storeFiles(dto.getBookId(), fileList)).thenReturn(imageList);
            when(imageRepository.saveAll(imageList)).thenReturn(imageList);

            List<Image> result = imageService.registerImage(dto, fileList);

            assertEquals(imageList, result);
            verify(imageStore, times(1)).storeFiles(dto.getBookId(), fileList);
            verify(imageRepository, times(1)).saveAll(result);
        });

    }

    @Test
    public void downloadImage() {
        String id = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();
        Resource fakeImageResource = mock(UrlResource.class);

        assertDoesNotThrow(() -> {
            when(imageStore.getFullPath(id)).thenReturn(path);
            when(imageService.getImageFromPath(path)).thenReturn(fakeImageResource);

            Resource imageResource = imageService.downloadImage(id);
            assertEquals(fakeImageResource, imageResource);
        });
    }
}
