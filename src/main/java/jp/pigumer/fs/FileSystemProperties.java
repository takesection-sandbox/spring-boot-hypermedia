package jp.pigumer.fs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "filesystem")
public class FileSystemProperties {

    private File dir = null;

    public void setDir(File dir) {
        this.dir = dir;
    }

    public File getDir() {
        return dir;
    }
}
