package com.flab.commerce.infra.s3;

import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final S3Client s3client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public List<String> uploadArticleImage(MultipartFile[] files)
        throws IOException {
        List<File> uploadFiles = convert(files).orElseThrow(
            () -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));

        return uploadFiles.stream().map(this::upload)
            .collect(Collectors.toList());
    }

    // s3에 파일 업로드
    private String upload(File uploadFile) {
        String fileName = uploadFile.getName();
        log.info(fileName);
        try {
            File resizeFile = resizer(uploadFile);
            String uploadImageUrl = putS3(resizeFile, fileName);
            removeNewFile(uploadFile);
            log.info("file upload success");
            return uploadImageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // s3로 업로드
//    private String putS3(File uploadFile, String fileName) {
//        s3client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(
//            CannedAccessControlList.PublicRead));
//        return s3client.getUrl(bucket, fileName).toString();
//    }

    private String putS3(File uploadFile, String fileName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .build();
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .build();
        return s3client.utilities().getUrl(getUrlRequest).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 파일 업로드
    private Optional<List<File>> convert(MultipartFile[] files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) { // 이미지 파일이 여러개 일시 반복문을 통해 fileList 담는다.
            if (FileNameUtil.validImageFileExtension(file.getOriginalFilename())) {
                File convertFile = new File(
                    System.getProperty("user.dir") + "/" + file.getOriginalFilename());
                if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 file 생성, 경로가 잘못되면 생성 불가
                    try (FileOutputStream fos = new FileOutputStream(
                        convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                        fos.write(file.getBytes());
                    } catch (Exception e) {
                        log.error("file local upload fail");
                        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                    }
                }
                fileList.add(convertFile);
            }
        }
        return Optional.of(fileList);
    }


    private File resizer(File input) throws IOException {
        // 매개변수 File input을 BufferedImage로 읽어 들인다.
        BufferedImage inputImage = ImageIO.read(input);
        try {
            // inputImage을 지정한 크기로 리사이징
            Image resizeImage = inputImage.getScaledInstance(1080, 1080, Image.SCALE_SMOOTH);
            // 리사이징된 이미지를 저장할 새로온 BufferedImage 객체를 생성(지정된 크기로 생성)
            BufferedImage output = new BufferedImage(1200, 627, BufferedImage.TYPE_INT_RGB);
            // 리사이징된 이미지를 생성한 BufferedImage 객체에 그린다(저장).
            // getGraphics(), createGraphics()의 차이
            // 두 메서드는 하는 일은 동일하나 반환타입의 차이로 createGraphics() 반환 타입 Graphics2D는 Graphics 클래스의 하위 클래스로, 더 많은 기능과 옵션을 제공
            output.createGraphics().drawImage(resizeImage, 0, 0, null);
            // 출력 파일을 File 객체로 생성 출력 객체의 이름은 원본 파일의 이름을 그대로 사용
            File outputFile = new File(input.getName());
            // 새로 생성된 BufferedImage 객체를 PNG 형식으로 출력 파일에 저장합니다.
            ImageIO.write(output, "png", outputFile);
            return outputFile;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            return null;
        }
    }
}
