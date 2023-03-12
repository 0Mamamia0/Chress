public final class f {
    private final String a = "0123456789abcdef";

    private String a(int i) {
        String str = "";
        for (int i2 = 0; i2 <= 3; i2++) {
            str = new StringBuffer(str).append(this.a.charAt((i >> ((i2 << 3) + 4)) & 15)).append(this.a.charAt((i >> (i2 << 3)) & 15)).toString();
        }
        return str;
    }

    private static int a(int i, int i2) {
        return (((i & Integer.MAX_VALUE) + (i2 & Integer.MAX_VALUE)) ^ (i & Integer.MIN_VALUE)) ^ (i2 & Integer.MIN_VALUE);
    }

    private int a(int i, int i2, int i3, int i4, int i5, int i6) {
        int a = a(a(i2, i), a(i4, i6));
        return a((a << i5) | (a >>> (32 - i5)), i3);
    }

    private int a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return a((i2 & i3) | ((i2 ^ (-1)) & i4), i, i2, i5, i6, i7);
    }

    private int b(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return a((i2 & i4) | (i3 & (i4 ^ (-1))), i, i2, i5, i6, i7);
    }

    private int c(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return a((i2 ^ i3) ^ i4, i, i2, i5, i6, i7);
    }

    private int d(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return a(i3 ^ (i2 | (i4 ^ (-1))), i, i2, i5, i6, i7);
    }

    public final String a(String str) {
        int length = ((str.length() + 8) >> 6) + 1;
        int[] iArr = new int[length << 4];
        for (int i = 0; i < (length << 4); i++) {
            iArr[i] = 0;
        }
        int i2 = 0;
        while (i2 < str.length()) {
            int i3 = i2 >> 2;
            iArr[i3] = iArr[i3] | (str.charAt(i2) << ((i2 % 4) << 3));
            i2++;
        }
        int i4 = i2 >> 2;
        iArr[i4] = iArr[i4] | (128 << ((i2 % 4) << 3));
        iArr[(length << 4) - 2] = str.length() << 3;
        int i5 = 1732584193;
        int i6 = -271733879;
        int i7 = -1732584194;
        int i8 = 271733878;
        for (int i9 = 0; i9 < iArr.length; i9 += 16) {
            int a = a(i5, i6, i7, i8, iArr[i9], 7, -680876936);
            int a2 = a(i8, a, i6, i7, iArr[i9 + 1], 12, -389564586);
            int a3 = a(i7, a2, a, i6, iArr[i9 + 2], 17, 606105819);
            int a4 = a(i6, a3, a2, a, iArr[i9 + 3], 22, -1044525330);
            int a5 = a(a, a4, a3, a2, iArr[i9 + 4], 7, -176418897);
            int a6 = a(a2, a5, a4, a3, iArr[i9 + 5], 12, 1200080426);
            int a7 = a(a3, a6, a5, a4, iArr[i9 + 6], 17, -1473231341);
            int a8 = a(a4, a7, a6, a5, iArr[i9 + 7], 22, -45705983);
            int a9 = a(a5, a8, a7, a6, iArr[i9 + 8], 7, 1770035416);
            int a10 = a(a6, a9, a8, a7, iArr[i9 + 9], 12, -1958414417);
            int a11 = a(a7, a10, a9, a8, iArr[i9 + 10], 17, -42063);
            int a12 = a(a8, a11, a10, a9, iArr[i9 + 11], 22, -1990404162);
            int a13 = a(a9, a12, a11, a10, iArr[i9 + 12], 7, 1804603682);
            int a14 = a(a10, a13, a12, a11, iArr[i9 + 13], 12, -40341101);
            int a15 = a(a11, a14, a13, a12, iArr[i9 + 14], 17, -1502002290);
            int a16 = a(a12, a15, a14, a13, iArr[i9 + 15], 22, 1236535329);
            int b = b(a13, a16, a15, a14, iArr[i9 + 1], 5, -165796510);
            int b2 = b(a14, b, a16, a15, iArr[i9 + 6], 9, -1069501632);
            int b3 = b(a15, b2, b, a16, iArr[i9 + 11], 14, 643717713);
            int b4 = b(a16, b3, b2, b, iArr[i9], 20, -373897302);
            int b5 = b(b, b4, b3, b2, iArr[i9 + 5], 5, -701558691);
            int b6 = b(b2, b5, b4, b3, iArr[i9 + 10], 9, 38016083);
            int b7 = b(b3, b6, b5, b4, iArr[i9 + 15], 14, -660478335);
            int b8 = b(b4, b7, b6, b5, iArr[i9 + 4], 20, -405537848);
            int b9 = b(b5, b8, b7, b6, iArr[i9 + 9], 5, 568446438);
            int b10 = b(b6, b9, b8, b7, iArr[i9 + 14], 9, -1019803690);
            int b11 = b(b7, b10, b9, b8, iArr[i9 + 3], 14, -187363961);
            int b12 = b(b8, b11, b10, b9, iArr[i9 + 8], 20, 1163531501);
            int b13 = b(b9, b12, b11, b10, iArr[i9 + 13], 5, -1444681467);
            int b14 = b(b10, b13, b12, b11, iArr[i9 + 2], 9, -51403784);
            int b15 = b(b11, b14, b13, b12, iArr[i9 + 7], 14, 1735328473);
            int b16 = b(b12, b15, b14, b13, iArr[i9 + 12], 20, -1926607734);
            int c = c(b13, b16, b15, b14, iArr[i9 + 5], 4, -378558);
            int c2 = c(b14, c, b16, b15, iArr[i9 + 8], 11, -2022574463);
            int c3 = c(b15, c2, c, b16, iArr[i9 + 11], 16, 1839030562);
            int c4 = c(b16, c3, c2, c, iArr[i9 + 14], 23, -35309556);
            int c5 = c(c, c4, c3, c2, iArr[i9 + 1], 4, -1530992060);
            int c6 = c(c2, c5, c4, c3, iArr[i9 + 4], 11, 1272893353);
            int c7 = c(c3, c6, c5, c4, iArr[i9 + 7], 16, -155497632);
            int c8 = c(c4, c7, c6, c5, iArr[i9 + 10], 23, -1094730640);
            int c9 = c(c5, c8, c7, c6, iArr[i9 + 13], 4, 681279174);
            int c10 = c(c6, c9, c8, c7, iArr[i9], 11, -358537222);
            int c11 = c(c7, c10, c9, c8, iArr[i9 + 3], 16, -722521979);
            int c12 = c(c8, c11, c10, c9, iArr[i9 + 6], 23, 76029189);
            int c13 = c(c9, c12, c11, c10, iArr[i9 + 9], 4, -640364487);
            int c14 = c(c10, c13, c12, c11, iArr[i9 + 12], 11, -421815835);
            int c15 = c(c11, c14, c13, c12, iArr[i9 + 15], 16, 530742520);
            int c16 = c(c12, c15, c14, c13, iArr[i9 + 2], 23, -995338651);
            int d = d(c13, c16, c15, c14, iArr[i9], 6, -198630844);
            int d2 = d(c14, d, c16, c15, iArr[i9 + 7], 10, 1126891415);
            int d3 = d(c15, d2, d, c16, iArr[i9 + 14], 15, -1416354905);
            int d4 = d(c16, d3, d2, d, iArr[i9 + 5], 21, -57434055);
            int d5 = d(d, d4, d3, d2, iArr[i9 + 12], 6, 1700485571);
            int d6 = d(d2, d5, d4, d3, iArr[i9 + 3], 10, -1894986606);
            int d7 = d(d3, d6, d5, d4, iArr[i9 + 10], 15, -1051523);
            int d8 = d(d4, d7, d6, d5, iArr[i9 + 1], 21, -2054922799);
            int d9 = d(d5, d8, d7, d6, iArr[i9 + 8], 6, 1873313359);
            int d10 = d(d6, d9, d8, d7, iArr[i9 + 15], 10, -30611744);
            int d11 = d(d7, d10, d9, d8, iArr[i9 + 6], 15, -1560198380);
            int d12 = d(d8, d11, d10, d9, iArr[i9 + 13], 21, 1309151649);
            int d13 = d(d9, d12, d11, d10, iArr[i9 + 4], 6, -145523070);
            int d14 = d(d10, d13, d12, d11, iArr[i9 + 11], 10, -1120210379);
            int d15 = d(d11, d14, d13, d12, iArr[i9 + 2], 15, 718787259);
            int d16 = d(d12, d15, d14, d13, iArr[i9 + 9], 21, -343485551);
            i5 = a(d13, i5);
            i6 = a(d16, i6);
            i7 = a(d15, i7);
            i8 = a(d14, i8);
        }
        return new StringBuffer(String.valueOf(a(i5))).append(a(i6)).append(a(i7)).append(a(i8)).toString();
    }
}