
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

/*
 *Steganography_Controller Class
 */
public class Main_Steganography
{
	//Program Variables
	private User_Interface view;
	private Steganography steglogic;
	private Steganography_2 steglogic_w_k;
	
	//Panel Displays
	private JPanel		decode_panel;
	private JPanel		encode_panel;
	private JPanel      encode_with_keys_panel;
	private JPanel      decode_with_keys_panel;
	//Panel Variables
	private JTextArea 	input;
	private JTextArea   input1;
	private JButton		encodeButton,decodeButton,encodeWithKeysButton,decodeWithKeysButton;
	private JLabel		image_input;
	private JLabel      image_input1;
	//Menu Variables
	private JMenuItem 	encode;
	private JMenuItem 	decode;
	private JMenuItem   encode_with_keys;
	private JMenuItem   decode_with_keys;
	private JMenuItem 	exit;
	
	//action event classes
	private Encode			enc;
	private Decode			dec;
	private Encode_With_Keys enc_w_k;
	private Decode_With_Keys dec_w_k;
	private EncodeButton	encButton;
	private EncodeWithKeysButton encwkButton;
	private DecodeButton	decButton;
	private DecodeWithKeysButton decwkButton;
	private String			stat_path = "";
	private String			stat_name = "";

	public Main_Steganography(User_Interface aView)
	{
		view  = aView;
		steglogic = new Steganography_1();
		steglogic_w_k =new Steganography_2();
		encode_panel	= view.getTextPanel();
		encode_with_keys_panel = view.getTextPanelWithKeys();
		decode_with_keys_panel = view.getImagePanelWithKeys();
		decode_panel	= view.getImagePanel();
		input			= view.getText();
		input1=view.getText1();
		image_input		= view.getImageInput();
		image_input1=view.getImageInput1();
		encodeButton	= view.getEButton();
		encodeWithKeysButton=view.getEWKButton();
		decodeButton	= view.getDButton();
		decodeWithKeysButton = view.getDWKButton();
		encode			= view.getEncode();
		encode_with_keys= view.getEncode_with_keys();
		decode			= view.getDecode();
		decode_with_keys=view.getDecode_with_keys();
		exit			= view.getExit();
		
		//assign action events
		enc = new Encode();
		encode.addActionListener(enc);
		enc_w_k=new Encode_With_Keys();
		encode_with_keys.addActionListener(enc_w_k);
		dec_w_k=new Decode_With_Keys();
		decode_with_keys.addActionListener(dec_w_k);
		dec = new Decode();
		decode.addActionListener(dec);
		exit.addActionListener(new Exit());
		encButton = new EncodeButton();
		encodeButton.addActionListener(encButton);
		encwkButton=new EncodeWithKeysButton();
		encodeWithKeysButton.addActionListener(encwkButton);
		decButton = new DecodeButton();
		decodeButton.addActionListener(decButton);
		decwkButton=new DecodeWithKeysButton();
		decodeWithKeysButton.addActionListener(decwkButton);

		encode_view();
	}
	

	private void encode_view()
	{
		update();
		view.setContentPane(encode_panel);
		view.setVisible(true);
	}

	private void decode_view()
	{
		update();
		view.setContentPane(decode_panel);
		view.setVisible(true);
	}

	private void decode_with_keys_view()
	{
		update();
		view.setContentPane(decode_with_keys_panel);
		view.setVisible(true);
	}

	private void encode_with_keys_view()
	{
		update();
		view.setContentPane(encode_with_keys_panel);
		view.setVisible(true);
	}
	

	private class Encode implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			encode_view();
		}
	}

	private class Encode_With_Keys implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			encode_with_keys_view();
		}
	}
	
//menu
	private class Decode implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			//decode_panel	= view.getImagePanel();
		decode_view();
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ImageValidator());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String image = directory.getPath();
					stat_name = directory.getName();
					stat_path = directory.getPath();
					stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
					stat_name = stat_name.substring(0, stat_name.length()-4);
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(image))));
				}
				catch(Exception except) {
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", 
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
	}


	private class Decode_With_Keys implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{

			decode_with_keys_view();
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ImageValidator());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String image = directory.getPath();
					stat_name = directory.getName();
					stat_path = directory.getPath();
					stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
					stat_name = stat_name.substring(0, stat_name.length()-4);
					image_input1.setIcon(new ImageIcon(ImageIO.read(new File(image))));
				}
				catch(Exception except) {
					JOptionPane.showMessageDialog(view, "The File cannot be opened!",
							"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	

	private class Exit implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			System.exit(0); //exit the program
		}
	}
	
