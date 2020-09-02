import java.io.*;
import java.util.InputMismatchException;
public class Main {
	 public static void main(String[] args) throws Exception {
	        InputReader in = new InputReader(System.in);
	        OutputWriter out = new OutputWriter(System.out);
			int t = in.readInt();
			Task Solver = new Task();
			while(t-- > 0){
				Solver.solve(in,out);
			}
			out.close(); 
	 }
}
class Task{
	String a;
	long ans;
	long dp[][][][][][][]; 
	public void solve(InputReader in, OutputWriter out){
		dp =  new long [2][2][2][2][11][11][20];
		ans = 0;
		a = in.readString();
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				for(int k = 0; k < 2; ++k) {
					for(int l = 0; l < 2; ++l) {
						for(int m = 0; m < 11; ++m) {
							for(int n = 0; n < 11; ++n) {
								for(int o = 0; o < 20; ++o) dp[i][j][k][l][m][n][o] = -1;
							}
						}
					}
				}
			}
		}
		ans -= recur(0,0,1,0,0,10,10);
		a = in.readString();
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				for(int k = 0; k < 2; ++k) {
					for(int l = 0; l < 2; ++l) {
						for(int m = 0; m < 11; ++m) {
							for(int n = 0; n < 11; ++n) {
								for(int o = 0; o < 20; ++o) dp[i][j][k][l][m][n][o] = -1;
							}
						}
					}
				}
			}
		}
		ans += recur(0,0,1,0,0,10,10);
		out.printLine(ans);
	}
		private long recur(int pos, int start, int rb, int even, int odd, int last, int slast) {
			if(pos == a.length()) {
				if(odd == 1 && even == 1 && start == 1) return 1;
				else return 0;
			}
			if(dp[start][rb][even][odd][last][slast][pos] != -1) return dp[start][rb][even][odd][last][slast][pos];
			int e = (rb == 1)? a.charAt(pos) - '0' : 9;
			long ret = 0;
			if(start == 0) {
				ret += recur(pos + 1, 0, (rb & ((a.charAt(pos) == '0') ? 1 : 0)), 0, 0, last, slast);
				for(int i = 1; i <= e; ++i) 
					ret += recur(pos + 1, 1,(rb & ((e == i) ? 1 : 0)), 0, 0, i, last);
				
			}
			else {
				for(int i = 0; i <= e; ++i) {
					ret += recur(pos + 1, 1, (rb & ((i == e) ? 1 : 0)), (even | ((i == last) ? 1 : 0)), (odd | ((i == slast) ? 1 : 0)), i, last);
				}
			}
			return dp[start][rb][even][odd][last][slast][pos] = ret;
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
