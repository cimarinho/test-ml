package br.com.ml.testml.business;

import br.com.ml.testml.exception.MutantException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MutantBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutantBusiness.class);

    private int countDna;
    private String wordSize;
    private char[][] verifyMutant;
    private static final int COLUMN_1 = 1;
    private static final int COLUMN_2 = 2;
    private static final int COLUMN_3 = 3;
    private static final int LINE_1 = 1;
    private static final int LINE_2 = 2;
    private static final int LINE_3 = 3;
    private Map<MutantType, MutantFound> mutantFound;
    private String[] dna;

    private MutantBusiness() {
        this.mutantFound = new HashMap<>();
        this.countDna = 0;
        this.mutantFound.put(MutantType.LINE, new MutantFound(false, 0, 0));
        this.mutantFound.put(MutantType.COLUMN, new MutantFound(false, 0, 0));
        this.mutantFound.put(MutantType.DIAGONAL, new MutantFound(false, 0, 0));
        this.mutantFound.put(MutantType.DIAGONALREVERSE, new MutantFound(false, 0, 0));
    }

    public static MutantBusiness getMutant(String[] dna) throws MutantException {
        isMutantValid(dna);
        int wordSize = dna[0].length();
        MutantBusiness mutant = new MutantBusiness();
        mutant.dna = dna;
        mutant.wordSize = dna[0];
        mutant.verifyMutant = new char[dna.length][wordSize];
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < wordSize; j++) {
                mutant.verifyMutant[i][j] = dna[i].charAt(j);
            }
        }
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
            this.initializeColumn();
            this.initializeDiagonal();
            this.initializeDiagonalReverse();
            this.initializeLine();
        }
        return false;
    }

    static void isMutantValid(String [] dna) throws MutantException {
        if (dna == null || dna[0] == null || dna.length <= 0) {
            throw new MutantException("Dna invalidate");
        }
        int dnaLength = dna[0].length();
        boolean[] ret = {true};
        for (int item = 0; item < dna.length; item++) {
            if (dna[item] == null || !Pattern.matches("^[ATCG]*", dna[item]) ||
                    dna[item].length() != dnaLength) {
                ret[0] = false;
                break;
            }
        }
        if (!ret[0]) {
            throw new MutantException("Dna invalidate");
        }
    }

    void lines(int itLine, int itColumn, int size) {
        if (!this.mutantFound.get(MutantType.LINE).found && (itColumn + COLUMN_3) < size) {
            if (sequenciaIgual(verifyMutant[itLine][itColumn],
                    verifyMutant[itLine][itColumn + COLUMN_1],
                    verifyMutant[itLine][itColumn + COLUMN_2],
                    verifyMutant[itLine][itColumn + COLUMN_3])) {
                LOGGER.debug("lines == {},{},{},{}\n", verifyMutant[itLine][itColumn], verifyMutant[itLine][itColumn + COLUMN_1], verifyMutant[itLine][itColumn + COLUMN_2], verifyMutant[itLine][itColumn + COLUMN_3]);
                countDna++;
                this.mutantFound.put(MutantType.LINE, new MutantFound(true, itLine, itColumn));
            }
        } else if (this.mutantFound.get(MutantType.LINE).found) {
            this.mutantFound.get(MutantType.LINE).count--;
            if (this.mutantFound.get(MutantType.LINE).count == 0) {
                this.mutantFound.put(MutantType.LINE, new MutantFound(false, 0, 0));
            }
        }
    }

    void diagonalReverse(int itLine, int itColumn) {
        if (!this.mutantFound.get(MutantType.DIAGONALREVERSE).found && (itLine - LINE_3) >= 0 && (itColumn - COLUMN_3) >= 0) {
            if (sequenciaIgual(verifyMutant[itLine][itColumn],
                    verifyMutant[itLine - LINE_1][itColumn - COLUMN_1],
                    verifyMutant[itLine - LINE_2][itColumn - COLUMN_2],
                    verifyMutant[itLine - LINE_3][itColumn - COLUMN_3])) {
                LOGGER.debug("diagnonalReverse == {},{},{},{}\n", verifyMutant[itLine][itColumn], verifyMutant[itLine - LINE_1][itColumn - COLUMN_1],
                        verifyMutant[itLine - LINE_2][itColumn - COLUMN_2], verifyMutant[itLine - LINE_3][itColumn - COLUMN_3]);
                this.countDna++;
                this.mutantFound.put(MutantType.DIAGONALREVERSE, new MutantFound(true, itLine, itColumn));
            }
        }
    }

    void diagonal(int itLine, int itColumn, int size) {
        if ((itLine + LINE_3) < size && (itColumn + COLUMN_3) < size) {
            if (sequenciaIgual(verifyMutant[itLine][itColumn],
                    verifyMutant[itLine + LINE_1][itColumn + COLUMN_1],
                    verifyMutant[itLine + LINE_2][itColumn + COLUMN_2],
                    verifyMutant[itLine + LINE_3][itColumn + COLUMN_3])) {
                LOGGER.debug("diagnonal == {},{},{},{}\n", verifyMutant[itLine][itColumn], verifyMutant[itLine + LINE_1][itColumn + COLUMN_1], verifyMutant[itLine + LINE_2][itColumn + COLUMN_2], verifyMutant[itLine + 3][itColumn + COLUMN_3]);
                this.countDna++;
                this.mutantFound.put(MutantType.DIAGONAL, new MutantFound(true, itLine, itColumn));
            }

        }
    }

    void column(int itLine, int itColumn, int size) {
        if ((itLine + LINE_3) < size) {
            if (sequenciaIgual(verifyMutant[itLine][itColumn],
                    verifyMutant[itLine + LINE_1][itColumn],
                    verifyMutant[itLine + LINE_2][itColumn],
                    verifyMutant[itLine + LINE_3][itColumn])) {
                LOGGER.debug("column == {},{},{},{}\n", verifyMutant[itLine][itColumn], verifyMutant[itLine + LINE_1][itColumn], verifyMutant[itLine + LINE_2][itColumn], verifyMutant[itLine + LINE_3][itColumn]);
                this.countDna++;
                this.mutantFound.put(MutantType.COLUMN, new MutantFound(true, itLine, itColumn));
            }
        }
    }

    void initializeLine() {
        this.mutantFound.put(MutantType.LINE, new MutantFound(false, 0, 0));
    }

    void initializeColumn() {
        this.mutantFound.get(MutantType.COLUMN).count--;
        if (this.mutantFound.get(MutantType.COLUMN).count == 0) {
            this.mutantFound.put(MutantType.COLUMN, new MutantFound(false, 0, 0));
        }
    }

    void initializeDiagonal() {
        this.mutantFound.get(MutantType.DIAGONAL).count--;
        if (this.mutantFound.get(MutantType.DIAGONAL).count == 0) {
            this.mutantFound.put(MutantType.DIAGONAL, new MutantFound(false, 0, 0));
        }
    }

    void initializeDiagonalReverse() {
        this.mutantFound.get(MutantType.DIAGONALREVERSE).count--;
        if (this.mutantFound.get(MutantType.DIAGONALREVERSE).count == 0) {
            this.mutantFound.put(MutantType.DIAGONALREVERSE, new MutantFound(false, 0, 0));
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

    private class MutantFound {
        MutantFound(boolean found, int line, int column) {
            this.found = found;
            this.line = line;
            this.column = column;
            this.column = 3;
        }

        boolean found;
        int line;
        int column;
        int count;
    }

    private enum MutantType {
        LINE, COLUMN, DIAGONALREVERSE, DIAGONAL
    }
}