//button
	private class EncodeButton implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ImageValidator());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String text = input.getText();
					String ext  = ImageValidator.getExtension(directory);
					String name = directory.getName();
					String path = directory.getPath();
					path = path.substring(0,path.length()-name.length()-1);
					name = name.substring(0, name.length()-4);
					
					String stegan = JOptionPane.showInputDialog(view,
									"Enter output file name:", "File name",
									JOptionPane.PLAIN_MESSAGE);
					
					if(steglogic.encode(path,name,ext,stegan,text,1,1))
					{
						JOptionPane.showMessageDialog(view, "The Image was encoded Successfully!", 
							"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(view, "The Image could not be encoded!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					//display the new image
					decode_view();
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(path + "/" + stegan + ".png"))));
				}
				catch(Exception except) {
				//msg if opening fails
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", 
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
	}

	private class EncodeWithKeysButton implements ActionListener//TODO change steglogic
	{
		/*
		 *handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			String key1 = JOptionPane.showInputDialog(view,
					"Enter key1:", "File name",
					JOptionPane.PLAIN_MESSAGE);

			String key2 = JOptionPane.showInputDialog(view,
					"Enter key2:", "File name",
					JOptionPane.PLAIN_MESSAGE);
			//start path of displayed File Chooser
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new ImageValidator());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String text = input1.getText();
					String ext  = ImageValidator.getExtension(directory);
					String name = directory.getName();
					String path = directory.getPath();
					path = path.substring(0,path.length()-name.length()-1);
					name = name.substring(0, name.length()-4);


					String stegan = JOptionPane.showInputDialog(view,
							"Enter output file name:", "File name",
							JOptionPane.PLAIN_MESSAGE);
					System.out.println(path + name + ext + stegan + text +Integer.parseInt(key1) +Integer.parseInt(key2));
					if(Integer.parseInt(key1)<=0 ||Integer.parseInt(key2)<=0 )
					{
						JOptionPane.showMessageDialog(view, "Keys must be >= 1",
								"", JOptionPane.INFORMATION_MESSAGE);
						throw new Exception();
					}
					if(steglogic_w_k.encode(path,name,ext,stegan,text,Integer.parseInt(key1),Integer.parseInt(key2)))
					{
						JOptionPane.showMessageDialog(view, "The Image was encoded Successfully!",
								"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(view, "The Image could not be encoded!",
								"Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					//display the new image
					decode_with_keys_view();
					image_input1.setIcon(new ImageIcon(ImageIO.read(new File(path + "/" + stegan + ".png"))));
				}
				catch(Exception except) {
					//msg if opening fails
					JOptionPane.showMessageDialog(view, "The File cannot be opened!",
							"Error!", JOptionPane.INFORMATION_MESSAGE);
					except.printStackTrace();
				}
			}
		}

	}
	
	/*
	 *Decode Button Class - handles the Decode Button item
	 */
	private class DecodeButton implements ActionListener
	{
		/*
		 *handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			String message = steglogic.decode(stat_path, stat_name,1,1);
			System.out.println(stat_path + ", " + stat_name);
			if(message != "")
			{
				encode_view();
				JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!", 
							"Success!", JOptionPane.INFORMATION_MESSAGE);
				input.setText(message);
			}
			else
			{
				JOptionPane.showMessageDialog(view, "The Image could not be decoded!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private class DecodeWithKeysButton implements ActionListener
	{
		/*
		 *handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			String key1 = JOptionPane.showInputDialog(view,
					"Enter key1:", "File name",
					JOptionPane.PLAIN_MESSAGE);

			String key2 = JOptionPane.showInputDialog(view,
					"Enter key2:", "File name",
					JOptionPane.PLAIN_MESSAGE);
			String message = steglogic_w_k.decode(stat_path, stat_name,Integer.parseInt(key1),Integer.parseInt(key2));
			System.out.println(stat_path + ", " + stat_name);
			if(message != "")
			{
				encode_with_keys_view();
				JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!",
						"Success!", JOptionPane.INFORMATION_MESSAGE);
				input1.setText(message);
			}
			else
			{
				JOptionPane.showMessageDialog(view, "The Image could not be decoded!",
						"Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	
	/*
	 *Updates the variables to an initial state
	 */
	public void update()
	{
		input.setText("");			//clear textarea
		input1.setText("");
		image_input.setIcon(null);	//clear image
		image_input1.setIcon(null);
		stat_path = "";				//clear path
		stat_name = "";				//clear name
	}
	
	/*
	 *Main Method for testing
	 */
	public static void main(String args[])
	{
		new Main_Steganography(
									new User_Interface("Steganography")
									//new Steganography_1()
									);
	}
}