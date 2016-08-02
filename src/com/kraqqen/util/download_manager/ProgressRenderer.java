package com.kraqqen.util.download_manager;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

class ProgressRenderer extends JProgressBar implements TableCellRenderer {

	private static final long serialVersionUID = 3663958874284762345L;

	public ProgressRenderer(int min, int max) {
		super(min, max);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setValue((int) ((Float) value).floatValue());
		return this;
	}
}