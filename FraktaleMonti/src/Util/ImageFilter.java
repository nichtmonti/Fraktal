package Util;

import java.io.File;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class ImageFilter extends FileFilter {

	@Override
	public boolean accept(File arg0) {
		if (arg0.isDirectory()) {
        return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "";
	}

	
	
}
