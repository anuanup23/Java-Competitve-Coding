PROBLEM URL   https://www.codechef.com/FLPAST01/problems/BADMATH


import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
	 public static void main(String[] args) throws Exception {
	        InputReader in = new InputReader(System.in);
	        OutputWriter out = new OutputWriter(System.out);
			Task Solver = new Task();
			int t = in.readInt();
			while(t-- > 0)
				Solver.solve(in,out);
			out.close();
	 }
}
class Task{
	int n;
	String s;
	String S[] = new String[100];
	BigInteger zero = new BigInteger("0");
	BigInteger two = new BigInteger("2");
	BigInteger m;
	public void solve(InputReader in, OutputWriter out) {
		n = in.readInt();
		String t = in.readString();
		m = new BigInteger(t);
		s = in.readString();
		S[0] = "1";
		for(int i = 1; i < 100; ++i){
		    S[i] = S[i - 1] + "0"; 
		}
		out.printLine(recur(0,zero));
	}
	private long recur(int pos, BigInteger val) {
		if(pos >= s.length()) {
			if(val.mod(m).equals(zero)) {
				return 1;
			}
			else return 0;
		}
		long ret = 0;
		BigInteger new_val = val;
		if(s.charAt(s.length() - 1 - pos) != '_') {
			int num = s.charAt(s.length() - 1 - pos ) == '1' ? 1 : 0;
			if(num == 1) {
				BigInteger q = new BigInteger(S[pos],2);
				new_val = new_val.or(q);
			}
			ret += recur(pos + 1, new_val);
		}
		else {
			BigInteger q = new BigInteger(S[pos],2);
			new_val = new_val.or(q);
			ret += recur(pos + 1, new_val);
			ret += recur(pos + 1, val);
		}
		return ret;
	}
}
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;
	public InputReader(InputStream stream) {
		this.stream = stream;
	}
	public int read() {
		if (numChars == -1) {
			throw new InputMismatchException();
		}
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0) {
				return -1;
			}
		}
		return buf[curChar++];
	}
	public int readInt() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
	public String readString() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}
	public double readDouble() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E') {
				return res * Math.pow(10, readInt());
			}
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}
			res *= 10;
			res += c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				m /= 10;
				res += (c - '0') * m;
				c = read();
			}
		}
		return res * sgn;
	}
	public long readLong() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9') {
				throw new InputMismatchException();
			}
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
	
	public boolean isSpaceChar(int c) {
		if (filter != null) {
			return filter.isSpaceChar(c);
		}
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
	public String next() {
		return readString();
	}
	
	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}
class OutputWriter {
	private final PrintWriter writer;
	
	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}
	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}
	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0) {
				writer.print(' ');
			}
			writer.print(objects[i]);
		}
		writer.flush();
	}
	public void printLine(Object... objects) {
		print(objects);
		writer.println();
		writer.flush();
	}
	public void close() {
		writer.close();
	}
	public void flush() {
		writer.flush();
	}
}
