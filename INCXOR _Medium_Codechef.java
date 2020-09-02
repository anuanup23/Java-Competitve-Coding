import java.io.*;
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
	long arr[] = new long[10];
	final long M = 1000000007;
	long dp[][][] = new long [33][(1 << 6)][(1 << 6)];
	public void solve(InputReader in, OutputWriter out) {
		n = in.readInt();
		for(int i = 0; i < n; ++i) arr[i] = in.readInt();
		for(int i = 0; i < 33; ++i) {
			for(int j = 0; j < (1 << 6); ++j) {
				for(int k = 0; k < (1 << 6); ++k){
				    dp[i][j][k] = -1;
				}
			}
		}
		out.printLine(recur(0, (1 << (n - 1)) - 1, (1 << (n - 1)) - 1));
	}
	private long recur(int pos, int bt, int xt) {
		if(pos == 31) return 1;
		if(dp[pos][bt][xt] != -1) return dp[pos][bt][xt];
		long ret = 0;
		int  a = 0;
		for(int i = 0; i < n; ++i) 
			if((arr[i] & (1 << (30 - pos))) != 0)
				a |= (1 << i);
		for(int xr = 0; xr < (1 << n); ++xr) {
			int b = a ^ xr;
			boolean possible = true;
			int currentBit = 0, previousBit = 0, new_bt = 0, new_xt = 0;
			for(int j = n - 1; j >= 0; j--) {
				currentBit = ((b & (1 << j)) == 0) ? 0 : 1;
				if((bt & (1 << j)) != 0) {
					if(previousBit < currentBit) {
						possible = false;
					}
					if(currentBit == previousBit) new_bt |= (1 << j);
				}
				previousBit = currentBit;
			}
			for(int j = n - 1; j >= 0; j--) {
				currentBit = ((xr & (1 << j)) == 0) ? 0 : 1;
				if((xt & (1 << j)) != 0) {
					if(previousBit < currentBit) {
						possible = false;
					}
					if(currentBit == previousBit) new_xt |= (1 << j);
				}
				previousBit = currentBit;
			}
			if(possible == true) {
				ret = (ret % M + (recur(pos + 1, new_bt, new_xt)) % M) % M;
			}
		}
		return dp[pos][bt][xt] = ret;
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