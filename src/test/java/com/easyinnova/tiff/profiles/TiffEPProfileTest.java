/**
 * <h1>TiffEPProfileTest.java</h1> <p> This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version; or, at your
 * choice, under the terms of the Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+. </p>
 * <p> This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License and the Mozilla Public License for more details. </p> <p> You should
 * have received a copy of the GNU General Public License and the Mozilla Public License along with
 * this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a>
 * and at <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> . </p> <p> NB: for the
 * © statement, include Easy Innova SL or other company/Person contributing the code. </p> <p> ©
 * 2015 Easy Innova, SL </p>
 *
 * @author Víctor Muñoz Solà
 * @version 1.0
 * @since 18/6/2015
 */
package com.easyinnova.tiff.profiles;

import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.easyinnova.tiff.model.TagValue;
import com.easyinnova.tiff.model.TiffDocument;
import com.easyinnova.tiff.model.types.IFD;
import com.easyinnova.tiff.reader.TiffReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The Class TiffEPProfileTest.
 */
public class TiffEPProfileTest {

  /**
   * The tr.
   */
  TiffReader tr;

  /**
   * The to.
   */
  TiffDocument to;

  /**
   * The tv.
   */
  TagValue tv;

  /**
   * The ifd.
   */
  IFD ifd;

  /**
   * The result.
   */
  int result;

  /**
   * Pre test.
   */
  @BeforeEach
  public void PreTest() {
    boolean ok = true;
    try {
      tr = new TiffReader();
    } catch (Exception e) {
      ok = false;
    }
    assertEquals(ok, true);
  }

  /**
   * Test.
   */
  @Test
  public void validTest() {
    TiffEPProfile bp;

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "IMG_0887_EP.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(true, bp.getValidation().correct);
  }

  /**
   * Test.
   */
  @Test
  public void invalidTests() {
    TiffEPProfile bp;

    result = tr.readFile("src" + separator + "test" + separator + "resources" + separator + "Small" + separator + "Grey_stripped.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);
    assertEquals(13, bp.getValidation().errors.size());
    assertEquals(0, bp.getValidation().warnings.size());

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample-EP.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample-EP-jpeg.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample-EP-jpeg-thumb.tif");
    assertEquals(0, result);
    assertEquals(false, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample-EP-thumb.tif");
    assertEquals(0, result);
    assertEquals(false, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "DSC_1501_EP.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "DSC_1502_EP.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "IMG_0887.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);

    result =
        tr.readFile("src" + separator + "test" + separator + "resources" + separator
            + "TIFF_EP Samples" + separator + "tiffep-sample.dng.tif");
    assertEquals(0, result);
    assertEquals(true, tr.getBaselineValidation().correct);
    to = tr.getModel();
    bp = new TiffEPProfile(to);
    bp.validate();
    assertEquals(false, bp.getValidation().correct);
  }
}
