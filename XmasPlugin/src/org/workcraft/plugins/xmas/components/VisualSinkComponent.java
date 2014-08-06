/*
*
* Copyright 2008,2009 Newcastle University
*
* This file is part of Workcraft.
*
* Workcraft is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Workcraft is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Workcraft.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package org.workcraft.plugins.xmas.components;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

import org.workcraft.annotations.DisplayName;
import org.workcraft.annotations.Hotkey;
import org.workcraft.annotations.SVGIcon;
import org.workcraft.dom.visual.Positioning;

@DisplayName("Sink")
@Hotkey(KeyEvent.VK_O)
@SVGIcon("images/icons/svg/xmas-sink.svg")
public class VisualSinkComponent extends VisualXmasComponent {

	public VisualSinkComponent(SinkComponent component) {
		super(component);
		if (component.getChildren().isEmpty()) {
			this.addInput("", Positioning.TOP);
		}
	}

	@Override
	public Shape getShape() {
		Path2D shape = new Path2D.Double();

		shape.moveTo(0, -0.5 * size);
		shape.lineTo(0, +0.5 * size);

		shape.moveTo(-0.5 * size, 0);
		shape.lineTo(+0.5 * size, 0);

		shape.moveTo(-0.3 * size, +0.2 * size);
		shape.lineTo(+0.3 * size, +0.2 * size);

		shape.moveTo(-0.1 * size, +0.4 * size);
		shape.lineTo(+0.1 * size, +0.4 * size);

		return shape;
	}

}