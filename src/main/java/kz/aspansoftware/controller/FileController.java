package kz.aspansoftware.controller;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/upload")
public class FileController {

    @Value("${upload.imagePath}")
    String imagePath;

    @Value("${apiUrl}")
    String apiUrl;

//    @Inject
//    FileRepository fileRepository;
//    private Logger log = LoggerFactory.getLogger(FileController.class);
//
//
//    @Get("/image/{containerId}")
//    @Transactional
//    public List<SantecFile> findByContainer(Long containerId) {
//        return fileRepository.findByContainer(containerId);
//    }
//
//    @Get("/image/id/{id}")
//    @Transactional
//    public SantecFile findById(Long id) {
//        return fileRepository.findById(id);
//    }
//
//    @Get("/file/container/{containerId}/container-class/{containerClass}")
//    @Transactional
//    public List<SantecFile> findByContainer(Long containerId, String containerClass) {
//        return fileRepository.findByContainerAndClass(containerId, containerClass);
//    }
//
//
//    private SantecFile handleDocument(CompletedFileUpload file, Long container) {
//        try {
//            var newFile = new File((documentPath + "/" + file.getFilename()));
//            if (newFile.exists()) {
//                newFile.delete();
//            }
//            Path path = Files.createFile(Path.of(documentPath + "/" + file.getFilename()));
//            Files.write(path, file.getBytes());
//            log.info("updload document {}", file.getFilename());
//            return fileRepository.create(container, DOCUMENT, file.getFilename(), path.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//

}
