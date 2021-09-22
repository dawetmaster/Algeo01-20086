// Kelas untuk reduksi matriks menjadi matriks eselon/eselon tereduksi

public class EchelonRedux {
    /* Reduksi matriks sampai menjadi eselon baris, asumsi matriks terdefinisi
    * WARNING: Not tested yet, baru sampai matriks eselon baris tidak tereduksi */
    private static void selfReduce(Matriks m) {
        int i = 0;
        while (i < m.Nbaris) {
            for (int j = 0; j < m.Nkolom; j++) {
                /* Cari pivot dari matriks */
                if (m.matriks[i][j] == 0) {
                    /* cari indeks baris pertama dengan nilai paling kiri tidak nol */
                    int pivot_index = i + 1;
                    int row_swap_target = -1; // inisialisasi buat mark jika tidak ketemu
                    while (pivot_index < m.Nbaris) {
                        if (m.matriks[pivot_index][j] != 0) {
                            row_swap_target = pivot_index;
                            break;
                        } else {
                            pivot_index++;
                        }
                    }
                    /* cek apakah row_swap_target != -1, kalau tidak begitu langsung skip aja */
                    if (row_swap_target != -1) {
                        double[] temp = m.matriks[i];
                        m.matriks[i] = m.matriks[row_swap_target];
                        m.matriks[row_swap_target] = temp;
                    } else {
                        continue;
                    }
                }
                /* Bagi baris dengan skalar agar elemen non-nol paling kiri = 1 */
                double divider = m.matriks[i][j];
                for (int k = 0; k < m.Nkolom; k++) {
                    m.matriks[i][k] /= divider;
                }
                /* Mulai reduksi baris */
                for (int row = i + 1; row < m.Nbaris; row++) {
                    double multiplier = m.matriks[row][j];
                    for (int col = 0; col < m.Nkolom; col++) {
                        m.matriks[row][col] -= multiplier * m.matriks[i][col];
                    }
                }
                /* reduksi baris selesai */
                i++;
            }
        }
    }
}
