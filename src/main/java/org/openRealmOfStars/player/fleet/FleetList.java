package org.openRealmOfStars.player.fleet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.openRealmOfStars.starMap.Coordinate;

/**
 *
 * Open Realm of Stars game project
 * Copyright (C) 2016, 2017 Tuomo Untinen
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
 * Fleet for handling list of ships
 *
 */

public class FleetList {

  /**
   * Player's fleets
   */
  private ArrayList<Fleet> fleetList;

  /**
   * Current index
   */
  private int index;

  /**
   * Constructor for FleetList. Empty fleet list is created.
   */
  public FleetList() {
    fleetList = new ArrayList<>();
    index = -1;
  }

  /**
   * Read FleetList from DataInputStream
   * @param dis DataInputStream
   * @throws IOException if there is any problem with DataInputStream
   */
  public FleetList(final DataInputStream dis) throws IOException {
    int count = dis.readInt();
    fleetList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Fleet fleet = new Fleet(dis);
      fleetList.add(fleet);
    }
    index = 0;
  }

  /**
   * Save Fleet List to DataOutputStream
   * @param dos DataOutputStream
   * @throws IOException if there is any problem with DataOutputStream
   */
  public void saveFleetList(final DataOutputStream dos) throws IOException {
    dos.writeInt(fleetList.size());
    for (Fleet fleet: fleetList) {
      fleet.saveFleet(dos);
    }
  }

  /**
   * Add new fleet to list. Add generate unique name for the fleet.
   * @param fleet Fleet to add to the list
   */
  public void add(final Fleet fleet) {
    if (fleetList.size() == 0) {
      index = 0;
    }
    if (!isUniqueName(fleet.getName(), fleet)) {
      fleet.setName(generateUniqueName());
    }
    fleetList.add(fleet);
  }

  /**
   * How many fleet name with starting same name
   * @param name Fleet name
   * @return number of fleet containing same starting name
   */
  public int howManyFleetWithStartingNames(final String name) {
    int fleetNum = 0;
    for (Fleet ite : fleetList) {
      // Gets the next fleet number
      if (ite.getName().startsWith(name)) {
        fleetNum++;
      }
    }
    return fleetNum;
  }

  /**
   * Is certain name unique for player's fleet. Check is done
   * without case sensitive.
   * @param name Fleet name to check
   * @param toIgnore Fleet to ignore, if null then check each fleet
   * @return True if name is unique.
   */
  public boolean isUniqueName(final String name, final Fleet toIgnore) {
    boolean unique = true;
    for (Fleet ite : fleetList) {
      if (toIgnore == null && ite.getName().equalsIgnoreCase(name)) {
        unique = false;
        break;
      }
      if (toIgnore != null && toIgnore != ite
          && ite.getName().equalsIgnoreCase(name)) {
        unique = false;
        break;
      }
    }
    return unique;
  }

  /**
   * Generate unique name for fleet
   * @return new fleet name
   */
  public String generateUniqueName() {
    return generateUniqueName("Fleet");
  }

  /**
   * Generate unique name for fleet
   * @param startWith fleet name starts with this name
   *        and appends with " #" + number
   * @return new fleet name
   */
  public String generateUniqueName(final String startWith) {
    String resultUniqueName = null;
    for (int i = fleetList.size() + 1; i >= 0; i--) {
        resultUniqueName = startWith + " #" + i;
      if (isUniqueName(resultUniqueName, null)) {
        break;
      }
    }
    return resultUniqueName;
  }

  /**
   * Remove fleet from the list by index
   * @param indexToRemove to remove
   */
  public void remove(final int indexToRemove) {
    if (indexToRemove <= index) {
      index--;
      if (index < 0) {
        index = 0;
      }
    }
    fleetList.remove(indexToRemove);
  }

  /**
   * Get next fleet from the list
   * @return Fleet or null if list contains no fleets
   */
  public Fleet getNext() {
    Fleet nextIndex = null;
    if (fleetList.size() > 0) {
      index++;
      if (index >= fleetList.size()) {
        index = 0;
      }
      nextIndex = fleetList.get(index);
    }
    return nextIndex;
  }

  /**
   * Get first fleet
   * @return Fleet or null if no fleets
   */
  public Fleet getFirst() {
    Fleet firstIndex = null;
    if (fleetList.size() > 0) {
      index = 0;
      firstIndex = fleetList.get(index);
    }
    return firstIndex;
  }

  /**
   * Get previous fleet from the list
   * @return Fleet or null if list contains no fleets
   */
  public Fleet getPrev() {
    Fleet prevIndex = null;
    if (fleetList.size() > 0) {
      index--;
      if (index < 0) {
        index = fleetList.size() - 1;
      }
      prevIndex = fleetList.get(index);
    }
    return prevIndex;
  }

  /**
   * Get the number of fleets
   * @return number of fleets
   */
  public int getNumberOfFleets() {
    return fleetList.size();
  }

  /**
   * Get current fleet from the list
   * @return Fleet or null if list contains no fleets
   */
  public Fleet getCurrent() {
    Fleet currentIndex = null;
    if (fleetList.size() > 0) {
        currentIndex = fleetList.get(index);
    }
    return currentIndex;
  }

  /**
   * Get by index fleet from the list
   * @param fleetIndex Index
   * @return Fleet or null if list contains no fleets
   */
  public Fleet getByIndex(final int fleetIndex) {
    Fleet fleet = null;
    if (fleetList.size() > 0 && fleetIndex >= 0
        && fleetIndex < fleetList.size()) {
        fleet = fleetList.get(fleetIndex);
    }
    return fleet;
  }

  /**
   * Get by fleet name fleet from the list
   * @param name Fleet name
   * @return Fleet or null if list contains no fleets
   */
  public Fleet getByName(final String name) {
      Fleet getFleetByName = null;
    if (fleetList.size() > 0) {
      for (Fleet fleet : fleetList) {
        if (fleet.getName().equals(name)) {
            getFleetByName = fleet;
        }
      }
    }
    return getFleetByName;
  }

  /**
   * Get by fleet name fleet index number
   * @param name Fleet name
   * @return index number or -1 if not found
   */
  public int getIndexByName(final String name) {
      int fleetIndex = -1;
    if (fleetList.size() > 0) {
      for (int i = 0; i < fleetList.size(); i++) {
        Fleet fleet = fleetList.get(i);
        if (fleet.getName().equals(name)) {
            fleetIndex = i;
        }
      }
    }
    return fleetIndex;
  }

  /**
   * Get current index for fleet list
   * @return current index
   */
  public int getIndex() {
    return index;
  }

  /**
   * Remove fleet from the list. This should be called after each remove.
   */
  public void recalculateList() {
    for (int i = 0; i < fleetList.size(); i++) {
      Fleet fleet = fleetList.get(i);
      if (fleet.getNumberOfShip() == 0) {
        fleetList.remove(i);
        break;
      }
    }
  }

  /**
   * Remove Fleet from the list
   * @param toRemove Fleet to remove
   */
  public void removeFleet(final Fleet toRemove) {
    for (int i = 0; i < fleetList.size(); i++) {
      Fleet fleet = fleetList.get(i);
      if (fleet.getName().equals(toRemove.getName())) {
        fleetList.remove(i);
        break;
      }
    }
  }

  /**
   * Get fleet by coordinate
   * @param coordinate Which is being matched
   * @return Fleet or null if no match
   */
  public Fleet getFleetByCoordinate(final Coordinate coordinate) {
    for (Fleet fleet : fleetList) {
      if (fleet.getCoordinate().sameAs(coordinate)) {
        return fleet;
      }
    }
    return null;
  }

  /**
   * Method to check if certain fleet is in the list
   * @param search Fleet to search
   * @return True if fleet is found, otherwise false
   */
  public boolean isFleetOnList(final Fleet search) {
    for (Fleet fleet : fleetList) {
      if (fleet == search) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get total fleet capacity.
   * @return Get Total fleet capacity.
   */
  public double getTotalFleetCapacity() {
    double result = 0;
    for (Fleet fleet : fleetList) {
      result = result + fleet.getTotalFleetCapacity();
    }
    return result;
  }
}