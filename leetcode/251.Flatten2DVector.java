/*
Since the OJ does while (i.hasNext()) cout << i.next();, i.e., always calls hasNext before next, I don't really have to call it myself so I could save that line in next. But I think that would be bad, we shouldn't rely on that.
*/

public class Vector2D {

    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    public int next() {
        hasNext();
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }
}

