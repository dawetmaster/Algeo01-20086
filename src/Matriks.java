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

    // SPECIAL CONSTRUCTOR
    public static Matriks makeIdentity(int dimension) {
        //menghasilkan matriks identitas
        //prekondisi: nbaris=nkolom
        Matriks m = new Matriks(dimension, dimension);
        for (int i = 0; i < m.Nbaris; i++) {
            for (int j = 0; j < m.Nkolom; j++) {
                m.matriks[i][j] = (i == j) ? 1 : 0;
            }
        }
        return m;
    }

    // NORMALISASI MATRIKS
    public void normalize() {
        for (int i = 0; i < this.Nbaris; i++) {
            for (int j = 0; j < this.Nkolom; j++) {
                if (Double.compare(this.matriks[i][j], -0.0) == 0 && Double.compare(this.matriks[i][j], 0.0) == 0) {
                    this.matriks[i][j] = +0.0;
                }
            }
        }
    }

    // OBE
    // ROW SWAP
    public void swapRows(int r1, int r2) {
        double[] temp = this.matriks[r1];
        this.matriks[r1] = this.matriks[r2];
        this.matriks[r2] = temp;
    }

    // ROW REDUCTION
    public void rowReduce(int r1, int r2, int colidx) {
        // maksudnya r1 = r1 - mult * r2
        double mult = this.matriks[r1][colidx] / this.matriks[r2][colidx];
        for (int i = 0; i < this.Nkolom; i++) {
            this.matriks[r1][i] -= mult * this.matriks[r2][i];
        }
    }

    // REPRESENTASI STRING
    public String repr() throws NullPointerException {
        String result = "";
        for (int i = 0; i < this.Nbaris; i++) {
            for (int j = 0; j < this.Nkolom; j++) {
                result = result.concat(Double.toString(this.matriks[i][j]));
                if (j != this.Nkolom - 1) {
                    result = result.concat(" ");
                } else {
                    result = result.concat("\n");
                }
            }
        }
        return result;
    }

    public String repr_forIO() {
        //menkonversi matriks agar siap ditampilkan ke GUI
        String result = "";
        for (int i = 0; i < this.Nbaris; i++) {
            for (int j = 0; j < this.Nkolom; j++) {
                result = result.concat(Double.toString(this.matriks[i][j]));
                if (j != this.Nkolom - 1) {
                    result = result.concat(" ");
                } else {
                    result = result.concat("<br/>");
                }
            }
        }
        return result;
    }

    // I/O
    public static Matriks parseMatrix(String text, int NBaris, int NKolom) {
        Matriks mat = new Matriks(NBaris, NKolom);
        String[] test_line = text.split("\n");
        for (int i = 0; i < NBaris; i++) {
            String[] line_text = test_line[i].split(" ");
            for (int j = 0; j < NKolom; j++) {
                //    System.out.println(line_text[j]);
                mat.matriks[i][j] = Double.parseDouble(line_text[j]);
                //  System.out.println(mat.matriks[i][j]);
                mat.matriks[i][j] = Double.parseDouble(line_text[j]);
            }
        }
        return mat;
    }

    public void writeMatriks() {// Note: Belum dikonekin sama gui
        /* I.S. matriks terdefinisi */
        /* F.S. mencetak matriks ke layar */
        System.out.print(this.repr());
    }

    public String SimpanHasil() {
        String hasil = "";
        if (this.matriks == null) {
            hasil = "Tidak ada solusi atau solusi tak berhingga karena determinan matriks A adalah 0";
        } else {
            for (int i = 0; i < this.Nbaris; i++) {
                // System.out.println("Hasil:"+result.matriks[i][0]);
                hasil = hasil + "x" + (i + 1) + ":";
                hasil += this.matriks[i][0];
                hasil += "\n";
            }
        }
        return hasil;
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
    public boolean isSquare() {
        return (this.Nbaris == this.Nkolom);
    }

    public boolean isIdentity() {
        // menghasilkan true jika Matriks ini adalah matriks identitas,yakni elemen di
        // diagonal utama semuanya bernilai true
        // kamus lokal
        boolean isIdentity = this.isSquare();// menghasilkan true jika Matriks ini matriks identitas
        int i = 0, j = 0;// variabel looping
        // Algoritma
        while (i < this.Nbaris && isIdentity) {
            while (j < this.Nkolom && isIdentity) {
                isIdentity = Boolean.logicalAnd(
                        isIdentity,
                        (i == j ? Double.compare(this.matriks[i][j], 1.0) == 0 : this.matriks[i][j] == 0)
                );
                j++;
            }
            i++;
        }
        return isIdentity;
    }

    /* Fungsi dan Prosedur */
    // * WARNING : OBSOLETE FUNCTION
    public static Matriks kali(Matriks m1, Matriks m2) {
        // fungsi yang melakukan perkalian matriks antara m1 dan m2
        // prekondisi: m1.NBaris = m2.NKolom
        Matriks mat = new Matriks(m1.Nbaris, m2.Nkolom);
        for (int i = 0; i < mat.Nbaris; i++) {
            for (int j = 0; j < mat.Nkolom; j++) {
                double sum_ = 0;
                for (int k = 0; k < m1.Nkolom; k++) {
                    //System.out.println(m1.matriks[i][k]);
                    // System.out.println(m2.matriks[k][j]+"\n");
                    sum_ += (m1.matriks[i][k] * m2.matriks[k][j]);
                    //   System.out.println(sum_);
                }
                mat.matriks[i][j] = sum_;
                // System.out.println();
            }
        }
        return mat;
    }

    // KALI SKALAR
    public Matriks scalarMult(double x) {
        Matriks result = this.cloneMatriks();
        for (int i = 0; i < this.Nbaris; i++) {
            for (int j = 0; j < this.Nkolom; j++) {
                result.matriks[i][j] *= x;
            }
        }
        return result;
    }

    public double determinantReduction() {
        // TODO: FIXKAN SEGERA
        Matriks mat = this.cloneMatriks();
        /* mulai prosedur */
        if (mat.isSquare()) {
            if (mat.Nbaris == 1) {
                return mat.matriks[0][0];
            } else {
                double result = 1;
                boolean positive = true;
                int size = mat.Nbaris;
                for (int col = 0; col < size; col++) {
                    int row = col;
                    if (mat.matriks[row][col] == 0) {
                        int pivot = row + 1;
                        while (pivot < size && mat.matriks[pivot][col] == 0) {
                            ++pivot;
                        } // pivot = size || mat.matriks[pivot][col] != 0
                        if (pivot < size) {
                            mat.swapRows(row, pivot);
                            positive = !positive;
                        } else {
                            continue;
                        }
                    }
                    for (int i = row + 1; i < size; i++) {
                        mat.rowReduce(i, row, col);
                    }
                }
                mat.normalize();
                for (int i = 0; i < size; i++) {
                    result *= mat.matriks[i][i];
                }
                result *= positive ? 1 : -1;
                return result;
            }
        } else {
            return Double.NaN;
        }
    }

    public Matriks submatrix(int row, int col) {
        if (this.isSquare()) {
            int size = this.Nbaris - 1;
            Matriks submatrix = new Matriks(size, size);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    submatrix.matriks[i][j] = this.matriks[i < row ? i : i + 1][j < col ? j : j + 1];
                }
            }
            return submatrix;
        } else {
            return null;
        }
    }

    public double minor(int row, int col) {
        if (this.isSquare()) {
            return this.submatrix(row, col).determinantReduction();
        } else {
            return Double.NaN;
        }
    }

    public Matriks cofactor() {
        if (this.isSquare()) {
            Matriks cofactor = new Matriks(this.Nbaris, this.Nkolom);
            for (int i = 0; i < this.Nbaris; i++) {
                for (int j = 0; j < this.Nkolom; j++) {
                    cofactor.matriks[i][j] = this.minor(i, j) * ((i + j) % 2 == 0 ? 1 : -1);
                }
            }
            cofactor.normalize();
            return cofactor;
        } else {
            throw new ArithmeticException("Matriks tidak persegi!");
        }
    }

    // TRANSPOSE
    public Matriks transpose() throws NullPointerException {
        Matriks result = new Matriks(this.Nkolom, this.Nbaris);
        for (int i = 0; i < result.Nbaris; i++) {
            for (int j = 0; j < result.Nkolom; j++) {
                result.matriks[i][j] = this.matriks[j][i];
            }
        }
        return result;
    }

    public double determinantCofactor() {
        if (this.isSquare()) {
            // BASIS
            if (this.Nbaris == 1) {
                return this.matriks[0][0];
            }
            // REKURENS
            else {
                double result = 0;
                for (int i = 0; i < this.Nbaris; i++) {
                    if (this.matriks[i][0] != 0) {
                        result += (this.matriks[i][0] * this.submatrix(i, 0).determinantCofactor()) * (i % 2 == 0 ? 1 : -1);
                    }
                }
                if (Double.compare(result, 0.0) == 0 || Double.compare(result, -0.0) == 0) {
                    result = 0;
                }
                return result;
            }
        } else {
            return Double.NaN;
        }
    }
}
