public class GaussMethod {
    public static double[] gaussElim(Matriks m){
        /*
           mengembalikan hasil eliminasi Gauss dalam bentuk matriks berukuran Nbaris x 1
           prereq: isUniqueSol(m) = true
         */

        double[] result = new double[m.Nbaris];

        EchelonRedux.selfReduce(m); // buat m menjadi matriks eselon baris

            // PENYULIHAN MUNDUR
            int i, j, pivot;

            // asumsi 1 utama pasti di m.matriks[m.Nbaris-1][m.Nkolom-2].
            result[m.Nbaris-1] = m.matriks[m.Nbaris-1][m.Nkolom-1];

            double temp;
            for(i = m.Nbaris-2; i >= 0; i--){
                temp = m.matriks[i][m.Nkolom-1]; j = 0;
                // cari satu utama, simpan indeks kolom di pivot
                while (m.matriks[i][j] == 0){
                    j++;
                }
                pivot = j;
                // kurang2in temp sama m[][] * result[][] sampe kolom ke-pivot
                j = m.Nkolom-2;
                while (j > pivot){
                    temp -= m.matriks[i][j] * result[j];
                    j--;
                }
                result[i] = temp;
            }
        return result;
    }
    public static double[] gaussJordanElim(Matriks m){
        /*
           mengembalikan hasil eliminasi Gauss-Jordan dalam bentuk matriks berukuran Nbaris x 1
           prereq: isUniqueSol(m) = true
         */
        double[] result = new double[m.Nbaris];
        EchelonRedux.selfReduce(m, true);
        // PENYULIHAN MUNDUR
        int i, j, pivot;

        // asumsi 1 utama pasti di m.matriks[m.Nbaris-1][m.Nkolom-2].
        result[m.Nbaris-1] = m.matriks[m.Nbaris-1][m.Nkolom-1];

        double temp;
        for(i = m.Nbaris-2; i >= 0; i--){
            temp = m.matriks[i][m.Nkolom-1]; j = 0;
            // cari satu utama, simpan indeks kolom di pivot
            while (m.matriks[i][j] == 0){
                j++;
            }
            pivot = j;
            // kurang2in temp sama m[][] * result[][] sampe kolom ke-pivot
            j = m.Nkolom-2;
            while (j > pivot){
                temp -= m.matriks[i][j] * result[j];
                j--;
            }
            result[i] = temp;
        }
        return result;
    }
    public static Matriks augment(Matriks a, Matriks b){
        /* membuat matriks augmented dari matriks a berukuran i x j dan b berukuran i x 0 */
        int i, j;
        Matriks m = new Matriks(a.Nbaris, a.Nbaris+1);
        for(i = 0; i < m.Nbaris; i++){
            for(j = 0;  j < m.Nkolom-1; j++){
                m.matriks[i][j] = a.matriks[i][j];
            }
        }
        for(i = 0; i < m.Nbaris; i++){
            m.matriks[i][m.Nkolom-1] = b.matriks[i][0];
        }
        return m;
    }
    public static String toParamEq(Matriks m){
        /* membuat string bentuk parametrik.
          m adalah matriks eselon baris yang memiliki solusi parametrik (isManySol(m) = true)
        */
        int i, j, k, realBarisCount;
        String solution = "<html>";
        String[] paramEq = new String[m.Nbaris];
        char paramVar = 'r';

        // cari mulai dari indeks berapa baris yang tidak semua elemennya nol
        boolean allZero;
        for(j = m.Nkolom-2; j >= 0; j--){
            allZero = true;
            for(i = m.Nbaris-1; i >= 0; i--){
                if (m.matriks[i][j] != 0){
                    allZero = false;
                    break;
                }
            }
            if (allZero || (m.matriks[i][j] != 1)){
                paramEq[j] = (((paramVar - 65) % 26) + 65) + "";
                paramVar++;
            }
        }
        realBarisCount = 0; i = 0; j = 0;
        boolean isZero;
        while (i < m.Nbaris) {
            isZero = true;
            while (isZero && j < m.Nkolom) {
                if (m.matriks[i][j] != 0) {
                    realBarisCount++;
                    isZero = false;
                }
                j++;
            }
            i++;
        }

        // hitungan
        for(i = 0; i < realBarisCount; i++){
            j = 0;
            while(m.matriks[i][j] != 1){
                j++;
            }
            paramEq[j] = "";
            if(j != m.Nkolom-2) {
                for(k = j + 1; k < m.Nkolom; k++){
                    // isi persamaan
                    if((paramEq[j] != null) && (paramEq[j].equals(""))){
                        if(k != m.Nkolom - 1){ // koefisien
                            if (m.matriks[i][k] < 0){
                                paramEq[j] += "+%.4f".formatted(m.matriks[i][k]) + paramEq[k];
                            } else if (m.matriks[i][k] > 0){
                                paramEq[j] += "-%.4f".formatted(m.matriks[i][k]) + paramEq[k];
                            }
                        } else { // konstanta
                            if (m.matriks[i][k] != 0){
                                paramEq[j] += "%.4f".formatted(m.matriks[i][k]);
                            }
                        }
                    } else {
                        if (k != m.Nkolom - 1){
                            if (m.matriks[i][k] < 0){
                                paramEq[j] += "+%.4f".formatted(m.matriks[i][k] * (-1)) + paramEq[k];
                            } else if (m.matriks[i][k] > 0) {
                                paramEq[j] += "-%.4f".formatted(m.matriks[i][k]) + paramEq[k];
                            }
                        } else {
                            if (m.matriks[i][k] > 0){
                                paramEq[j] += "+%.4f".formatted(m.matriks[i][k]);
                            } else if (m.matriks[i][k] < 0){
                                paramEq[j] += " %.4f".formatted(m.matriks[i][k]);
                            }
                        }
                    }
                }
            } else {
                paramEq[j] = "%.4f".formatted(m.matriks[i][m.Nkolom-1]);
            }
        }

        solution += "</html>";
        return solution;
    }
    public static String printSol (double[] solution, boolean toTxt){
        /* ubah solution ke bentuk string untuk file teks jika toTxt = true,
           output ke layar jika toTxt = false */
        String solutionString = toTxt? "" : "<html>";
        for(int i = 0; i < solution.length; i++){
            solutionString += "x%d = ".formatted(i+1);
            solutionString += "%.2f".formatted(solution[i]);
            if (i != solution.length-1){
                solutionString += (toTxt? "\n" : "<br/>");
            }
        }
        solutionString += toTxt? "" : "</html>";
        return solutionString;
    }
    public static boolean isNoSol(Matriks m) {
        /*
            mengecek apakah SPL dalam bentuk matriks augmented tidak punya penyelesaian
            SPL matriks tidak punya penyelesaian jika setelah direduksi baris terakhir terdapat
            nilai selain 0 pada kolom  [0..Nkolom-2] dan nilai 0 untuk kolom ke-[Nkolom-1].
            Prereq: matriks sudah berbentuk matriks eselon baris
        */
        boolean allZero = true; // cek [0..NKolom-2] di baris terakhir bernilai 0 semua
        int j = 0;
        while(allZero && (j < m.Nkolom-1)){
            if (m.matriks[m.Nbaris-1][j] != 0){
                allZero = false;
            }
            j++;
        }
        return allZero && (m.matriks[m.Nbaris-1][m.Nkolom-1] != 0);
    }
    public static boolean isManySol(Matriks m) {
        /*
            mengecek apakah SPL dalam bentuk matriks augmented punya banyak penyelesaian
            SPL matriks punya banyak penyelesaian jika setelah direduksi, semua elemen
            pada baris terakhir adalah nol.
            Prereq: matriks sudah berbentuk matriks eselon baris
        */
        boolean allZero = true; // cek [0..NKolom-2] di baris terakhir bernilai 0 semua
        int j = 0;
        while(allZero && (j < m.Nkolom-1)){
            if (m.matriks[m.Nbaris-1][j] != 0){
                allZero = false;
            }
            j++;
        }
        return (m.Nbaris + 1 < m.Nkolom) || (allZero && (m.matriks[m.Nbaris-1][m.Nkolom-1] == 0));
    }
    public static boolean isUniqueSol (Matriks m){
        /*
            mengecek apakah SPL dalam bentuk matriks augmented punya solusi unik.
            SPL matriks punya solusi unik jika tidak memenuhi kriteria matriks banyak solusi dan
            matriks tanpa solusi.
            Prereq: matriks sudah berbentuk matriks eselon baris
        */
        return (m.Nbaris + 1 == m.Nkolom) || !(isNoSol(m) || isManySol(m));
    }
}
