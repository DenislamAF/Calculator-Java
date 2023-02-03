package Solution;

enum RomanNumerals {
    I(1),
    IV(4),
    V(5),
    IX(9),
    X(10),
    XL(40),
    L(50),
    XC(90),
    C(100),
    D(500);
    private final int value;
    private RomanNumerals(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
}
