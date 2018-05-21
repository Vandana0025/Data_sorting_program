import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;


public class DemoJFileChooser extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton go;
	static JFrame jFrame = new JFrame();
	static JTextArea jTextArea = new JTextArea();

	JFileChooser chooser;
	String choosertitle = "Data Sorting Program";

	public DemoJFileChooser() {
		go = new JButton("Choose a folder to Sort");
		
		go.addActionListener(this);
		add(go);
	}

	public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// disable the "All files" option.
		chooser.setAcceptAllFileFilterUsed(false);
		//
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

			System.out.println( "Sorting --> " + chooser.getSelectedFile());

			String path = chooser.getSelectedFile() + "/";
			File file = new File(path);

			File[] files = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return true;
				}
			});
			for (File f : files) {
				// System.out.println(f.getName());
				String extension = "";
				int i = f.getName().lastIndexOf('.');
				if (i > 0) {
					extension = f.getName().substring(i + 1);

				}

				String newDir = path + extension.toUpperCase() + " Files";
				//excluding Folder name that have no extension
				if (!newDir.equals(path + " Files")) {
					File newFile = new File(newDir);
					
					if (!newFile.exists()) {
						newFile.mkdir();
					}
					//moving the files
					f.renameTo(new File(newFile + "\\" + f.getName()));
					f.delete();
				}

			}
		} else {
			System.out.println( "No Selection" );
		
		}
		System.out.println( "Completed" );
		msgbox("Sorting finished!");
		
	}
	private void msgbox(String s){
		   JOptionPane.showMessageDialog(null, s);
		}

	public Dimension getPreferredSize() {
		return new Dimension(400, 200);
	}

	public static void main(String s[]) {
		JFrame frame = new JFrame("Data Sorter");
		DemoJFileChooser panel = new DemoJFileChooser();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		});
		frame.getContentPane().add(panel, "Center");
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
		
					
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("                                           \u00a9"+"Vandana Manhas");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel.add(statusLabel);

		frame.setVisible(true);
	}
}