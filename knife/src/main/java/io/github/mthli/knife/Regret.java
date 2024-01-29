package io.github.mthli.knife;

import android.text.Editable;

/**
 * @author: WangKe
 * @date: 2024/1/29 0029
 */
public class Regret {
    private Editable editable;
    private int selectionEnd;

    public Regret(Editable editable, int selectionEnd) {
        this.editable = editable;
        this.selectionEnd = selectionEnd;
    }

    public Editable getEditable() {
        return editable;
    }

    public void setEditable(Editable editable) {
        this.editable = editable;
    }

    public int getSelectionEnd() {
        return selectionEnd;
    }

    public void setSelectionEnd(int selectionEnd) {
        this.selectionEnd = selectionEnd;
    }
}
