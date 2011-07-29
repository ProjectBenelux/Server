/* cea - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.DataInputStream;
import java.net.URL;

final class DecryptString {
    static int a;
    static int b;
    static int c;
    static int d;
    static int e;
    static int f;
    static int g;
    static int[] h = new int[6];
    
    public static void a(byte i) {
		h = null;
		if (i < 2)
			a(-8, 88, 87);
    }
    
    static final boolean a(int i, int i_13_, int i_14_) {
		e++;
		if (i_14_ >= -126)
			return false;
		if ((0x10000 & i_13_) == 0)
			return false;
		return true;
    }
   
    static final String a(int i, int i_25_, byte[] is, int i_26_) {
		d++;
		char[] cs = new char[i_25_];
		int i_27_ = 0;
		for (int i_28_ = 0; i_28_ < i_25_; i_28_++) {
			int i_29_ = is[i_28_ + i_26_] & 0xff;
			if (i_29_ != 0) {
			if (i_29_ >= 128 && i_29_ < 160) {
				int i_30_ = ASCII_Char.g[i_29_ - 128];
				if (i_30_ == 0)
				i_30_ = 63;
				i_29_ = i_30_;
			}
			cs[i_27_++] = (char) i_29_;
			}
		}
		if (i != 26144)
			return null;
		return new String(cs, 0, i_27_);
    }

}
