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

package org.workcraft.plugins.shared;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.workcraft.Config;
import org.workcraft.gui.propertyeditor.PropertyDeclaration;
import org.workcraft.gui.propertyeditor.PropertyDescriptor;
import org.workcraft.gui.propertyeditor.SettingsPage;

public class CommonCommentSettings implements SettingsPage {
	private static LinkedList<PropertyDescriptor> properties;

	protected static double baseSize = 1.0;
	protected static double strokeWidth = 0.02;
	protected static Color textColor = Color.BLACK;
	protected static Color borderColor = Color.GRAY;
	protected static Color fillColor = new Color(255, 255, 200);

	public String getSection() {
		return "Common";
	}

	public String getName() {
		return "Comment";
	}

	public CommonCommentSettings() {
		properties = new LinkedList<PropertyDescriptor>();

		properties.add(new PropertyDeclaration<CommonCommentSettings, Double>(
				this, "Base size (cm)", Double.class) {
			protected void setter(CommonCommentSettings object, Double value) {
				CommonCommentSettings.setBaseSize(value);
			}
			protected Double getter(CommonCommentSettings object) {
				return CommonCommentSettings.getBaseSize();
			}
		});

		properties.add(new PropertyDeclaration<CommonCommentSettings, Double>(
				this, "Stroke width (cm)", Double.class) {
			protected void setter(CommonCommentSettings object, Double value) {
				CommonCommentSettings.setStrokeWidth(value);
			}
			protected Double getter(CommonCommentSettings object) {
				return CommonCommentSettings.getStrokeWidth();
			}
		});

		properties.add(new PropertyDeclaration<CommonCommentSettings, Color>(
				this, "Text color", Color.class) {
			protected void setter(CommonCommentSettings object, Color value) {
				CommonCommentSettings.setTextColor(value);
			}
			protected Color getter(CommonCommentSettings object) {
				return CommonCommentSettings.getTextColor();
			}
		});

		properties.add(new PropertyDeclaration<CommonCommentSettings, Color>(
				this, "Border color", Color.class) {
			protected void setter(CommonCommentSettings object, Color value) {
				CommonCommentSettings.setBorderColor(value);
			}
			protected Color getter(CommonCommentSettings object) {
				return CommonCommentSettings.getBorderColor();
			}
		});

		properties.add(new PropertyDeclaration<CommonCommentSettings, Color>(
				this, "Fill color", Color.class) {
			protected void setter(CommonCommentSettings object, Color value) {
				CommonCommentSettings.setFillColor(value);
			}
			protected Color getter(CommonCommentSettings object) {
				return CommonCommentSettings.getFillColor();
			}
		});
	}

	public List<PropertyDescriptor> getDescriptors() {
		return properties;
	}

	public void load(Config config) {
		baseSize = config.getDouble("CommonCommentSettings.baseSize", 1.0);
		strokeWidth = config.getDouble("CommonCommentSettings.strokeWidth", 0.02);
		textColor = config.getColor("CommonCommentSettings.textColor", Color.BLACK);
		borderColor = config.getColor("CommonCommentSettings.foregroundColor", Color.GRAY);
		fillColor = config.getColor("CommonCommentSettings.fillColor", new Color(255, 255, 200));
	}

	public void save(Config config) {
		config.setDouble("CommonCommentSettings.baseSize", baseSize);
		config.setDouble("CommonCommentSettings.strokeWidth", strokeWidth);
		config.setColor("CommonCommentSettings.textColor", textColor);
		config.setColor("CommonCommentSettings.borderColor", borderColor);
		config.setColor("CommonCommentSettings.fillColor", fillColor);
	}

	public static double getBaseSize() {
		return baseSize;
	}

	public static void setBaseSize(double value) {
		CommonCommentSettings.baseSize = value;
	}

	public static double getStrokeWidth() {
		return strokeWidth;
	}

	public static void setStrokeWidth(double value) {
		CommonCommentSettings.strokeWidth = value;
	}

	public static Color getTextColor() {
		return textColor;
	}

	public static void setTextColor(Color value) {
		CommonCommentSettings.textColor = value;
	}

	public static Color getBorderColor() {
		return borderColor;
	}

	public static void setBorderColor(Color value) {
		CommonCommentSettings.borderColor = value;
	}

	public static Color getFillColor() {
		return fillColor;
	}

	public static void setFillColor(Color value) {
		CommonCommentSettings.fillColor = value;
	}

}
