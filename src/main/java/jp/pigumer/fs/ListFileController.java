package jp.pigumer.fs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@EnableConfigurationProperties(FileSystemProperties.class)
@EnableAutoConfiguration
public class ListFileController {

    @Autowired
    FileSystemProperties properties;

    @RequestMapping("/list")
    public List<FileName> list() {
        File dir = properties.getDir();

        return Arrays.asList(dir.list((d, name) -> name.endsWith(".mp4") || name.endsWith(".m4v")
        )).stream().map(filename -> {
            FileName fn = new FileName(filename);
            fn.add(linkTo(methodOn(ReadFileController.class).readFile(filename)).withRel("download"));
            return fn;
        }).collect(Collectors.toList());
    }
}
