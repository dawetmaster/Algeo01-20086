public class Matriks {
    /* ATRIBUT */
    int Nbaris;
    int Nkolom;
    double[][] matriks;

    /* METHOD */

    // konstruktor
    public Matriks(int nbaris, int nkolom) {
        /* Kontruktor matriks */
        this.Nbaris = nbaris;
        this.Nkolom = nkolom;
        this.matriks = new double[Nbaris][Nkolom];
        // inisialisasi matriks
        for (int i = 0; i < this.Nbaris; i++)
            for (int j = 0; j < this.Nkolom; j++)
                matriks[i][j] = 0;
    }
    // Input/Output
    /*
     * NOTE: Belum bikin input dari GUI nya public void readMatriks() {
     */
    /* I.S. semua elemen matriks bernilai 0 */
    /* F.S. Matriks terisi elemen */
    /*
     * for (int i = 0; i < this.Nbaris; i++) for (int j = 0; j < this.Nkolom; j++)
     * //NOTE:isi dengan input matriks[i][j] = 0; }
     */

    public void writeMatriks() {// Note: Belum dikonekin sama gui
        /* I.S. matriks terdefinisi */
        /* F.S. mencetak matriks ke layar */
        for (int i = 0; i < this.Nbaris; i++) {
            for (int j = 0; j < this.Nkolom; j++)
                System.out.print(matriks[i][j] + " ");
            System.out.println();
        }
    }

    /* Copy/Kloning matriks */
    public Matriks cloneMatriks() {
        Matriks m = new Matriks(this.Nbaris, this.Nkolom);
        for (int i = 0; i < m.Nbaris; i++) {
            m.matriks[i] = this.matriks[i].clone();
        }
        return m;
    }

    /* Predikat */
    public boolean isIdentity() {
        // menghasilkan true jika Matriks ini adalah matriks identitas,yakni elemen di
        // diagonal utama semuanya bernilai true
        // kamus lokal
        boolean isIdentity;// menghasilkan true jika Matriks ini matriks identitas
        int i, j;// variabel looping
        // Algoritma
        isIdentity = true;
        i = 0;
        j = 0;
        while (i < this.Nbaris && isIdentity) {
            while (j < this.Nkolom && isIdentity) {
                if (i == j) {// untuk elemen diagonal utama
                    if (Double.compare(this.matriks[i][j], 1.0) != 0)// kalau nilai nya sama dengan 0 berarti nilai si
                                                                     // elemen matriksnya 1. Ada di dokumentasi
                                                                     // fungsinya
                        isIdentity = false;
                } else {
                    if (this.matriks[i][j] != 0) {
                        isIdentity = false;
                    }
                }
                j++;
            }
            i++;
        }
        return isIdentity;
    }

    public Matriks plusMinusMatriks(Matriks m1, Matriks m2, boolean isPlus) {
        // m1.Nbaris == m2.Nbaris dan m1.Nkolom = m2.Nkolom
        // return m1 + m2 jika isPlus = true, dan m1 - m2 jika isPlus = false.
        Matriks m = new Matriks(m1.Nbaris, m1.Nkolom);
        if (isPlus) {
            for (int i = 0; i < m1.Nbaris; i++) {
                for (int j = 0; j < m1.Nkolom; j++) {
                    m.matriks[i][j] = m1.matriks[i][j] + m2.matriks[i][j];
                }
            }
        } else { /* isPlus = false */
            for (int i = 0; i < m1.Nbaris; i++) {
                for (int j = 0; j < m1.Nkolom; j++) {
                    m.matriks[i][j] = m1.matriks[i][j] - m2.matriks[i][j];
                }
            }
        }
        return m;
    }

    /* Fungsi dan Prosedur */
    // * TODO: cek fungsi ini bila sudah ada fungsi untuk input
    public static Matriks kali(Matriks m1, Matriks m2) {
        // fungsi yang melakukan perkalian matriks antara m1 dan m2
        // prekondisi: m1.NBaris = m2.NKolom
        Matriks mat = new Matriks(m1.Nbaris, m2.Nkolom);
        for (int i = 0; i < mat.Nbaris; i++) {
            for (int j = 0; j < mat.Nkolom; j++) {
                double sum_ = 0;
                for (int k = 0; k < m1.Nkolom; k++)
                    sum_ += (m1.matriks[i][k] * m2.matriks[k][j]);
                mat.matriks[i][j] = sum_;
            }
        }
        return mat;
    }

    public double determinantReduction() {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        /* kloning matriks dulu */
        Matriks m = this.cloneMatriks();
        /* mulai prosedur */
        if (m.Nbaris == m.Nkolom) {
            double multiplier;
            int swaps = 0;
            // Start dari kolom pertama
            for (int col = 0; col < m.Nkolom; col++) {
                // cek apakah m.matriks[col][col] == 0
                if (m.matriks[col][col] == 0) {
                    // cek baris selanjutnya, lalu tukarkan
                    int row_swap_target = -1; /* anggap swap target belum ketemu */
                    for (int i = col + 1; i < m.Nbaris; i++) {
                        if (m.matriks[i][col] != 0) {
                            row_swap_target = i;
                            break;
                        }
                    }
                    if (row_swap_target != -1) {
                        ++swaps;
                        double[] temp = m.matriks[col];
                        m.matriks[col] = m.matriks[row_swap_target];
                        m.matriks[row_swap_target] = temp;
                    } else {
                        // satu kolom isinya nol semua, jadi determinannya nol
                        return 0;
                    }
                } else {
                    // Loop baris
                    for (int row = col + 1; row < m.Nbaris; row++) {
                        multiplier = m.matriks[row][col] / m.matriks[col][col];
                        // Loop pengurangan dari kolom pertama sampai kolom terakhir
                        for (int i = 0; i < m.Nkolom; i++) {
                            m.matriks[row][i] = m.matriks[row][i] - multiplier * m.matriks[col][i];
                        }
                    }
                }
            }
            // hitung hasil
            double result = 1;
            for (int i = 0; i < m.Nbaris; i++) {
                result *= m.matriks[i][i];
            }
            return result;
        } else {
            return Double.NaN;
        }
    }

    public Matriks minor(int row, int col) {
        if (this.Nbaris == this.Nkolom) {
            int dimension = this.Nbaris - 1;
            Matriks minor = new Matriks(dimension, dimension);
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    minor.matriks[i][j] = this.matriks[i >= row ? i + 1 : i][j >= col ? j + 1 : j];
                }
            }
            return minor;
        } else {
            return null;
        }
    }

    public double determinantCofactor() {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        /* Pakai default value, kolom pertama (index = 0) */
        if (this.Nbaris == this.Nkolom) {
            // algoritma ekspansi kofaktor dengan cara rekursif
            // basis
            if (this.Nbaris == 2) {
                return (this.matriks[0][0] * this.matriks[1][1] - this.matriks[1][0] * this.matriks[0][1]);
            } else {
                // rekurens
                double determinant = 0;
                for (int i = 0; i < this.Nbaris; i++) {
                    determinant += Math.pow(-1, i) * this.matriks[i][0] * this.minor(i, 0).determinantCofactor();
                }
                return determinant;
            }
        } else {
            return Double.NaN;
        }
    }

    public double determinantCofactor(int mat_index, boolean row_mode) {
        /* I.S. jumlah baris dan jumlah kolom harus sama */
        /* F.S. diperoleh determinan */
        /* Prekondisi: ada input mat_index dan row_mode */
        if (this.Nbaris == this.Nkolom) {
            // algoritma ekspansi kofaktor dengan cara rekursif
            // basis
            if (this.Nbaris == 2) {
                return (this.matriks[0][0] * this.matriks[1][1] - this.matriks[1][0] * this.matriks[0][1]);
            } else {
                // rekurens
                double determinant = 0;
                if (row_mode) {
                    for (int i = 0; i < this.Nkolom; i++) {
                        determinant += Math.pow(-1, i) * this.matriks[mat_index][i] * this.minor(mat_index, i).determinantCofactor();
                    }
                } else {
                    for (int i = 0; i < this.Nbaris; i++) {
                        determinant += Math.pow(-1, i) * this.matriks[i][mat_index] * this.minor(i, mat_index).determinantCofactor();
                    }
                }
                return determinant;
            }
        } else {
            return Double.NaN;
        }
    }
}
