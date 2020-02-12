package fi.academy.dao;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ColorTest {
    @Test
    public void testHex2RgbWithProperHexReturnsAGoodRGBArray() {
        assertThat(Color.hex2rgb("#000000"), is(new int[]{0,0,0}));
        assertThat(Color.hex2rgb("#ffffff"), is(new int[]{255,255,255}));
        assertThat(Color.hex2rgb("#010203"), is(new int[]{1,2,3}));
    }
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testHex2RgbWithTooShortHexPatternThrowsColorException() throws ColorException {
        expectedException.expect(ColorException.class);
        Color.hex2rgb("#000");
        expectedException.expect(ColorException.class);
        Color.hex2rgb("#123456");
        expectedException.expect(ColorException.class);
        Color.hex2rgb("123456");
        expectedException.expect(ColorException.class);
        Color.hex2rgb("1234567");
    }
    @Test
    public void testHex2RgbWithTooLongHexPatternThrowsColorException() throws ColorException {
        expectedException.expect(ColorException.class);
        Color.hex2rgb("#1234567");
    }
    @Test
    public void testHex2RgbWithInvalidHexPatternThrowsColorException() throws ColorException {
        expectedException.expect(ColorException.class);
        Color.hex2rgb("123456");
    }
    @Test
    public void testHex2RgbWithIncorrectCharactersHexPatternThrowsColorException() throws ColorException {
        expectedException.expect(ColorException.class);
        Color.hex2rgb("#cafexx");
    }
    @Test
    public void testCreatingAColorWithMismatchingHexAndRgbThrowsAnException() throws ColorException {
        expectedException.expect(ColorException.class);
        Color c = new Color("doo", "#100108", new int[] {16, 1, 99});
    }
    @Test
    public void testCreatingAColorWithHexOnlyWillProduceAProperRgbAndCustomColor() {
        Color c = new Color("dude", "#118008");
        assertThat(c.getRgb(), is(new int[]{17,128,8}));
        assertThat(c.isCustom(), is(true));
    }
}