/*

If we don't know programming, how do we find the 4 boundaries given a black pixel?

Do we need to search every black cell? Absolutely not.

Intuitively, we would expand from the given 1 * 1 black cell, "aggressively" expand to the 4 boundaries, roughly half of the remaining space. If we don't "cut" any black pixel, we know we go too far and should go back half.

This is exactly the process of binary search.

One simple way without any worry about boundary, is as follows:

Use a vertical line, to jump to the leftmost black pixel , in the range of [0, y]
Use a vertical line, to jump to the rightmost black pixel, in the range of [y, n - 1]
Use a horizontal line, to jump to the topmost black pixel, in the range of [0, x]
Use a horizontal line, to jump to the bottommost black pixel, in the range of [x, m - 1]*/
public int minArea(char[][] image, int x, int y) {
    int m = image.length, n = image[0].length;
    int colMin = binarySearch(image, true, 0, y, 0, m, true);
    int colMax = binarySearch(image, true, y + 1, n, 0, m, false);
    int rowMin = binarySearch(image, false, 0, x, colMin, colMax, true);
    int rowMax = binarySearch(image, false, x + 1, m, colMin, colMax, false);
    return (rowMax - rowMin) * (colMax - colMin);
}

public int binarySearch(char[][] image, boolean horizontal, int lower, int upper, int min, int max, boolean goLower) {
    while(lower < upper) {
        int mid = lower + (upper - lower) / 2;
        boolean inside = false;
        for(int i = min; i < max; i++) {
            if((horizontal ? image[i][mid] : image[mid][i]) == '1') {
                inside = true; 
                break;
            }
        }
        if(inside == goLower) {
            upper = mid;
        } else {
            lower = mid + 1;
        }
    }
    return lower;
}
/*

Suppose we have a 2D array

"000000111000000"
"000000101000000"
"000000101100000"
"000001100100000"
Imagine we project the 2D array to the bottom axis with the rule "if a column has any black pixel it's projection is black otherwise white". The projected 1D array is

"000001111100000"
Theorem

If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.

Proof by contradiction

Assume to the contrary that there are disconnected black pixels at i
and j where i < j in the 1D projection array. Thus there exists one
column k, k in (i, j) and and the column k in the 2D array has no
black pixel. Therefore in the 2D array there exists at least 2 black
pixel regions separated by column k which contradicting the condition
of "only one black pixel region".

Therefore we conclude that all the black pixels in the 1D projection
array is connected.
This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.

To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.

To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)

We can do the same for the other boundaries. The area is then calculated by the boundaries.
Thus the algorithm runs in O(m log n + n log m)

*/

private char[][] image;
public int minArea(char[][] iImage, int x, int y) {
    image = iImage;
    int m = image.length, n = image[0].length;
    int left = searchColumns(0, y, 0, m, true);
    int right = searchColumns(y + 1, n, 0, m, false);
    int top = searchRows(0, x, left, right, true);
    int bottom = searchRows(x + 1, m, left, right, false);
    return (right - left) * (bottom - top);
}
private int searchColumns(int i, int j, int top, int bottom, boolean opt) {
    while (i != j) {
        int k = top, mid = (i + j) / 2;
        while (k < bottom && image[k][mid] == '0') ++k;
        if (k < bottom == opt)
            j = mid;
        else
            i = mid + 1;
    }
    return i;
}
private int searchRows(int i, int j, int left, int right, boolean opt) {
    while (i != j) {
        int k = left, mid = (i + j) / 2;
        while (k < right && image[mid][k] == '0') ++k;
        if (k < right == opt)
            j = mid;
        else
            i = mid + 1;
    }
    return i;
}
//  Runtime: 1 ms
