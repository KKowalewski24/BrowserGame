package pl.kkowalewski.board;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.kkowalewski.exception.BadGroupSizeException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuFieldGroup implements Cloneable, Serializable {

    /*------------------------ FIELDS REGION ------------------------*/
    private List<SudokuField> fields;

    /*------------------------ METHODS REGION ------------------------*/

    /**
     * Constructor of the class.
     *
     * @param fields list of the SudokuField objects
     */
    public SudokuFieldGroup(final List<SudokuField> fields) {
        if (fields.size() != SudokuBoard.SIZE) {
            throw new BadGroupSizeException("Length must be equals 9");
        }
        this.fields = fields;
    }

    /**
     * Method verify correctness.
     *
     * @return boolean - if verify successful then true
     */
    public final boolean verify() {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            for (int i2 = i + 1; i2 < SudokuBoard.SIZE; i2++) {
                if (fields.get(i).getFieldValue() == fields.get(i2).getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Method return unmodifiable list of SudokuField objects.
     *
     * @return unmodifiable list
     */
    public final List<SudokuField> getSudokuFieldList() {
        return Collections.unmodifiableList(fields);
    }

    /**
     * Method return list of fields.
     *
     * @return List of Integers converted from list of SudokuField
     */
    public final List<Integer> getFields() {
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            valueList.add(fields.get(i).getFieldValue());
        }

        return valueList;
    }

    @Override
    public boolean equals(final Object o) {
        return new EqualsBuilder()
                .append(fields, ((SudokuFieldGroup) o).fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }
}
