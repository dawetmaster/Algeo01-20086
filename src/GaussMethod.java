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
    public static boolean isUniqueSol (Matriks m){
        /*
            mengecek apakah SPL dalam bentuk matriks augmented tidak punya penyelesaian
            SPL matriks tidak punya penyelesaian jika setelah direduksi baris terakhir terdapat
            nilai selain 0 pada kolom  [0..Nkolom-2] dan nilai 0 untuk kolom ke-[Nkolom-1]
            Prereq: matriks sudah tereduksi
            TODO: resolve bug
        */
        boolean allZero = true; // cek [0..NKolom-2] bernilai 0 semua
        int j = 0;
        while(allZero && (j < m.Nbaris-1)){
            if (m.matriks[m.Nbaris-1][j] != 0){
                allZero = false;
            }
            j++;
        }
        boolean noSol = allZero && (m.matriks[m.Nbaris-1][m.Nkolom-1] != 0);
        boolean manySol = allZero && (m.matriks[m.Nbaris-1][m.Nkolom-1] == 0);
        return !noSol && !manySol;
    }
}
