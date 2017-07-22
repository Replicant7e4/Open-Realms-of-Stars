package org.openRealmOfStars.starMap.newsCorp;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2017  Tuomo Untinen
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, see http://www.gnu.org/licenses/
*
*
* Image instruction class.
*
*/
public class ImageInstruction {
  /**
   * Instructions for background
   */
  public static final String BACKGROUND = "background";
  /**
   * Instructions for texts
   */
  public static final String TEXT = "text";
  /**
   * Instructions for planet
   */
  public static final String PLANET = "planet";
  /**
   * Instructions for Relation symbol between two text
   */
  public static final String RELATION_SYMBOL = "relation_symbol";
  /**
   * Instructions for ship
   */
  public static final String SHIP = "ship";

  /**
   * String builder used to collect all the instructions
   */
  private StringBuilder sb;
  /**
   * Constructor for Image instruction
   */
  public ImageInstruction() {
    sb = new StringBuilder();
  }

  /**
   * Character for starting parameters
   */
  private static final String PARAM_START = "(";

  /**
   * Character for ending parameters
   */
  private static final String PARAM_END = ")";

  /**
   * Instruction delimeter
   */
  private static final String INSTRUCTION_DELIM = "+";

  /**
   * Check if there is instruction delimiter if it is missing
   * this will add it there too.
   */
  private void checkDelim() {
    if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '+') {
      sb.append(INSTRUCTION_DELIM);
    }
  }
  /**
   * Add background to image instructions
   * @param backgroundName Background name
   * @return ImageInstruction with background
   */
  public ImageInstruction addBackground(final String backgroundName) {
    checkDelim();
    sb.append(BACKGROUND);
    sb.append(PARAM_START);
    sb.append(backgroundName);
    sb.append(PARAM_END);
    return this;
  }

  /**
   * Build actual instructions
   * @return instruction string
   */
  public String build() {
    return toString();
  }

  @Override
  public String toString() {
    return sb.toString();
  }
}
