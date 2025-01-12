package org.openRealmOfStars.game.States;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import org.openRealmOfStars.game.GameCommands;
import org.openRealmOfStars.gui.buttons.SpaceButton;
import org.openRealmOfStars.gui.infopanel.EmptyInfoPanel;
import org.openRealmOfStars.gui.infopanel.InfoPanel;
import org.openRealmOfStars.gui.labels.SpaceLabel;
import org.openRealmOfStars.gui.panels.BlackPanel;
import org.openRealmOfStars.gui.panels.RaceImagePanel;
import org.openRealmOfStars.player.PlayerInfo;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2018  Tuomo Untinen
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
* Realm view for Open Realm of Stars
*
*/
public class RealmView extends BlackPanel {

  /**
  *
  */
  private static final long serialVersionUID = 1L;

  /**
   * Realm view constructor
   * @param realm Realm which is being shown
   * @param listener ActionListener
   */
  public RealmView(final PlayerInfo realm, final ActionListener listener) {
    this.setLayout(new BorderLayout());
    InfoPanel base = new InfoPanel();
    base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
    base.setTitle(realm.getEmpireName());
    this.add(base, BorderLayout.CENTER);
    RaceImagePanel raceImage = new RaceImagePanel();
    raceImage.setRaceToShow(realm.getRace().getNameSingle());
    raceImage.setSize(220, 220);
    raceImage.setMaximumSize(new Dimension(220, 220));
    base.add(raceImage);
    EmptyInfoPanel panelX = new EmptyInfoPanel();
    panelX.setLayout(new GridLayout(1, 0));
    InfoPanel info = new InfoPanel();
    info.setTitle("Racial information");
    info.setLayout(new BorderLayout());
    SpaceLabel raceDescription = new SpaceLabel(
        realm.getRace().getFullDescription(false, false));
    info.add(raceDescription, BorderLayout.CENTER);
    panelX.add(info);
    info = new InfoPanel();
    info.setTitle("Government information");
    info.setLayout(new BorderLayout());
    SpaceLabel governmentDescription = new SpaceLabel(
        realm.getGovernment().getDescription(false));
    info.add(governmentDescription, BorderLayout.CENTER);
    panelX.add(info);
    base.add(panelX);
    // Bottom panel
    InfoPanel bottomPanel = new InfoPanel();
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.setTitle(null);
    SpaceButton btn = new SpaceButton("Back to star map",
        GameCommands.COMMAND_VIEW_STARMAP);
    btn.addActionListener(listener);
    bottomPanel.add(btn, BorderLayout.CENTER);
    // Add panels to base
    this.add(bottomPanel, BorderLayout.SOUTH);
  }
}
