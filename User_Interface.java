

/*
 *Import List
 */
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;


public class User_Interface extends JFrame
{

	private static int WIDTH  = 500;
	private static int HEIGHT = 400;

	private JTextArea 	input;
	private JTextArea   input1;
	private JTextArea input_for_key1;
	private JTextArea input_for_key2;
	private JScrollBar 	scroll,scroll2;
	private JButton		encodeButton,decodeButton,encodeWithKeysButton,decodeWithKeysButton;
	private JButton testbutton;
	private JLabel		image_input,image_input1;

	private JMenu 		file;
	private JMenuItem 	encode;
	private JMenuItem 	decode;


	private JMenuItem   encode_with_keys;
	private JMenuItem   decode_with_keys;
	private JMenuItem 	exit;

	public User_Interface(String name)
	{
		super(name);
		JMenuBar menu = new JMenuBar();
		
		JMenu file = new JMenu("File");	file.setMnemonic('F');
		encode = new JMenuItem("Encode"); encode.setMnemonic('E'); file.add(encode);
		decode = new JMenuItem("Decode"); decode.setMnemonic('D'); file.add(decode);
		encode_with_keys=new JMenuItem("Encode with keys"); encode_with_keys.setMnemonic('E'); file.add(encode_with_keys);
		decode_with_keys=new JMenuItem("Decode with keys"); decode_with_keys.setMnemonic('D'); file.add(decode_with_keys);
		file.addSeparator();
		exit = new JMenuItem("Exit"); exit.setMnemonic('x'); file.add(exit);
		
		menu.add(file);
		setJMenuBar(menu);

		setResizable(true);
		setBackground(Color.lightGray);
		setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
	}




	public JMenuItem	getEncode()		{ return encode;			}

	public JMenuItem	getDecode()		{ return decode;			}



	public JMenuItem getEncode_with_keys() {return encode_with_keys;}

	public JMenuItem getDecode_with_keys() {return decode_with_keys;}

	public JMenuItem	getExit()		{ return exit;				}

	public JTextArea	getText()		{ return input;				}
	public JTextArea getText1(){return input1;}

	public JLabel		getImageInput()	{ return image_input;		}
	public JLabel       getImageInput1(){return image_input1;}

	public JPanel		getTextPanel()	{ return new Text_Panel();	}
	public  JPanel getTextPanelWithKeys(){return  new Text_Panel_With_Keys();}

	public JPanel		getImagePanel()	{ return new Image_Panel();	}

	public JPanel		getImagePanelWithKeys()	{ return new Image_Panel_With_Keys();	}

	public JButton		getEButton()	{ return encodeButton;		}

	public JButton      getEWKButton()  { return encodeWithKeysButton;}

	public JButton		getDButton()	{ return decodeButton;		}
	public JButton      getDWKButton()  { return decodeWithKeysButton;}
	

	private class Text_Panel extends JPanel
	{

		public Text_Panel()
		{

			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints layoutConstraints = new GridBagConstraints(); 
			setLayout(layout);
			
			input = new JTextArea();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll = new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			layout.setConstraints(scroll,layoutConstraints);
			scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    	add(scroll);
	    	//testbutton=new JButton("TEST");
	    	encodeButton = new JButton("Encode without keys");
	    	layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(encodeButton,layoutConstraints);
	    	add(encodeButton);
			//add(testbutton);
	    	

			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		}
	}


	private class Text_Panel_With_Keys extends JPanel
	{

		public Text_Panel_With_Keys()
		{

			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints layoutConstraints = new GridBagConstraints();
			setLayout(layout);

			input1 = new JTextArea();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0;
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1;
			layoutConstraints.fill 		= GridBagConstraints.BOTH;
			layoutConstraints.insets 	= new Insets(0,0,0,0);
			layoutConstraints.anchor 	= GridBagConstraints.CENTER;
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll = new JScrollPane(input1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			layout.setConstraints(scroll,layoutConstraints);
			scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			add(scroll);

			encodeWithKeysButton = new JButton("Encode with keys"); //TODO it's not an encode button!!!!
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1;
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1;
			layoutConstraints.fill 		= GridBagConstraints.BOTH;
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5);
			layoutConstraints.anchor 	= GridBagConstraints.CENTER;
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(encodeWithKeysButton,layoutConstraints);
			add(encodeWithKeysButton);
			//add(testbutton);

			//set basic display
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

		}
	}


	private class Image_Panel extends JPanel
	{

		public Image_Panel()
		{

			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints layoutConstraints = new GridBagConstraints(); 
			setLayout(layout);
			
			image_input = new JLabel();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll2 = new JScrollPane(image_input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			layout.setConstraints(scroll2,layoutConstraints);
			scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			image_input.setHorizontalAlignment(JLabel.CENTER);
	    	add(scroll2);
	    	
	    	decodeButton = new JButton("Decode without keys");
	    	layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(decodeButton,layoutConstraints);
	    	add(decodeButton);
	    	
	    	//set basic display
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    }
	 }

	private class Image_Panel_With_Keys extends JPanel
	{

		public Image_Panel_With_Keys()
		{
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints layoutConstraints = new GridBagConstraints();
			setLayout(layout);

			image_input1 = new JLabel();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0;
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1;
			layoutConstraints.fill 		= GridBagConstraints.BOTH;
			layoutConstraints.insets 	= new Insets(0,0,0,0);
			layoutConstraints.anchor 	= GridBagConstraints.CENTER;
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll2 = new JScrollPane(image_input1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			layout.setConstraints(scroll2,layoutConstraints);
			scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			image_input1.setHorizontalAlignment(JLabel.CENTER);
			add(scroll2);

			decodeWithKeysButton = new JButton("Decode with keys");
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1;
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1;
			layoutConstraints.fill 		= GridBagConstraints.BOTH;
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5);
			layoutConstraints.anchor 	= GridBagConstraints.CENTER;
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(decodeWithKeysButton,layoutConstraints);
			add(decodeWithKeysButton);

			//set basic display
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		}
	}



	/*
	 *Main Method for testing
	 */
	public static void main(String args[])
	{
		new User_Interface("Steganography");
	}
}