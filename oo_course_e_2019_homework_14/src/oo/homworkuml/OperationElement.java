package oo.homworkuml;

import com.oocourse.uml2.models.elements.UmlElement;

import java.util.ArrayList;

public class OperationElement {
    private UmlElement curOperation;
    private ArrayList<UmlElement> returnSub = new ArrayList<>();
    private ArrayList<UmlElement> paramSub = new ArrayList<>();
    private ArrayList<UmlElement> allSub = new ArrayList<>();

    public OperationElement(UmlElement x) {
        curOperation = x;
    }

    public ArrayList<UmlElement> getParamSub() {
        return paramSub;
    }

    public ArrayList<UmlElement> getReturnSub() {
        return returnSub;
    }

    public UmlElement getCurOperation() {
        return curOperation;
    }

    public void setAllSub(UmlElement x) {
        this.allSub.add(x);
    }

    public void setParamSub(UmlElement x) {
        this.paramSub.add(x);
    }

    public void setReturnSub(UmlElement x) {
        this.returnSub.add(x);
    }
}
