package jp.pigumer.fs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@EnableConfigurationProperties(FileSystemProperties.class)
@EnableAutoConfiguration
public class ReadFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadFileController.class);

    @Autowired
    FileSystemProperties properties;

    @RequestMapping("/fs/{filename:.+}")
    public ResponseEntity<Resource> readFile(@PathVariable String filename) {
        File file = new File(properties.getDir(), filename);
        LOGGER.info(file.getAbsolutePath());
        if (file.exists() && file.canRead()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("video", "mp4"));
            return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
