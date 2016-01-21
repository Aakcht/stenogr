
public interface Steganography {
    public boolean encode(String path, String original, String ext1, String stegan, String message,long key1,long key2);
    public String decode(String path, String name,long key1,long key2);

}
