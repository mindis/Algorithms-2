public class SquareFreeString {
    public String isSquareFree(String s) {
      for (int i = 0; i < s.length(); ++i) {
        for (int j = 2; i + j <= s.length(); j += 2) {
          String left = s.substring(i, i + j / 2);
          String right = s.substring(i + j / 2, i + j);
          if (left.equals(right)) {
            return "not square-free";
          }
        }
      }
      return "square-free";
    }
  }
