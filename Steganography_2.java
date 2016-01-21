
import java.awt.*;
import java.io.File;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Steganography_2 implements Steganography
{

    private int imageheight,imagewidth;

    public Steganography_2()
    {
    }

    ArrayList<coord> coords=new ArrayList<coord>();

    class coord
    {
        int x,y,z;

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof coord)&&(((coord) obj).x==x)&&(((coord) obj).y==y)&&(((coord) obj).z==z);
        }
    }

    public boolean encode(String path, String original, String ext1, String stegan, String message,long key1, long key2)
    {

        coords.clear();
        String			file_name 	= image_path(path,original,ext1);
        BufferedImage 	image_orig	= getImage(file_name);
        imageheight=image_orig.getHeight();
        imagewidth =image_orig.getWidth();
        Random coord_generator=new Random(key1);
        for(int i = 0;i<key2;i++ )
        {
            coord_generator.nextInt();
        }


        BufferedImage image = user_space(image_orig);
        encode_info(image,coord_generator,message.getBytes().length);
        encode_message(image,coord_generator,message);
        //image.se
        //image = add_text(image,message);

        return(setImage(image,new File(image_path(path,stegan,"png")),"png"));
    }
    public void setBit(BufferedImage image,coord c,int val)
    {
        try
        {Color temp=new Color(image.getRGB(c.x,c.y));}
        catch (Exception e){
        System.out.println(c.x + "   " + c.y);
    }
        Color temp=new Color(image.getRGB(c.x,c.y));
        if(c.z==0)
        {
            int t=temp.getRed();
            if(val==1)
            {
                t= ((t>>1)<<1)+1;
            }
            if(val==0)
            {
                t=((t>>1)<<1);
            }
            image.setRGB(c.x,c.y,new Color(t,temp.getGreen(),temp.getBlue()).getRGB());
        }
        if(c.z==1)
        {
            int t=temp.getGreen();
            if(val==1)
            {
                t= ((t>>1)<<1)+1;
            }
            if(val==0)
            {
                t=((t>>1)<<1);
            }

            image.setRGB(c.x,c.y,new Color(temp.getRed(),t,temp.getBlue()).getRGB());
        }
        if(c.z==2)
        {
            int t=temp.getBlue();
            if(val==1)
            {
                t= ((t>>1)<<1)+1;
            }
            if(val==0)
            {
                t=((t>>1)<<1);
            }
            image.setRGB(c.x,c.y,new Color(temp.getRed(),temp.getGreen(),t).getRGB());
        }
    }

    public int getBit(BufferedImage image,coord c,int val)
    {
        Color temp=new Color(image.getRGB(c.x,c.y));
        if (c.z == 0)
        {
            return temp.getRed()&1;
        }
        if (c.z == 1)
        {
            return temp.getGreen()&1;
        }
        if (c.z == 2)
        {
            return temp.getBlue()&1;
        }

        return 12;
    }

    public boolean encode_info(BufferedImage image,Random coord_generator,int length)
    {
        for(int i=0;i<40;i++)
        {
            coord c = new coord();
            c.x=Math.abs(coord_generator.nextInt()%imagewidth);
            c.y=Math.abs(coord_generator.nextInt()%imageheight);
            c.z=Math.abs(coord_generator.nextInt()%3);

            if(coords.contains(c))
            {
                i--;
            }
            else
            {
                coords.add(c);
                setBit(image,c,i%2);
            }

        }
        for(int i=0;i<20;i++)
        {
            coord c = new coord();
            c.x=Math.abs(coord_generator.nextInt()%imagewidth);
            c.y=Math.abs(coord_generator.nextInt()%imageheight);
            c.z=Math.abs(coord_generator.nextInt()%3);
            if(coords.contains(c))
            {
                i--;
            }
            else
            {
                setBit(image,c,length&1);
                length=length>>1;
            }
        }

        return true;
    }

    int decode_info(BufferedImage image, Random coord_generator) throws Exception
    {
         int length=0;
        for(int i=0;i<40;i++)
        {
            coord c = new coord();
            c.x=Math.abs(coord_generator.nextInt()%imagewidth);
            c.y=Math.abs(coord_generator.nextInt()%imageheight);
            c.z=Math.abs(coord_generator.nextInt()%3);;

            if(coords.contains(c))
            {
                i--;
            }
            else
            {
                coords.add(c);
               // System.out.println(getBit(image, c, length & 1));
                if((i%2)!=getBit(image,c,i%2))
                {

                    throw new Exception();
                }
            }
        }

        for(int i=0;i<20;i++) {
            coord c = new coord();
            c.x=Math.abs(coord_generator.nextInt()%imagewidth);
            c.y=Math.abs(coord_generator.nextInt()%imageheight);
            c.z=Math.abs(coord_generator.nextInt()%3);
            if (coords.contains(c)) {
                i--;
            } else {


                length = (length ) | getBit(image, c, length & 1)<<i;
                System.out.println(getBit(image,c,length&1)+"||"+length);
            }

        }

        return length;
    }


    boolean encode_message(BufferedImage image,Random coord_generator,String message)
    {
        byte[] b = message.getBytes();
        for(int i=0;i<b.length;i++)
        {
            for(int j=0;j<8;j++)
            {
                coord c = new coord();
                c.x=Math.abs(coord_generator.nextInt()%imagewidth);
                c.y=Math.abs(coord_generator.nextInt()%imageheight);
                c.z=Math.abs(coord_generator.nextInt()%3);
                if(coords.contains(c))
                {
                    j--;
                }
                else
                {
                    setBit(image,c,b[i]&1);
                    b[i]= (byte) (b[i]>>1);
                }
            }
        }
        return true;
    }

    String decode_message(BufferedImage image,Random coord_generator,int length)
    {
        byte[] b=new byte[length];
        for(int i=0;i<length;i++)
        {
            b[i]=0;
            for(int j=0;j<8;j++)
            {
                coord c = new coord();
                c.x=Math.abs(coord_generator.nextInt()%imagewidth);
                c.y=Math.abs(coord_generator.nextInt()%imageheight);
                c.z=Math.abs(coord_generator.nextInt()%3);
                if(coords.contains(c))
                {
                    j--;
                }
                else
                {
                    b[i]= (byte) (b[i]+(getBit(image,c,1)<<j));
                    System.out.println("+"+b[i]+"+");

                }
        //        System.out.println(b[i]);
            }
        }
        return new String(b);
    }

    public String decode(String path, String name,long key1,long key2)
    {
        byte[] decode;
        int length;
        Random coord_generator = new Random(key1);
        coords.clear();
        for(int i = 0;i<key2;i++ )
        {
            coord_generator.nextInt();
        }
        try
        {
            //user space is necessary for decrypting
            BufferedImage image  = user_space(getImage(image_path(path,name,"png")));
            imagewidth=image.getWidth();
            imageheight=image.getHeight();
            //decode = decode_text(get_byte_data(image));
            length=decode_info(image,coord_generator);
            System.out.println("!!!!!!"+length+"!!!!!");
            String decode_result;
            decode_result=decode_message(image,coord_generator,length);
            System.out.println("|"+decode_result+"|");
            return(decode_result);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "There is no hidden message in this image!","Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return "";
        }
    }


    private String image_path(String path, String name, String ext)
    {
        return path + "/" + name + "." + ext;
    }

    private BufferedImage getImage(String f)
    {
        BufferedImage 	image	= null;
        File 		file 	= new File(f);

        try
        {
            image = ImageIO.read(file);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,
                    "Image could not be read!","Error",JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    private boolean setImage(BufferedImage image, File file, String ext)
    {
        try
        {
            file.delete(); //delete resources used by the File
            ImageIO.write(image,ext,file);
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "File could not be saved!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    private BufferedImage add_text(BufferedImage image, String text)
    {
        //convert all items to byte arrays: image, message, message length
        byte img[]  = get_byte_data(image);
        byte msg[] = text.getBytes();
        byte len[]   = bit_conversion(msg.length);
        try
        {
            encode_text(img, len,  0); //0 first positiong
            encode_text(img, msg, 32); //4 bytes of space for length: 4bytes*8bit = 32 bits
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Target File cannot hold message!", "Error",JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    private BufferedImage user_space(BufferedImage image)
    {
        //create new_img with the attributes of image
        BufferedImage new_img  = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D    graphics = new_img.createGraphics();
        graphics.drawRenderedImage(image, null);
        graphics.dispose(); //release all allocated memory for this image
        return new_img;
    }


    private byte[] get_byte_data(BufferedImage image)
    {
        WritableRaster raster   = image.getRaster();
        DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
    }


    private byte[] bit_conversion(int i)
    {

        byte byte3 = (byte)((i & 0xFF000000) >>> 24); //0
        byte byte2 = (byte)((i & 0x00FF0000) >>> 16); //0
        byte byte1 = (byte)((i & 0x0000FF00) >>> 8 ); //0
        byte byte0 = (byte)((i & 0x000000FF)       );

        return(new byte[]{byte3,byte2,byte1,byte0});
    }


    private byte[] encode_text(byte[] image, byte[] addition, int offset)
    {

        if(addition.length + offset > image.length)
        {
            throw new IllegalArgumentException("File not long enough!");
        }

        for(int i=0; i<addition.length; ++i)
        {

            int add = addition[i];
            for(int bit=7; bit>=0; --bit, ++offset)
            {

                int b = (add >>> bit) & 1;

                image[offset] = (byte)((image[offset] & 0xFE) | b );
            }
        }
        return image;
    }


    private byte[] decode_text(byte[] image)
    {
        int length = 0;
        int offset  = 32;

        for(int i=0; i<32; ++i)
        {
            length = (length << 1) | (image[i] & 1);
        }

        byte[] result = new byte[length];


        for(int b=0; b<result.length; ++b )
        {

            for(int i=0; i<8; ++i, ++offset)
            {

                result[b] = (byte)((result[b] << 1) | (image[offset] & 1));
            }
        }
        return result;
    }
}
