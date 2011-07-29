/* ByteBuffer_2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.math.BigInteger;

class ByteBuffer_2 {
    static int k;
    static int l;
    static int m;
    static int n;
    static int o;
    static int p;
    static int q;
    static int r;
    static int s;
    static int t;
    static int u = 0;
    static int v;
    static int w;
    static int x;
    static int y;
    static int z;
    static int A;
    static int B;
    static int C;
    static int D;
    static int E;
    static int F;
    static int G;
    static int H;
    static int I;
    static int J;
    static int K;
    static int L;
    static int M;
    byte[] N;//data
    static int O;
    static int P;
    static int Q;
    static int R;
    static int S;
    static int T;
    static int U;
    int V;
    static int W;
    static int X;
    static int Y;
    static int Z;
    static int ab;
    static int bb;
    static int cb;
    static int db;
    static int eb;
    static int fb;
    static int gb;
    static int hb;
    static int ib;
    static int jb;
    static int kb;
    static int lb;
    static int mb;
    static int nb;
    static int ob;
    static int pb;
    static int qb;
    static int rb;
    static int sb;
    static int tb;
    static int ub;
    static int vb;
    static int[] wb = { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
			99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 120 };
    static int xb;
    static int yb;
    static int zb;
    static int Ab;
    static int Bb;
    static int Cb;
   
    final int a(int i) {
		((ByteBuffer_2) this).V += 4;
		if (i != 4087)
			return 16;
		ib++;
		return ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3] & 0xff)
			+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 24 & ~0xffffff)
			+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 16 & 0xff0000)
			+ ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 4]) << 8));
	}
     
    final int b(int i) {
		w++;
		int i_5_ = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V] & 0xff;
		if (i != -9561)
			ab = 70;
		if (i_5_ < 128)
			return s(i ^ 0x16b) - 64;
		return c(13111) - 49152;
    }
    
    final void d(int i, int i_6_) {
		if (i > 94) {
			((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i_6_;
			l++;
			((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_6_ >> 8);
		}
    }
    
    final int a(byte i) {
		if (i != -80)
			wb = null;
		Y++;
		((ByteBuffer_2) this).V += 2;
		return ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] - 128)
			+ ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] & 0xff) << 8));
    }
    
    final void e(int i, int i_7_) {
		if (i > -60)
			b((int) -59);
		eb++;
		if ((i_7_ & ~0x7f) != 0) {
			if ((i_7_ & ~0x3fff) != 0) {
			if ((i_7_ & ~0x1fffff) != 0) {
				if ((i_7_ & ~0xfffffff) != 0)
				c(0x80 | i_7_ >>> 28, (byte) -121);
				c((0x100a2a03 | i_7_) >>> 21, (byte) -111);
			}
			c((0x2021fc | i_7_) >>> 14, (byte) -123);
			}
			c(0x80 | i_7_ >>> 7, (byte) -112);
		}
		c(0x7f & i_7_, (byte) -115);
    }
    
    final int c(int i) {
		((ByteBuffer_2) this).V += 2;
		if (i != 13111)
			b((byte) -115, (int) -41);
		T++;
		return ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] & 0xff)
			+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 8 & 0xff00));
    }
    
    final void a(byte[] is, int i, int i_10_, int i_11_) {
		I++;
		for (int i_12_ = i_10_ + i_11_ + i; i_11_ <= i_12_; i_12_--)
			is[i_12_] = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
    }
    
    final void f(int i, int i_13_) {
		K++;
		if (i != 13412)
			((ByteBuffer_2) this).N = null;
		((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i_13_;
		((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_13_ >> 8);
		((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_13_ >> 16);
		((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_13_ >> 24);
    }
    
    final int j(int i) {
		((ByteBuffer_2) this).V += 4;
		U++;
		int i_14_ = 13 % ((-55 - i) / 51);
		return (((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3]) << 8)
			+ (((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] & 0xff) << 16)
			   + ((~0xffffff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 24)
				  + (0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 4]))));
    }
    
    final void a(int i, int i_15_, int i_16_, byte[] is) {
		O++;
		if (i == 65536) {
			for (int i_17_ = i_16_; i_17_ < i_15_ + i_16_; i_17_++)
			((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = is[i_17_];
		}
    }
    
    final void a(BigInteger biginteger, int i, BigInteger biginteger_20_) {
		r++;
		int i_21_ = ((ByteBuffer_2) this).V;
		((ByteBuffer_2) this).V = 0;
		byte[] is = new byte[i_21_];
		a(is, true, i_21_, 0);
		BigInteger biginteger_22_ = new BigInteger(is);
		int i_23_ = -26 / ((i - 16) / 62);
		BigInteger biginteger_24_
			= biginteger_22_.modPow(biginteger, biginteger_20_);
		byte[] is_25_ = biginteger_24_.toByteArray();
		((ByteBuffer_2) this).V = 0;
		m(is_25_.length, 13469);
		a(65536, is_25_.length, 0, is_25_);
    }
    
    final void a(int i, int i_26_, long l) {
		p++;
		i--;
		int i_27_ = 76 % ((i_26_ - 46) / 38);
		if (i < 0 || i > 7)
			throw new IllegalArgumentException();
		for (int i_28_ = 8 * i; i_28_ >= 0; i_28_ -= 8)
			((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> i_28_);
    }
    
    final int a(boolean bool) {
	q++;
	if (bool != false)
	    wb = null;
	((ByteBuffer_2) this).V += 3;
	return ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3] & 0xff)
		+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 16 & 0xff0000)
		+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 8 & 0xff00));
    }
    
    final byte k(int i) {
	o++;
	if (i != -20933)
	    return (byte) -119;
	return ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
    }
    
    final void a(int i, boolean bool) {
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 2] = (byte) (i >> 8);
	tb++;
	if (bool != false)
	    a(-59, 98, -95, null);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 1] = (byte) i;
    }
    
    final int b(boolean bool) {
	((ByteBuffer_2) this).V += 2;
	ub++;
	if (bool != true)
	    return -107;
	return ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] - 128)
		+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 8 & 0xff00));
    }
    
    final int l(int i) {
	((ByteBuffer_2) this).V += 4;
	sb++;
	if (i < 56)
	    f(113, (int) 12);
	return ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 8 & 0xff00)
		+ ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 4] & 0xff) << 24)
		+ ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3] & 0xff) << 16)
		+ (0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1]));
    }
    
    final void a(int i, byte i_29_) {
	if (i_29_ != 0)
	    ((ByteBuffer_2) this).V = 58;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
	qb++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
    }
    
    final byte c(byte i) {
	if (i != -51)
	    l(-55, -93);
	H++;
	return (byte) (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] - 128);
    }
    
    final int m(int i) {
	((ByteBuffer_2) this).V += 4;
	if (i > -66)
	    return 88;
	A++;
	return ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2])
		+ (((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] & 0xff) << 8)
		   + (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 4] << 16 & 0xff0000))
		+ ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3]) << 24));
    }
    
    final void g(int i, int i_30_) {
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 1] = (byte) i;
	C++;
	if (i_30_ != 2105852)
	    ((ByteBuffer_2) this).V = -71;
    }
    
/*    final void d(byte i) {
	Z++;
	if (i > -114)
	    b((int) 46, (int) 76);
	if (((ByteBuffer_2) this).N != null)
	    fk.a(((ByteBuffer_2) this).N, -13476);
	((ByteBuffer_2) this).N = null;
    }
*/    
/*    final void b(String string, int i) {
	if (i < -16) {
	    X++;
	    int i_31_ = string.indexOf('\0');
	    if (i_31_ >= 0)
		throw new IllegalArgumentException("NUL character at " + i_31_
						   + " - cannot pjstr");
	    ((ByteBuffer_2) this).V += lu.a(0, string.length(), ((ByteBuffer_2) this).N,
				  (byte) -58, ((ByteBuffer_2) this).V, string);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) 0;
	}
    }
*/    
    final int n(int i) {
	((ByteBuffer_2) this).V += 4;
	kb++;
	if (i > -45)
	    ((ByteBuffer_2) this).V = -8;
	return ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 4] & 0xff)
		+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 16 & 0xff0000)
		+ ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 24 & ~0xffffff)
		   + (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3] << 8 & 0xff00)));
    }
    
    final int c(boolean bool) {
	S++;
	int i = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
	if (bool != false)
	    b((byte) 53);
	int i_32_ = 0;
	for (/**/; i < 0; i = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++])
	    i_32_ = (i_32_ | 0x7f & i) << 7;
	return i | i_32_;
    }
    
