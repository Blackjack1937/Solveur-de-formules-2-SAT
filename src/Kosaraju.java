import java.util.*;

/** class Kosaraju **/
public class Kosaraju {
    /** Fonction qui exécute le parcours en profondeur (Depth First Search DFS) **/
    public void DFS(List<Integer>[] graph, int v, boolean[] visites, List<Integer> c)
    {
        visites[v] = true;
        for (int i = 0; i < graph[v].size(); i++)
            if (!visites[graph[v].get(i)])
                DFS(graph, graph[v].get(i), visites, c);
        c.add(v);
    }
    /**Parcours tous les sommets accessibles, ajoutant chaque sommet à la liste order à la fin de sa visite.**/
    public List<Integer> orderfiller(List<Integer>[] graph, boolean[] visites)
    {
        int V = graph.length;
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < V; i++)
            if (!visites[i])
                DFS(graph, i, visites, order);
        return order;
    }
    /** Fonction pour obtenir la transposée du graph dirigé **/
    public List<Integer>[] getT(List<Integer>[] graph)
    {
        int V = graph.length;
        List<Integer>[] g = new List[V];
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<>();
        for (int v = 0; v < V; v++)
            for (int i = 0; i < graph[v].size(); i++)
                g[graph[v].get(i)].add(v);
        return g;
    }
    /** Fonction pour obtenir les composantes fortement connexes **/
    public List<List<Integer>> getSC(List<Integer>[] graph, String filename) throws Exception {
        int V = graph.length;
        boolean[] visites = new boolean[V];
        List<Integer> order = orderfiller(graph, visites);
        /* transposée du graph **/
        List<Integer>[] reverseGraph = getT(graph);
        visites = new boolean[V];
        Collections.reverse(order);

        /* les composantes fortement connexes **/
        List<List<Integer>> SCCc = new ArrayList<>();
        for (int v : order) {
            if (!visites[v]) {
                List<Integer> c = new ArrayList<>();
                DFS(reverseGraph, v, visites, c);
                SCCc.add(c);
            }
        }
        Read lire = new Read();
        List<List<Integer>> SCC = new ArrayList<>(SCCc);
        for(int i=0;i<SCCc.size();i++){
            for (int j=0;j<SCCc.get(i).size();j++){
                if (SCCc.get(i).get(j)>lire.lire(filename).order()/2) {
                    SCC.get(i).set(j, -(SCCc.get(i).get(j) - lire.lire(filename).order()/2));
                }
                else {
                    SCC.get(i).set(j, SCCc.get(i).get(j));
                }
            }
        }
        return SCC;
    }


}