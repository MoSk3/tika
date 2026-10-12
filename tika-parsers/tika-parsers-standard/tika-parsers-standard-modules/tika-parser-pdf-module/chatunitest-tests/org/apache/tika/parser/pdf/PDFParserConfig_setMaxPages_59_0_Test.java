package org.apache.tika.parser.pdf;

import org.apache.tika.parser.pdf.PDFParserConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.parser.pdf.image.ImageGraphicsEngineFactory;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PDFParserConfig_setMaxPages_59_0_Test {

    @Test
    public void testSetMaxPages() {
        PDFParserConfig config = new PDFParserConfig();
        config.setMaxPages(-1);
        assertEquals(-1, config.getMaxPages());
        config.setMaxPages(1);
        assertEquals(1, config.getMaxPages());
        config.setMaxPages(10);
        assertEquals(10, config.getMaxPages());
    }
}