/*
    final void h(int i, int i_33_) {
	if (i_33_ <= 121)
	    p(-49);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
	cb++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 24);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 16);
    }
*/    
    final int o(int i) {
	P++;
	if (i != -49)
	    u = 41;
	((ByteBuffer_2) this).V += 2;
	int i_34_ = ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] - 128 & 0xff)
		     + (0xff00 & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] << 8));
	if (i_34_ > 32767)
	    i_34_ -= 65536;
	return i_34_;
    }
    
    final void a(long l, int i) {
	if (i == -16719) {
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 56);
	    xb++;
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 48);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 40);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 32);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 24);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 16);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) (l >> 8);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (int) l;
	}
    }
    
    final byte d(boolean bool) {
	if (bool != false)
	    return (byte) 90;
	W++;
	return (byte) -((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
    }
    
/*  final void a(int i, int[] is, int i_35_, int i_36_) {
		B++;
		int i_37_ = ((ByteBuffer_2) this).V;
		((ByteBuffer_2) this).V = i_36_;
		int i_38_ = (i - i_36_) / 8;
		int i_39_ = 0;
		if (i_35_ != -957401312)
			a(-114L, -119);
		for (; i_39_ < i_38_; i_39_++) {
			int i_40_ = l(111);
			int i_41_ = l(i_35_ ^ ~0x3910c883);
			int i_42_ = -957401312;
			int i_43_ = -1640531527;
			int i_44_ = 32;
			while (i_44_-- > 0) {
			i_41_ -= (is[i_42_ >>> 11 & ~0x6f3ffffc] + i_42_
				  ^ i_40_ + (i_40_ >>> 5 ^ i_40_ << 4));
			i_42_ -= i_43_;
			i_40_ -= is[i_42_ & 0x3] + i_42_ ^ i_41_ + (i_41_ << 4
									^ i_41_ >>> 5);
			}
			((ByteBuffer_2) this).V -= 8;
			c(i_40_, (int) -103);
			c(i_41_, (int) -114);
		}
		((ByteBuffer_2) this).V = i_37_;
    }
*/    
/*  final String p(int i) {
		rb++;
		byte i_45_ = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
		if (i_45_ != 0)
			throw new IllegalStateException("Bad version number in gjstr2");
		int i_46_ = ((ByteBuffer_2) this).V;
		while (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] != 0) {
			
		}
		int i_47_ = ((ByteBuffer_2) this).V - 1 - i_46_;
		if (i == (i_47_ ^ 0xffffffff))
			return "";
		return DecryptString.a(i + 26145, i_47_, ((ByteBuffer_2) this).N, i_46_);
    }
*/
    
    final int q(int i) {
	if (i != 255)
	    return 104;
	vb++;
	return -((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] + 128 & 0xff;
    }
    
/*
    final int r(int i) {
	s++;
	if (i > -87)
	    c((int) 98, (int) -10);
	return ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] - 128 & 0xff;
    }
*/   

 
    final String e(byte i) {
	G++;
	int i_48_ = ((ByteBuffer_2) this).V;
	if (i <= 114)
	    return null;
	while (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] != 0) {
	    
	}
	int i_49_ = ((ByteBuffer_2) this).V - i_48_ - 1;
	if (i_49_ == 0)
	    return "";
	return DecryptString.a(26144, i_49_, ((ByteBuffer_2) this).N, i_48_);
    }
   
 
    final int s(int i) {
	Bb++;
	if (i != -9268)
	    return -14;
	return ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] & 0xff;
    }
    
    final void i(int i, int i_50_) {
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 16);
	E++;
	if (i_50_ <= -88) {
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 24);
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
	}
    }
    
    public static void f(byte i) {
	if (i == -114)
	    wb = null;
    }
    
    final int t(int i) {
	if (i != -13829)
	    ((ByteBuffer_2) this).N = null;
	bb++;
	int i_51_ = 0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V];
	if (i_51_ < 128)
	    return s(i ^ 0x1237);
	return c(13111) - 32768;
    }
    
    final void j(int i, int i_52_) {
	fb++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_52_ + 128);
	int i_53_ = -83 / ((-68 - i) / 38);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_52_ >> 8);
    }
    
    final void k(int i, int i_54_) {
	x++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_54_ >> 8);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_54_ + i);
    }
    
