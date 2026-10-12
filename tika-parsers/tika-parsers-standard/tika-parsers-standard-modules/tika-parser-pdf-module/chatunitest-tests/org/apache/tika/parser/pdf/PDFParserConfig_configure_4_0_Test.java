package org.apache.tika.parser.pdf;

import org.apache.tika.parser.pdf.PDF2XHTML;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.mockito.*;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.parser.pdf.image.ImageGraphicsEngineFactory;

class PDFParserConfig_configure_4_0_Test {

    @Test
    void testConfigure() {
        PDFParserConfig config = new PDFParserConfig();
        PDF2XHTML pdf2XHTML = Mockito.mock(PDF2XHTML.class);
        config.configure(pdf2XHTML);
        Mockito.verify(pdf2XHTML).setSortByPosition(config.isSortByPosition());
        Mockito.verify(pdf2XHTML).setWordSeparator(config.isEnableAutoSpace() ? " " : "");
        Mockito.verify(pdf2XHTML).setAverageCharTolerance(config.getAverageCharTolerance());
        Mockito.verify(pdf2XHTML).setSpacingTolerance(config.getSpacingTolerance());
        Mockito.verify(pdf2XHTML).setDropThreshold(config.getDropThreshold());
        Mockito.verify(pdf2XHTML).setSuppressDuplicateOverlappingText(config.isSuppressDuplicateOverlappingText());
        Mockito.verify(pdf2XHTML).setIgnoreContentStreamSpaceGlyphs(config.isIgnoreContentStreamSpaceGlyphs());
    }
}
