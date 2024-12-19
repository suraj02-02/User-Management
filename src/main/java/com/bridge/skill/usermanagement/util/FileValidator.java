package com.bridge.skill.usermanagement.util;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class FileValidator {

    private final Tika tika;

    @Value("${file.upload.max.image.size}")
    private long maxImageSize;

    @Value("${file.upload.max.pdf.size}")
    private long maxPdfSize;

    /**
     * Detects the MIME type of file from MultipartFile.
     *
     * @param file The uploaded file (MultipartFile).
     * @return The detected MIME type.
     * @throws IOException If an I/O error occurs.
     */
    public String detectMimeType(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Metadata metadata = new Metadata();
            return tika.detect(inputStream, metadata);
        }
    }

    /**
     * Checks if the file is an image (JPEG, PNG).
     *
     * @param mimeType The detected MIME type of the file.
     * @return True if the file is an image (JPEG or PNG); otherwise, false.
     */
    public boolean isImage(String mimeType) {
        return MimeTypeUtils.IMAGE_JPEG.toString().equals(mimeType) || MimeTypeUtils.IMAGE_PNG.toString().equals(mimeType);
    }

    /**
     * Checks if the file is a valid PDF.
     *
     * @param mimeType The detected MIME type of the file.
     * @return True if the file is a valid PDF; otherwise, false.
     */
    public boolean isPdf(String mimeType) {
        return "application/pdf".equals(mimeType);
    }

    public boolean isValidImage(MultipartFile file) throws IOException {
       String mimeType =  detectMimeType(file);
       return isImage(mimeType) && file.getSize() <= maxImageSize;
    }

    public boolean isValidPdf(MultipartFile file) throws IOException {
        String mimeType =  detectMimeType(file);
        return isPdf(mimeType) && file.getSize() <= maxPdfSize;
    }


}
