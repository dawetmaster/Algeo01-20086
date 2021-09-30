public class GaussMethod {
    public static double[] gaussElim(Matriks m){
        /*
           mengembalikan hasil eliminasi Gauss dalam bentuk matriks berukuran Nbaris x 1
         */
        // caution: masih dibuat dengan asumsi matriks berukuran n,n+1. TODO: buat secara umum

        double[] result = new double[m.Nbaris];

        EchelonRedux.selfReduce(m); // buat m menjadi matriks eselon
        // if (isUniqueSol(m)){ // uncomment kalau sudah resolved

            // PENYULINGAN MUNDUR
            int i, j, pivot;
            // cari nilai awal
            /*
                boolean check = false; j = 0;
                while(!check && j < m.Nkolom-1){
                    if (m.matriks[m.Nbaris-1][j] == 0){
                        j++;
                    } else {
                        check = true;
                    }
                }
                result.matriks[m.Nbaris-1][0] = m.matriks[m.Nbaris-1][m.Nkolom-1]/m.matriks[m.Nbaris-1][j];
            */

            // asumsi 1 utama pasti di m.matriks[m.Nbaris-1][m.Nkolom-2]. TODO: hilangkan asumsi, kalo perlu?
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

        // }
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
    public static boolean isUniqueSol (Matriks m){
        /*
            mengecek apakah SPL dalam bentuk matriks augmented punya solusi unik.
            SPL matriks punya solusi unik jika tidak memenuhi kriteria matriks banyak solusi dan
            matriks tanpa solusi.
            Prereq: matriks sudah berbentuk matriks eselon baris
        */
        return !isNoSol(m) && !isManySol(m);
    }
    public static boolean isNoSol(Matriks m) {
        /*
            mengecek apakah SPL dalam bentuk matriks augmented tidak punya penyelesaian
            SPL matriks tidak punya penyelesaian jika setelah direduksi baris terakhir terdapat
            nilai selain 0 pada kolom  [0..Nkolom-2] dan nilai 0 untuk kolom ke-[Nkolom-1].
            Prereq: matriks sudah berbentuk matriks eselon baris
        */
        boolean allZero = true; // cek [0..NKolom-2] bernilai 0 semua
        int j = 0;
        while(allZero && (j < /*m.Nbaris-1*/m.Nkolom-1)){
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
        boolean allZero = true; // cek [0..NKolom-2] bernilai 0 semua
        int j = 0;
        while(allZero && (j < /*m.Nbaris-1*/m.Nkolom-1)){
            if (m.matriks[m.Nbaris-1][j] != 0){
                allZero = false;
            }
            j++;
        }
        return allZero && (m.matriks[m.Nbaris-1][m.Nkolom-1] == 0) || (m.Nbaris + 1 < m.Nkolom);
    }
}
