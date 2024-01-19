/**
 * <h1>RandomAccessFileInputStream.java</h1> <p> This program is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version; or,
 * at your choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p> <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License and the Mozilla Public License for more details. </p>
 * <p> You should have received a copy of the GNU General Public License and the Mozilla Public
 * License along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * © statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> ©
 * 2015 Easy Innova, SL </p>
 *
 * @author Víctor Muñoz Solà
 * @version 1.0
 * @since 30/6/2015
 */
package com.easyinnova.tiff.io;

import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import com.easyinnova.tiff.model.ByteOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The Class TiffFileInputStreamTest.
 */
public class TiffFileInputStreamTest {

  /**
   * Sets the up before class.
   *
   * @throws Exception the exception
   */
  @BeforeAll
  public static void setUpBeforeClass() throws Exception {

  }

  /**
   * Tear down after class.
   *
   * @throws Exception the exception
   */
  @AfterAll
  public static void tearDownAfterClass() throws Exception {

  }

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @BeforeEach
  public void setUp() throws Exception {

  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @AfterEach
  public void tearDown() throws Exception {

  }

  /**
   * Test.
   *
   * @throws IOException zxc
   */
  @Test
  public void ReadByte() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "ByteTest.hex"));

    assertEquals("127", stream.readByte().toString());
    assertEquals("128", stream.readByte().toString());
    assertEquals("0", stream.readByte().toString());
    assertEquals("255", stream.readByte().toString());
    stream.close();
  }

  /**
   * Read ascii.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void ReadAscii() throws IOException {
    TiffInputStream asci = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "asciiTest.hex"));
    assertEquals("A", asci.readAscii().toString());
    assertEquals("s", asci.readAscii().toString());
    assertEquals("c", asci.readAscii().toString());
    assertEquals("i", asci.readAscii().toString());
    assertEquals("i", asci.readAscii().toString());
    assertEquals("T", asci.readAscii().toString());
    assertEquals("e", asci.readAscii().toString());
    assertEquals("s", asci.readAscii().toString());
    assertEquals("t", asci.readAscii().toString());
    assertEquals("F", asci.readAscii().toString());
    assertEquals("i", asci.readAscii().toString());
    assertEquals("l", asci.readAscii().toString());
    assertEquals("e", asci.readAscii().toString());
    asci.close();
  }

  /**
   * Read s byte.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readSByte() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "ByteTest.hex"));
    assertEquals("127", stream.readSByte().toString());
    assertEquals("-128", stream.readSByte().toString());
    assertEquals("0", stream.readSByte().toString());
    assertEquals("-1", stream.readSByte().toString());
    stream.close();
  }

  /**
   * Read short.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readShort() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "ShortTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("32767", stream.readShort().toString());
    assertEquals("32768", stream.readShort().toString());
    assertEquals("0", stream.readShort().toString());
    assertEquals("65535", stream.readShort().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("65407", stream.readShort().toString());
    assertEquals("128", stream.readShort().toString());
    assertEquals("0", stream.readShort().toString());
    assertEquals("65535", stream.readShort().toString());
    stream.close();
  }

  /**
   * Read sshort.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readSShort() throws IOException {
    
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "ShortTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("32767", stream.readSShort().toString());
    assertEquals("-32768", stream.readSShort().toString());
    assertEquals("0", stream.readSShort().toString());
    assertEquals("-1", stream.readSShort().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("-129", stream.readSShort().toString());
    assertEquals("128", stream.readSShort().toString());
    assertEquals("0", stream.readSShort().toString());
    assertEquals("-1", stream.readSShort().toString());
    stream.close();
  }

  /**
   * Read long.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readLong() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "LongTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("2147483647", stream.readLong().toString());
    assertEquals("2147483648", stream.readLong().toString());
    assertEquals("0", stream.readLong().toString());
    assertEquals("4294967295", stream.readLong().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("4294967167", stream.readLong().toString());
    assertEquals("128", stream.readLong().toString());
    assertEquals("0", stream.readLong().toString());
    assertEquals("4294967295", stream.readLong().toString());
    stream.close();
  }

  /**
   * Read ss long.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readSSLong() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "LongTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("2147483647", stream.readSLong().toString());
    assertEquals("-2147483648", stream.readSLong().toString());
    assertEquals("0", stream.readSLong().toString());
    assertEquals("-1", stream.readSLong().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("-129", stream.readSLong().toString());
    assertEquals("128", stream.readSLong().toString());
    assertEquals("0", stream.readSLong().toString());
    assertEquals("-1", stream.readSLong().toString());
    stream.close();
  }

  /**
   * Read ss long.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readFloat() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "FloatTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("0.0", stream.readFloat().toString());
    assertEquals("-0.0", stream.readFloat().toString());
    assertEquals("Infinity", stream.readFloat().toString());
    assertEquals("-Infinity", stream.readFloat().toString());
    assertEquals("3.4028235E38", stream.readFloat().toString());
    assertEquals("-3.4028235E38", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("-1.1754942E-38", stream.readFloat().toString());
    assertEquals("1.1754942E-38", stream.readFloat().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("0.0", stream.readFloat().toString());
    assertEquals("1.8E-43", stream.readFloat().toString());
    assertEquals("4.6096E-41", stream.readFloat().toString());
    assertEquals("4.6275E-41", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());
    assertEquals("NaN", stream.readFloat().toString());

    stream.close();
  }

  /**
   * Read double.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readDouble() throws IOException {
    TiffInputStream stream = new TiffInputStream(new File("src" + separator + "test" + separator + "resources" + separator + "io" + separator + "DoubleTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("0.0", stream.readDouble().toString());
    assertEquals("-0.0", stream.readDouble().toString());
    assertEquals("Infinity", stream.readDouble().toString());
    assertEquals("-Infinity", stream.readDouble().toString());
    assertEquals("1.7976931348623157E308", stream.readDouble().toString());
    assertEquals("-1.7976931348623157E308", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("-2.225073858507201E-308", stream.readDouble().toString());
    assertEquals("2.225073858507201E-308", stream.readDouble().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("0.0", stream.readDouble().toString());
    assertEquals("6.3E-322", stream.readDouble().toString());
    assertEquals("3.0418E-319", stream.readDouble().toString());
    assertEquals("3.04814E-319", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    assertEquals("NaN", stream.readDouble().toString());
    stream.close();
  }

  /**
   * Read rational.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readRational() throws IOException {
    TiffInputStream stream =
        new TiffInputStream(new File("src" + separator + "test" + separator + "resources"
            + separator + "io" + separator + "RationalTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("2147483647/2147483647", stream.readRational().toString());
    assertEquals("0/0", stream.readRational().toString());
    assertEquals("2147483648/2147483648", stream.readRational().toString());
    assertEquals("0/2147483647", stream.readRational().toString());
    assertEquals("4294967295/4294967295", stream.readRational().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("4294967167/4294967167", stream.readRational().toString());
    assertEquals("0/0", stream.readRational().toString());
    assertEquals("128/128", stream.readRational().toString());
    assertEquals("0/4294967167", stream.readRational().toString());
    assertEquals("4294967295/4294967295", stream.readRational().toString());
    stream.close();
  }

  /**
   * Read Srational.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void readSRational() throws IOException {
    TiffInputStream stream =
        new TiffInputStream(new File("src" + separator + "test" + separator + "resources"
            + separator + "io" + separator + "RationalTest.hex"));
    stream.setByteOrder(ByteOrder.BIG_ENDIAN);
    assertEquals("2147483647/2147483647", stream.readSRational().toString());
    assertEquals("0/0", stream.readSRational().toString());
    assertEquals("-2147483648/-2147483648", stream.readSRational().toString());
    assertEquals("0/2147483647", stream.readSRational().toString());
    assertEquals("-1/-1", stream.readSRational().toString());
    stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
    stream.seekOffset(0);
    assertEquals("-129/-129", stream.readSRational().toString());
    assertEquals("0/0", stream.readSRational().toString());
    assertEquals("128/128", stream.readSRational().toString());
    assertEquals("0/-129", stream.readSRational().toString());
    assertEquals("-1/-1", stream.readSRational().toString());
    stream.close();
  }
}
