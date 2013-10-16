package org.ibondi.emulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;


public class EmulatorGUI {
	
	public static void main(String[] args){
		new EmulatorGUI();
	}

	public EmulatorGUI() {
		final JFrame frame = new JFrame("Emulator GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openFile = new JButton("Open GPX file");
        openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "GPX files";
					}
					
					@Override
					public boolean accept(File file) {
						return file.isDirectory() || file.getName().endsWith(".gpx");
					}
				});
				int openDialog = jfc.showOpenDialog(frame);
				if(openDialog==JFileChooser.APPROVE_OPTION){
					
				}
			}
		});
        frame.getContentPane().add(openFile);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
