public class p {
    public static int[] gradient(int i, int rgb, int i3) {
        int r = rgb >> 16;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        int i7 = (((i3 >> 16) - r) * 100) / (i - 2);
        int i8 = ((((i3 >> 8) & 255) - g) * 100) / (i - 2);
        int i9 = (((i3 & 255) - b) * 100) / (i - 2);
        int[] iArr = new int[i];
        iArr[0] = rgb;
        iArr[i - 1] = i3;
        for (int i10 = 1; i10 < i - 1; i10++) {
            iArr[i10] = ((r + ((i10 * i7) / 100)) << 16) | ((g + ((i10 * i8) / 100)) << 8) | (b + ((i10 * i9) / 100));
        }
        return iArr;
    }

}
