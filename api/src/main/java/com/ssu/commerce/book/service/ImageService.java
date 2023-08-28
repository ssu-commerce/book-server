package com.ssu.commerce.book.service;

import com.ssu.commerce.book.dto.param.RegisterImageParamDto;
import com.ssu.commerce.book.file.ImageStore;
import com.ssu.commerce.book.model.Image;
import com.ssu.commerce.book.persistence.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        imageRepository.saveAll(imageList);

        return imageList;
    }
}
