/**
 * <h1>SubIFD.java</h1>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version; or, at your choice, under the terms of the
 * Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License and the Mozilla Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License and the Mozilla Public License
 * along with this program. If not, see <a
 * href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a> and at <a
 * href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> .
 * </p>
 * <p>
 * NB: for the © statement, include Easy Innova SL or other company/Person contributing the code.
 * </p>
 * <p>
 * © 2015 Easy Innova, SL
 * </p>
 *
 * @author Víctor Muñoz Solà
 * @version 1.0
 * @since 2/6/2015
 *
 */
package com.easyinnova.tiff.model.types;

import com.easyinnova.tiff.model.IfdTags;
import com.easyinnova.tiff.model.ImageStrips;
import com.easyinnova.tiff.model.ImageTiles;
import com.easyinnova.tiff.model.Metadata;
import com.easyinnova.tiff.model.TagValue;
import com.easyinnova.tiff.model.TiffTags;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class IFD.
 */
public class IFD extends abstractTiffType {

  /** The tags. */
  private IfdTags tags;

  /** The next ifd. */
  private IFD nextIFD;

  /** Pointer to the parent ifd. */
  private IFD parentIFD;

  /** Pointer to the child ifd. */
  private IFD subIFD;

  /** The image strips. */
  private transient ImageStrips imageStrips;

  /** The image tiles. */
  private transient ImageTiles imageTiles;

  /** The is image. */
  private boolean isImage;

  /** The next ifd offset. */
  private int nextOffset;

  /** The ifd offset. */
  private int offset;

  /**
   * The Enum ImageRepresentation.
   */
  public enum ImageRepresentation {
    /**
     * The image is stored in strips.
     */
    STRIPS,
    /**
     * The image is stored in tiles.
     */
    TILES,
    /**
     * Undefined.
     */
    UNDEFINED
  }

  /**
   * Instantiates a new long.
   *
   * @param isImage the is image
   */
  public IFD(boolean isImage) {
    super();
    tags = new IfdTags();
    nextIFD = null;
    this.isImage = isImage;
  }

  /**
   * Gets the next offset.
   *
   * @return the next offset
   */
  public int getNextOffset() {
    return nextOffset;
  }

  /**
   * Sets the next offset.
   *
   * @param offset the new next offset
   */
  public void setNextOffset(int offset) {
    nextOffset = offset;
  }

  /**
   * Sets offset.
   *
   * @param offset the offset
   */
  public void setOffset(int offset) { this.offset = offset; }

  /**
   * Gets offset.
   *
   * @return the offset
   */
  public int getOffset() { return offset; }

  /**
   * Gets length.
   *
   * @return the length
   */
  public int getLength() {
    int length = 2 + getTags().getTags().size()*12 + 4;
    return length;
  }

  /**
   * Sets the parent.
   *
   * @param parent the new parent
   */
  public void setParent(IFD parent) {
    parentIFD = parent;
  }

  /**
   * Gets the tags.
   *
   * @return the tags
   */
  public IfdTags getTags() {
    return tags;
  }

  /**
   * Gets the parent.
   *
   * @return the parent
   */
  public IFD getParent() {
    return parentIFD;
  }

  /**
   * Gets the image size (x*y).
   *
   * @return the image size
   */
  public long getImageSize() {
    long x = 0, y = 0;
    if (tags.containsTagId(TiffTags.getTagId("ImageLength")))
      x = tags.get("ImageLength").getFirstNumericValue();
    if (tags.containsTagId(TiffTags.getTagId("ImageWidth")))
      y = tags.get("ImageWidth").getFirstNumericValue();
    return x * y;
  }

  /**
   * Sets the subIFD.
   *
   * @param subIFD the new subIFD
   */
  public void setsubIFD(IFD subIFD) {
    this.subIFD = subIFD;
  }

  /**
   * Gets the subIFD.
   *
   * @return the subIFD
   */
  public IFD getsubIFD() {
    return subIFD;
  }

  /**
   * Checks for parent.
   *
   * @return true, if successful
   */
  public boolean hasParent() {
    return parentIFD != null;
  }

  /**
   * Checks for sub IFDs.
   *
   * @return true, if successful
   */
  public boolean hasSubIFD() {
    return tags.containsTagId(330);
  }

  /**
   * Is thumbnail boolean.
   *
   * @return the boolean
   */
  public boolean isThumbnail() {
    if (tags.containsTagId(254)) {
      if (tags.get(254).getCardinality() > 0)
        return BigInteger.valueOf(tags.get(254).getFirstNumericValue()).testBit(0);
    }
    if (tags.containsTagId(255)) {
      if (tags.get(255).getCardinality() > 0)
        return tags.get(255).getFirstNumericValue() == 2;
    }
    if (hasSubIFD() && getImageSize() < getsubIFD().getImageSize()) {
      return true;
    }
    if (hasParent() && getImageSize() < getParent().getImageSize()) {
      return true;
    }
    return false;
  }

  /**
   * Gets sub ifds.
   *
   * @return the subifd list.
   */
  public List<IFD> getSubIFD() {
    List<IFD> l = new ArrayList<IFD>();
    if (hasSubIFD()) {
      for (abstractTiffType o : tags.get(330).getValue()) {
        l.add((IFD) o);
      }
    }
    return l;
  }

