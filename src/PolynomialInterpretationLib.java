public class PolynomialInterpretationLib {
    public static Matriks createAugmented(Matriks coordMatrix) {
        Matriks m = new Matriks(coordMatrix.Nbaris, coordMatrix.Nbaris + 1);
        for (int i = 0; i < coordMatrix.Nbaris; i++) {
            for (int j = 0; j < coordMatrix.Nbaris; j++) {
                m.matriks[i][j] = Math.pow(coordMatrix.matriks[i][0], j);
            }
            m.matriks[i][coordMatrix.Nbaris] = coordMatrix.matriks[i][1];
        }
        return m;
    }
}
