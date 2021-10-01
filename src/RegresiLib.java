public class RegresiLib {
    public static Matriks dataToSPL(Matriks data) {
        // Masukkan data ke matriks
        // y letaknya paling kanan, data udah disediakan dulu

        /* BANYAK DATA */
        int n = data.Nbaris;
        int k = data.Nkolom;

        /* MATRIKS HASIL */
        Matriks spl = new Matriks(k + 1, k + 2);

        /* KOMPUTASI */
        // baris kesatu
        spl.matriks[0][0] = n;                              // elemen kesatu
        for (int col = 1; col < k + 1; col++) {             // forloop elemen selanjutnya
            int sum = 0;
            for (int dataidx = 0; dataidx < n; dataidx++) {
                sum += data.matriks[dataidx][col - 1];
            }
            spl.matriks[0][col] = sum;
        }

        // baris selanjutnya sampai terakhir
        for (int row = 1; row < k + 1; row++) {
            // elemen paling kiri
            int sum = 0;
            for (int dataidx = 0; dataidx < n; dataidx++) {
                sum += data.matriks[dataidx][row - 1];
            }
            spl.matriks[row][0] = sum;
            // elemen tengah sampe kanan
            for (int col = 1; col < k + 2; col++) {
                sum = 0;
                for (int dataidx = 0; dataidx < n; dataidx++) {
                    sum += data.matriks[dataidx][row - 1] * data.matriks[dataidx][col - 1];
                }
                spl.matriks[row][col] = sum;
            }
        }

        /* HASIL AKHIR */
        return spl;
    }
}
