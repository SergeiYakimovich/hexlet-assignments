package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;

    public ReversedSequence(String text) {
        this.str = new StringBuilder(text).reverse().toString();
    }

    @Override
    public String subSequence(int n1, int n2) {
        return this.str.substring(n1, n2);
    }

    @Override
    public char charAt(int n) {
        return this.str.charAt(n);
    }

    @Override
    public int length() {
        return this.str.length();
    }

    @Override
    public String toString() {
        return this.str;
    }

}
// END
