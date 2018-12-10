package br.com.ml.testml.business;

import br.com.ml.testml.exception.MutantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Pattern;

public class MutantBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutantBusiness.class);

    private boolean achouLinha;
    private int contadorLinha;
    private int countDna;
    private String wordSize;
    private char[][] verifyMutant;
    private static final int COLUMN_1 = 1;
    private static final int COLUMN_2 = 2;
    private static final int COLUMN_3 = 3;
    private static final int LINE_1 = 1;
    private static final int LINE_2 = 2;
    private static final int LINE_3 = 3;

    private String[] dna;

    private MutantBusiness() {
        this.achouLinha = true;
        this.contadorLinha = 3;
        this.countDna = 0;
    }

    public static MutantBusiness getMutant(String[] dna) throws MutantException {
        MutantBusiness mutant = new MutantBusiness();
        mutant.dna = dna;
        mutant.wordSize = dna[0];
        mutant.isMutantValid();
        return mutant;
    }

    public boolean isMutant() {
        for (int itLine = 0; itLine < dna.length; itLine++) {
            for (int itColumn = 0; itColumn < wordSize.length(); itColumn++) {
                this.lines(itLine, itColumn, wordSize.length());
                this.column(itLine, itColumn, dna.length);
                this.diagonal(itLine, itColumn, dna.length);
                this.diagonalReverse(itLine, itColumn);
            }
            if (countDna >= 2) {
                return true;
            }
        }
        return false;
    }

    void isMutantValid() throws MutantException {
        boolean[] ret = {true};
        Arrays.stream(dna).forEach(item -> {
            if (!Pattern.matches("^[ATCG]*", item)) {
                ret[0] = false;
            }
        });
        this.verifyMutant = new char[dna.length][wordSize.length()];
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < wordSize.length(); j++) {
                verifyMutant[i][j] = dna[i].charAt(j);
            }
        }
        if (!ret[0]) {
            throw new MutantException();
        }
    }

    void lines(int itLine, int itColumn, int size) {
        if (achouLinha && (itColumn + 3) < size) {
            if (sequenciaIgual( verifyMutant[itLine][itColumn],
                                verifyMutant[itLine][itColumn + COLUMN_1],
                                verifyMutant[itLine][itColumn + COLUMN_2],
                                verifyMutant[itLine][itColumn + COLUMN_3])) {
                LOGGER.debug("lines == %c,%c,%c,%c\n", verifyMutant[itLine][itColumn], verifyMutant[itLine][itColumn + COLUMN_1], verifyMutant[itLine][itColumn + COLUMN_2], verifyMutant[itLine][itColumn + COLUMN_3]);
                countDna++;
                achouLinha = false;
            }
        } else if (!achouLinha) {
            contadorLinha--;
            if (contadorLinha == 0) {
                achouLinha = true;
            }
        }
    }

    void diagonalReverse(int itLine, int itColumn) {
        if ((itLine - 3) >= 0 && (itColumn - 3) >= 0) {
            if (sequenciaIgual( verifyMutant[itLine][itColumn],
                                verifyMutant[itLine - LINE_1][itColumn - COLUMN_1],
                                verifyMutant[itLine - LINE_2][itColumn - COLUMN_2],
                                verifyMutant[itLine - LINE_3][itColumn - COLUMN_3])) {
                LOGGER.debug("diagnonalReverse == %c,%c,%c,%c\n", verifyMutant[itLine][itColumn], verifyMutant[itLine - LINE_1][itColumn - COLUMN_1],
                        verifyMutant[itLine - LINE_2][itColumn - COLUMN_2], verifyMutant[itLine - LINE_3][itColumn - COLUMN_3]);
                this.countDna++;
            }
        }
    }

    void diagonal(int itLine, int itColumn, int size) {
        if ((itLine + 3) < size && (itColumn + 3) < size) {
            if (sequenciaIgual( verifyMutant[itLine][itColumn],
                                verifyMutant[itLine + LINE_1][itColumn + COLUMN_1],
                                verifyMutant[itLine + LINE_2][itColumn + COLUMN_2],
                                verifyMutant[itLine + LINE_3][itColumn + COLUMN_3])) {
                LOGGER.debug("diagnonal == %c,%c,%c,%c\n", verifyMutant[itLine][itColumn], verifyMutant[itLine + LINE_1][itColumn + COLUMN_1], verifyMutant[itLine + LINE_2][itColumn + COLUMN_2], verifyMutant[itLine + 3][itColumn + COLUMN_3]);
                this.countDna++;
            }
        }
    }

    void column(int itLine, int itColumn, int size) {
        if ((itLine + 3) < size) {
            if (sequenciaIgual( verifyMutant[itLine][itColumn],
                                verifyMutant[itLine + LINE_1][itColumn],
                                verifyMutant[itLine + LINE_2][itColumn],
                                verifyMutant[itLine + LINE_3][itColumn])) {
                LOGGER.debug("column == %c,%c,%c,%c\n", verifyMutant[itLine][itColumn], verifyMutant[itLine + LINE_1][itColumn], verifyMutant[itLine + LINE_2][itColumn], verifyMutant[itLine + LINE_3][itColumn]);
                this.countDna++;
            }
        }
    }

    boolean sequenciaIgual(char... sequence) {
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] != sequence[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public int getCountDna() {
        return countDna;
    }
}