package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.file.ImageStore;
import com.ssu.commerce.book.model.Image;
import com.ssu.commerce.book.persistence.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ImageService {

    private final ImageStore imageStore;

    private final ImageRepository imageRepository;

    @Transactional
    public List<Image> registerImage(RegisterImageParamDto dto, List<MultipartFile> fileList) throws IOException {
        List<Image> imageList = imageStore.storeFiles(dto.getBookId(), fileList);

        return imageRepository.saveAll(imageList);
    }

    public Resource downloadImage(String imageId) throws IOException {
        String imagePath = imageStore.getFullPath(imageId);
        return getImageFromPath(imagePath);
    }

    public Resource getImageFromPath(String imagePath) throws IOException {
        Resource imageResource = new UrlResource("file:" + imagePath);
        InputStream inputStream = imageResource.getInputStream();
        return new InputStreamResource(inputStream);
    }
}