  /**
   * Create IFD metadata.
   */
  public Metadata createMetadata() {
    Metadata metadata = new Metadata();
    for (TagValue tag : getMetadata().getTags()) {
      if (tag.getCardinality() == 1) {
        abstractTiffType t = tag.getValue().get(0);
        if (t.isIFD()) {
          Metadata metadata2 = ((IFD) t).createMetadata();
          for (String key : metadata2.keySet()) {
            metadata.add(key, metadata2.get(key));
          }
        } else if (t.containsMetadata()) {
          try {
            Metadata meta = t.createMetadata();
            metadata.addMetadata(meta);
          } catch (Exception ex) {
            // TODO: What?
          }
        }
      }
    }
    return metadata;
  }

  /**
   * Sets the next ifd.
   *
   * @param ifd the new next ifd
   */
  public void setNextIFD(IFD ifd) {
    nextIFD = ifd;
  }

  /**
   * Checks for next ifd.
   *
   * @return true, if next IFD exists
   */
  public boolean hasNextIFD() {
    return nextIFD != null;
  }

  /**
   * Gets the metadata.
   *
   * @param name the name
   * @return the metadata
   */
  public TagValue getTag(String name) {
    TagValue tv = null;
    int id = TiffTags.getTagId(name);
    if (tags.containsTagId(id))
      tv = tags.get(id);
    return tv;
  }

  /**
   * Adds the metadata.
   *
   * @param tv the tv
   */
  public void addTag(TagValue tv) {
    tags.addTag(tv);
  }

  /**
   * Gets the metadata.
   *
   * @return the metadata
   */
  public IfdTags getMetadata() {
    return tags;
  }

  /**
   * Contains tag id.
   *
   * @param tagid the tagid
   * @return true, if successful
   */
  public boolean containsTagId(int tagid) {
    return tags.containsTagId(tagid);
  }

  /**
   * Prints the tags.
   */
  public void printTags() {
    for (TagValue ie : tags.getTags()) {
      try {
        String name = "" + ie.getId();
        if (TiffTags.hasTag(ie.getId()))
          name = TiffTags.getTag(ie.getId()).getName();
        String val = ie.toString();
        String type = TiffTags.tagTypes.get(ie.getType());
        System.out.println(name + "(" + ie.getType() + "->" + type + "): " + val);
      } catch (Exception ex) {
        System.out.println("Tag error");
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    String s = "";
    if (this.hasParent())
      s = "SubIFD";
    else if (isImage)
      s = "IFD";
    else
      s = tags.toString();
    return s;
  }

  /**
   * Gets the next ifd.
   *
   * @return the next ifd
   */
  public IFD getNextIFD() {
    return nextIFD;
  }

  /**
   * Gets the image in strips.
   *
   * @return the image strips object
   */
  public ImageStrips getImageStrips() {
    return imageStrips;
  }

  /**
   * Gets the image tiles.
   *
   * @return the image tiles
   */
  public ImageTiles getImageTiles() {
    return imageTiles;
  }

  /**
   * Sets the image strips.
   *
   * @param strips the new image strips
   */
  public void setImageStrips(ImageStrips strips) {
    this.imageStrips = strips;
  }

  /**
   * Sets the image tiles.
   *
   * @param imageTiles the new image tiles
   */
  public void setImageTiles(ImageTiles imageTiles) {
    this.imageTiles = imageTiles;
  }

  /**
   * Sets the checks if is image.
   *
   * @param b the new checks if is image
   */
  public void setIsImage(boolean b) {
    isImage = b;
  }

  /**
   * Sets the checks if is ifd.
   *
   * @param b the new checks if is ifd
   */
  public void setIsIFD(boolean b) {
    super.setIsIFD(b);
  }

  /**
   * Checks if is image.
   *
   * @return true, if is image
   */
  public boolean isImage() {
    return isImage;
  }

  /**
   * Checks for strips.
   *
   * @return true, if successful
   */
  public boolean hasStrips() {
    return imageStrips != null;
  }

  /**
   * Checks for tiles.
   *
   * @return true, if successful
   */
  public boolean hasTiles() {
    return imageTiles != null;
  }

  /**
   * Removes the tag.
   *
   * @param tagName the tag name
   */
  public void removeTag(String tagName) {
    this.tags.removeTag(tagName);
    /*
    int id = TiffTags.getTagId("IPTC");
    if (this.tags.containsTagId(id)) {
      IPTC iptc = (IPTC) this.tags.get(id).getValue().get(0);
      iptc.removeTag("Copyright");
    }*/
  }

  /**
   * Adds a tag.
   *
   * @param tagName  the tag name
   * @param tagValue the tag value
   */
  public void addTag(String tagName, String tagValue) {
    this.tags.addTag(tagName, tagValue);
  }

  /**
   * Adds a tag.
   *
   * @param tagName  the tag name
   * @param tagValue the tag value
   */
  public void addTag(String tagName, int[] tagValue) {
    this.tags.addTag(tagName, tagValue);
  }
}

