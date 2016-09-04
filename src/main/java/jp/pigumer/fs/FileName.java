package jp.pigumer.fs;

import org.springframework.hateoas.ResourceSupport;

public class FileName extends ResourceSupport {

    public final String filename;

    public FileName(String filename) {
        this.filename = filename;
    }
}
