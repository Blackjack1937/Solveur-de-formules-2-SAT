import java.util.*;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class Digraph {

    record Arc(int source, int destination, int label) {}

    private final int cardinal;


    private final ArrayList<LinkedList<Arc>> incidency;

    public Digraph(int size) {
        cardinal = size;
        incidency = new ArrayList<>(size);
        for (int i = 0; i < cardinal; i++) {
            incidency.add(new LinkedList<>());
        }
    }

    public int order() {

        return cardinal;
    }

    public void addArc(int source, int dest, int label) throws Exception {
        if (Math.max(source, dest) >= this.cardinal) {
            throw new Exception(" L'indice du sommet est supérieur ou égal à cardinal, le nombre de sommets du graphe");
        }
        if (source < 0) {
            incidency.get(-source).addLast(new Arc(source, dest, label));
        } else {
            incidency.get(source).addLast(new Arc(source, dest, label));
        }

    }
    public ArrayList<Integer> arc() {
        ArrayList<Integer> arc = new ArrayList<>();
        for (Arc a : this.arcs()) {
            arc.add(a.source());
        }
        return arc;
    }

    public ArrayList<Integer> arc2() {
        ArrayList<Integer> arc2 = new ArrayList<>();
        for (Arc a : this.arcs()) {
            arc2.add(a.destination());
        }
        return arc2;
    }

    public Iterable<Arc> arcs() {
        return () -> new Iterator<>() {
            private int nextVertex = 0;
            private Iterator<Arc> currentList = Collections.emptyIterator();
            {
                goToNext();
            }

            private void goToNext() {
                while (nextVertex < Digraph.this.order() && !currentList.hasNext()) {
                    currentList = Digraph.this.incidency.get(nextVertex).iterator();
                    nextVertex++;
                }
            }

            public boolean hasNext() {
                return currentList.hasNext();
            }

            public Arc next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Arc arc = currentList.next();
                if (!currentList.hasNext()) {
                    goToNext();
                }
                return arc;
            }
        };
    }


    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append("Nombre de sommets : ").append(cardinal).append("\n");
        result.append("Sommets : ");
        for (int i = 1; i < cardinal / 2 + 1; i++) {
            result.append(i).append(" ").append(-i).append(" ");
        }
        result.append("\nArcs : \n");
        for (Arc a : this.arcs()) {
            result.append(a.source()).append(" -> ").append(a.destination()).append(", étiquette : ")
                    .append(a.label()).append("\n");
        }
        return result.toString();
    }





}