/*
    final String g(byte i) {
	if (i <= 108)
	    ((ByteBuffer_2) this).N = null;
	pb++;
	byte i_55_ = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
	if (i_55_ != 0)
	    throw new IllegalStateException("Bad version number in gUTF8");
	int i_56_ = c(false);
	if (((ByteBuffer_2) this).N.length < ((ByteBuffer_2) this).V + i_56_)
	    throw new IllegalStateException
		      ("Length of string is longer than available data");
	String string = ci.a(((ByteBuffer_2) this).N, ((ByteBuffer_2) this).V, -241, i_56_);
	((ByteBuffer_2) this).V += i_56_;
	return string;
    }
*/
    
/*
    ByteBuffer_2(int i) {
	((ByteBuffer_2) this).N = fk.a((byte) 90, i);
	((ByteBuffer_2) this).V = 0;
    }
*/
    
    ByteBuffer_2(byte[] is) {
	((ByteBuffer_2) this).V = 0;
	((ByteBuffer_2) this).N = is;
    }
    
/*
    static final void b(int i, byte i_57_) {
	if (i_57_ != 51)
	    u = -59;
	lb++;
	kr.f = i;
	bl.v.a(18385);
    }
    
*/
    final void c(int i, byte i_58_) {
	y++;
	if (i_58_ < -107)
	    ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
    }
    
    final int u(int i) {
	((ByteBuffer_2) this).V += 2;
	zb++;
	int i_59_ = ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1])
		     + (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] << 8 & 0xff00));
	if (i <= 15)
	    ((ByteBuffer_2) this).N = null;
	if (i_59_ > 32767)
	    i_59_ -= 65536;
	return i_59_;
    }
    
    final long a(byte i, int i_60_) {
	i_60_--;
	int i_61_ = -30 / ((50 - i) / 47);
	Cb++;
	if (i_60_ < 0 || i_60_ > 7)
	    throw new IllegalArgumentException();
	int i_62_ = i_60_ * 8;
	long l = 0L;
	for (/**/; i_62_ >= 0; i_62_ -= 8)
	    l |= (0xffL & (long) ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++]) << i_62_;
	return l;
    }
    
