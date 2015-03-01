package com.twu.biblioteca;

import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Created by user 1 on 28/02/2015.
 */

public class TextFromStandardInputStreamJeremyEdit {
    private final TextFromStandardInputStreamJeremyEdit.SystemInMock systemInMock = new TextFromStandardInputStreamJeremyEdit.SystemInMock();
    private InputStream originalIn;

    public static TextFromStandardInputStreamJeremyEdit emptyStandardInputStream() {
        return new TextFromStandardInputStreamJeremyEdit("");
    }

    /** @deprecated */
    @Deprecated
    public TextFromStandardInputStreamJeremyEdit(String text) {
        this.provideText(text);
    }

    public void provideText(String text) {
        this.systemInMock.provideText(text);
    }

    public void before() throws Throwable {
        this.originalIn = System.in;
        System.setIn(this.systemInMock);
    }

    protected void after() {
        System.setIn(this.originalIn);
    }

    private static class SystemInMock extends InputStream {
        private StringReader reader;

        private SystemInMock() {
        }

        public void provideText(String text) {
            this.reader = new StringReader(text);
        }

        public int read() throws IOException {
            return this.reader.read();
        }
    }
}
