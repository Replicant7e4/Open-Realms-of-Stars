package org.openRealmOfStars.player.tech;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2017 Tuomo Untinen
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
* TechList test
*
*/
public class TechListTests {

  @Test
  @Category(org.openRealmOfStars.BehaviourTest.class)
  public void testTradeTechs() {
    Tech[] ownTechs = new Tech[5];
    Tech[] tradeTechs = new Tech[3];
    ownTechs[0] = new Tech("MilTest1", TechType.Combat, 1);
    ownTechs[1] = new Tech("MilTest2", TechType.Combat, 1);
    ownTechs[2] = new Tech("MilTest3", TechType.Combat, 1);
    ownTechs[3] = new Tech("MilTest4", TechType.Combat, 1);
    ownTechs[4] = new Tech("MilTest5", TechType.Combat, 1);
    tradeTechs[0] = new Tech("MilTest1", TechType.Combat, 1);
    tradeTechs[1] = new Tech("MilTest0", TechType.Combat, 1);
    tradeTechs[2] = new Tech("MilTest6", TechType.Combat, 1);
    Tech[] techs = TechList.getTechDifference(tradeTechs, ownTechs);
    assertEquals(2, techs.length);
    assertEquals("MilTest0", techs[0].getName());
    assertEquals("MilTest6", techs[1].getName());
  }

  @Test
  @Category(org.openRealmOfStars.UnitTest.class)
  public void testBasic() {
    TechList list = new TechList();
    assertEquals(20, list.getTechFocus(TechType.Combat));
    assertEquals(16, list.getTechFocus(TechType.Defense));
    assertEquals(16, list.getTechFocus(TechType.Electrics));
    assertEquals(16, list.getTechFocus(TechType.Hulls));
    assertEquals(16, list.getTechFocus(TechType.Improvements));
    assertEquals(16, list.getTechFocus(TechType.Propulsion));
    assertEquals(1, list.getTechLevel(TechType.Combat));
    assertEquals(1, list.getTechLevel(TechType.Defense));
    assertEquals(1, list.getTechLevel(TechType.Electrics));
    assertEquals(1, list.getTechLevel(TechType.Hulls));
    assertEquals(1, list.getTechLevel(TechType.Improvements));
    assertEquals(1, list.getTechLevel(TechType.Propulsion));
  }

}