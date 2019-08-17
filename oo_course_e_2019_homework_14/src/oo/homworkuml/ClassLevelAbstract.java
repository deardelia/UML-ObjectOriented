package oo.homworkuml;

import com.oocourse.uml2.models.elements.UmlElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ClassLevelAbstract {
    private ArrayList<UmlElement> methods;
    private HashMap<String, ClassLevelAbstract> associationMap;
    private HashMap<String, UmlElement> selfAssiociations;
    private UmlElement curElement;
    private int typeInt;
    private ClassLevelAbstract parent;
    private HashSet<String> parents;
    private HashSet<String> allParents;
    private boolean recursiveInherit;

    public ClassLevelAbstract(UmlElement x) {
        this.curElement = x;
        this.methods =  new ArrayList<>();
        this.associationMap = new HashMap<>();
        this.selfAssiociations = new HashMap<>();
        this.typeInt = 1;
        this.parent = null;
        this.parents = new HashSet<>();
        this.allParents=  new HashSet<>();
        this.recursiveInherit = false;
    }

    public void setAllParents(String x) {
        allParents.add(x);
    }

    public void setRecursiveInherit() {
        recursiveInherit = true;
    }

    public boolean getRecursiveInherit() {
        return recursiveInherit;
    }


    public HashSet<String> getAllParents() {
        return allParents;
    }

    public HashSet<String> getParents() {
        return parents;
    }

    public void setParents(String x) {
        parents.add(x);
    }

    public void addMethods(UmlElement method) {
        methods.add(method);
    }

    public void setParent(ClassLevelAbstract x) {
        parent = x;
    }

    public void setAssociationMap(String assoId, ClassLevelAbstract a) {
        associationMap.put(assoId, a);
    }

    public void setSelfAssiociations(String assoId, UmlElement a) {
        selfAssiociations.put(assoId, a);
    }

    public void setTypeInt(int x) {
        typeInt = x;
    }

    public UmlElement getCurElement() {
        return curElement;
    }

    public int getTypeInt() {
        return typeInt;
    }

    public HashMap<String, ClassLevelAbstract> getAssociationMap() {
        return associationMap;
    }

    public HashMap<String, UmlElement> getSelfAssiociations() {
        return selfAssiociations;
    }

    public ArrayList<UmlElement> getMethods() {
        return methods;
    }

    public ClassLevelAbstract getParent() {
        return parent;
    }
}
