package com.ssu.commerce.book.file;

import com.ssu.commerce.book.model.Image;
import com.ssu.commerce.core.exception.SsuCommerceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageStore {

    @Value("${spring.image.path}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<Image> storeFiles(@NotNull final UUID bookId, @NotNull final List<MultipartFile> multipartFileList) throws IOException {
        if (CollectionUtils.isEmpty(multipartFileList)) {
            throw new SsuCommerceException(HttpStatus.BAD_REQUEST, "IMAGE_001", "FILE IS NULL");
        }
        List<Image> storeFileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            UUID imageId = UUID.randomUUID();
            multipartFile.transferTo(new File(getFullPath(imageId.toString())));
            storeFileList.add(
                    Image.builder()
                            .id(imageId)
                            .bookId(bookId)
                            .build()
            );
        }
        return storeFileList;
    }
}
