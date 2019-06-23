package pl.kkowalewski.board;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.kkowalewski.exception.BadFieldValueException;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final int MIN_RANGE = 0;
    public static final int MAX_RANGE = 9;

    private int value;
    private boolean isEmptyField;

    /*------------------------ METHODS REGION ------------------------*/
    public SudokuField() {

    }

    public SudokuField(final int value) {
        this.value = value;
    }

    public final int getFieldValue() {
        return this.value;
    }

    public final boolean isEmptyField() {
        return isEmptyField;
    }

    public final void setEmptyField() {
        isEmptyField = true;
    }

    /**
     * Method set value of the chosen field.
     *
     * @param value It it value that chosen field will be assigned
     */
    public final void setFieldValue(final int value) {
        if (value < MIN_RANGE || value > MAX_RANGE) {
            throw new BadFieldValueException("Must be <1,9>");
        }
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        return new EqualsBuilder()
                .append(value, ((SudokuField) o).value)
                .append(isEmptyField, ((SudokuField) o).isEmptyField)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .append(isEmptyField)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("isEmptyField", isEmptyField)
                .toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SudokuField sudokuField = new SudokuField();
        sudokuField.value = this.value;
        return sudokuField;
    }
}