/*    final void d(int i, byte i_63_) {
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (-i + 128);
	nb++;
	if (i_63_ != 7)
	    d((byte) 55);
    }
*/    
    final void l(int i, int i_64_) {
	z++;
	if (i_64_ >= -4)
	    ((ByteBuffer_2) this).N = null;
	((ByteBuffer_2) this).N[-i - 4 + ((ByteBuffer_2) this).V] = (byte) (i >> 24);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 3] = (byte) (i >> 16);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 2] = (byte) (i >> 8);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - i - 1] = (byte) i;
    }
    
    final int h(byte i) {
	m++;
	if (i != -18)
	    b((byte) 58);
	return 0xff & -((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
    }
    
    final void e(int i, byte i_65_) {
	if (i_65_ != -22)
	    wb = null;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 16);
	t++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
    }
    
    final int i(byte i) {
	v++;
	int i_66_ = 0;
	if (i != -16)
	    ((ByteBuffer_2) this).V = 126;
	int i_67_;
	for (i_67_ = t(-13829); i_67_ == 32767; i_67_ = t(i - 13813))
	    i_66_ += 32767;
	i_66_ += i_67_;
	return i_66_;
    }
    
    final int v(int i) {
	jb++;
	if (i != -1)
	    wb = null;
	((ByteBuffer_2) this).V += 3;
	return (((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3]) << 16)
		+ (((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2]) << 8)
		   + (0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1])));
    }
    
    final void b(byte i, int i_68_) {
	db++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i_68_ + 128);
	if (i != -72)
	    s(28);
    }
    
    final int j(byte i) {
	((ByteBuffer_2) this).V += 2;
	k++;
	if (i <= 101)
	    a(62L, 44);
	return (((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] & 0xff) << 8)
		+ (((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2] & 0xff));
    }
    
    final byte w(int i) {
	if (i != 16436)
	    ab = -82;
	Q++;
	return (byte) (-((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] + 128);
    }
    
/*
    final boolean x(int i) {
	yb++;
	((ByteBuffer_2) this).V -= 4;
	int i_69_ = oea.a(255, 0, ((ByteBuffer_2) this).N, ((ByteBuffer_2) this).V);
	int i_70_ = l(108);
	if (i_69_ == i_70_)
	    return true;
	if (i != 9182)
	    ab = -99;
	return false;
    }
*/
    
    final void a(byte[] is, boolean bool, int i, int i_71_) {
	L++;
	if (bool == true) {
	    for (int i_72_ = i_71_; i_72_ < i_71_ + i; i_72_++)
		is[i_72_] = ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++];
	}
    }
    
    final void f(int i, byte i_73_) {
	hb++;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
	if (i_73_ < 32)
	    ab = -33;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 16);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 24);
    }
    
    final int k(byte i) {
	if (i > -39)
	    c((byte) -100, (int) 122);
	F++;
	((ByteBuffer_2) this).V += 3;
	int i_74_ = ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 1] & 0xff)
		     + ((((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 3] & 0xff) << 16)
		     + ((0xff & ((ByteBuffer_2) this).N[((ByteBuffer_2) this).V - 2]) << 8));
	if (i_74_ > 8388607)
	    i_74_ -= 16777216;
	return i_74_;
    }
    
    final void c(byte i, int i_75_) {
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) -i_75_;
	if (i == 125)
	    R++;
    }
    
    final void m(int i, int i_76_) {
	Ab++;
	if (i_76_ != 13469)
	    wb = null;
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) (i >> 8);
	((ByteBuffer_2) this).N[((ByteBuffer_2) this).V++] = (byte) i;
    }
    
    final long e(boolean bool) {
	n++;
	long l = 0xffffffffL & (long) l(120);
	if (bool != false)
	    return 68L;
	long l_77_ = 0xffffffffL & (long) l(110);
	return (l << 32) + l_77_;
    }
}
