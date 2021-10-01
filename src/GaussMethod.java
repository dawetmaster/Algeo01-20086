import java.lang.*;

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
        Matriks m = new Matriks(a.Nbaris, a.Nkolom+1);
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
           m adalah matriks eselon baris yang memiliki solusi parametrik (isManySol(m) = true) */
        int i, j, k, realBarisCount;
        Matriks tempMatrix = m.cloneMatriks();
        char[] varList = new char[m.Nkolom-1];

        boolean allZero; realBarisCount = 0;
        for(i = 0; i < m.Nbaris; i++){ // hitung baris tanpa 1 utama
            allZero = true;
            for(j = 0; j < m.Nkolom; j++){
                if (m.matriks[i][j] != 0){
                    allZero = false;
                    break;
                }
            }
            if (!allZero) realBarisCount++;
        }

        // inisialisasi daftar variabel bebas
        for(i = 0; i < realBarisCount; i++){
            varList[i] = 'x';
        }
        char tempVar = 'r';
        for(i = realBarisCount; i < varList.length; i++){
            // buat daftar variabel bebas, asumsi semua alfabet bisa masuk
            // dari 'r', 's', ..., 'p', kecuali 'x'
            varList[i] = tempVar;
            tempVar++;
            if (tempVar == 'x') {
                tempVar++;
            } else if (tempVar == 'z') tempVar = 'a';
        }

        /*int[] oneUtamaIdx = new int[realBarisCount];
        for(i = realBarisCount-1; i >= 0; i--){
            // operasi membentuk persamaan x = ..., dengan ganti tanda elemen setelah 1 utama
            j = 0; // cari satu utama di baris i
            while(tempMatrix.matriks[i][j] == 0){
                j++;
            }
            oneUtamaIdx[i] = j;
            // setelah dapet 1 utama, dilawanin simbolnya, supaya membentuk x(i+1) = ...
            for(k = j+1; k < tempMatrix.Nkolom; k++){
                tempMatrix.matriks[i][k] *= -1;
            }
        }

        for(i = realBarisCount-2; i >= 0; i--){ // operasi masuk2in yang di bawah ke persamaan
            for(k = oneUtamaIdx[i]+2; k < tempMatrix.Nkolom; k++){
                tempMatrix.matriks[i][k] = tempMatrix.matriks[i][k]
                        + (tempMatrix.matriks[i+1][k] * tempMatrix.matriks[i][oneUtamaIdx[i]+1]);
            }
            tempMatrix.matriks[i][oneUtamaIdx[i]+1] = 0; // depannya nol-in
        }*/

        /*for(i = 0; i < realBarisCount; i++){
            solution += "%c%d = ".formatted(varList[i], i+1);
            j = 0; // cari satu utama di baris i
            while(tempMatrix.matriks[i][j] == 0){
                j++;
            } // 1 utama di j
            for(k = j+1; k < tempMatrix.Nkolom; j++){
                solution += "%.4f%c".formatted(tempMatrix.matriks[i][k], varList[k]);
            }
            solution += "<br/>";
        }
        for(i = realBarisCount; i < m.Nbaris; i++){
            solution += "%c".formatted(varList[i]);
            if (i < m.Nbaris - 1) solution += ",";
        }
        solution += "ϵ R";*/

        String solution = "";
        // test
        for(i = 0; i < varList.length; i++){
            solution += "%c".formatted(varList[i]);
            if (varList[i] == 'x') solution += "%d".formatted(i+1);
            if (i < varList.length) solution += ", ";
        }
        // endtest
        solution += "ϵ R";

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
        return (m.Nbaris + 1 == m.Nkolom) && !(isNoSol(m) || isManySol(m));
    }
}
