package kz.aspansoftware.controller;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import kz.aspansoftware.records.SartFile;
import kz.aspansoftware.repository.FileRepository;
import net.coobird.thumbnailator.Thumbnailator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/v1/private/file")
@CrossOrigin
@ExecuteOn(BLOCKING)
public class FilePrivateController {

    @Value("${upload.imagePath}")
    String imagePath;

    @Value("${apiUrl}")
    String apiUrl;

    @Inject
    FileRepository fileRepository;
    private Logger log = LoggerFactory.getLogger(FilePrivateController.class);

    @Post
    @PermitAll
    @Consumes(value = MediaType.MULTIPART_FORM_DATA)
//    @Transactional
    public SartFile uploadImage(CompletedFileUpload file, Long container) {
        return handleImage(file, container);
    }

    //    @Connectable
    public SartFile handleImage(CompletedFileUpload file, Long container) {
        try {

            File tempFile = File.createTempFile("image-", file.getFilename());
            Path path = Paths.get(tempFile.getAbsolutePath());
            Files.write(path, file.getBytes());
            log.info("updload document {}", file.getFilename());
            var smallImage = new File(imagePath + "/thumbnail-" + tempFile.getName());
            Thumbnailator.createThumbnail(tempFile, smallImage, 250, 250);
            var thumb = fileRepository.create(container, "", smallImage.getName(), smallImage.getPath());
            return thumb;

        } catch (IOException e) {
            log.error("error", e);
            file.discard();
            throw new RuntimeException(e);
        }
    }

    @Delete("/{id}")
//    @Transactional
    public int delete(@PathVariable Long id) {
        return fileRepository.delete(id);
    }
}
