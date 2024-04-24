import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Satisfiabilité {

    static final int MAX = 100000;
    static List<List<Integer>> adjo;
    static List<List<Integer>> adjoInv;
    static boolean[] v = new boolean[MAX];
    static boolean[] vInv = new boolean[MAX];
    static Stack<Integer> s = new Stack<>();
    static int[] scc = new int[MAX];
    static int count = 1;

    static {
        adjo = new ArrayList<>(MAX);
        adjoInv = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            adjo.add(new ArrayList<>());
            adjoInv.add(new ArrayList<>());
        }
    }

    static void addArcs(int a, int b) {
        if (a >= 0 && a < adjo.size()) {
            adjo.get(a).add(b);
        } else {
            System.out.println("Indice hors limites: " + a);
        }
    }

    static void addArcsInverse(int a, int b) {
        if (a >= 0 && a < adjoInv.size()) {
            adjoInv.get(a).add(b);
        } else {
            System.out.println("Indice hors limites: " + a);
        }
    }


    static void dfs1(int u) {
        if (v[u])
            return;

        v[u] = true;

        for (int i = 0; i < adjo.get(u).size(); i++)
            dfs1(adjo.get(u).get(i));

        s.push(u);
    }

    static void dfs2(int u) {
        if (vInv[u])
            return;

        vInv[u] = true;

        for (int i = 0; i < adjoInv.get(u).size(); i++)
            dfs2(adjoInv.get(u).get(i));

        scc[u] = count;
    }

    static void Satisfiable(int n, int m, int[] a, int[] b) {
        for (int i = 0; i < m; i++) {
            if (a[i] > 0 && b[i] > 0) {
                addArcs(a[i] + n, b[i]);
                addArcsInverse(a[i] + n, b[i]);
                addArcs(b[i] + n, a[i]);
                addArcsInverse(b[i] + n, a[i]);
            } else if (a[i] > 0 && b[i] < 0) {
                addArcs(a[i] + n, n - b[i]);
                addArcsInverse(a[i] + n, n - b[i]);
                addArcs(-b[i], a[i]);
                addArcsInverse(-b[i], a[i]);
            } else if (a[i] < 0 && b[i] > 0) {
                addArcs(-a[i], b[i]);
                addArcsInverse(-a[i], b[i]);
                addArcs(b[i] + n, n - a[i]);
                addArcsInverse(b[i] + n, n - a[i]);
            } else {
                addArcs(-a[i], n - b[i]);
                addArcsInverse(-a[i], n - b[i]);
                addArcs(-b[i], n - a[i]);
                addArcsInverse(-b[i], n - a[i]);
            }
        }

        for (int i = 1; i <= 2 * n; i++)
            if (!v[i])
                dfs1(i);

        while (!s.isEmpty()) {
            int top = s.peek();
            s.pop();

            if (!vInv[top]) {
                dfs2(top);
                count++;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (scc[i] == scc[i + n]) {
                System.out.println("l'expression est non satisfaisable, une composante contient à la fois un littéral et son opposé.");
                return;
            }
        }

        System.out.println("\n\nl'expression est satisfaisable , aucune composante ne contient à la fois un littéral et son opposé.");
    }

    public void satis(String filename) throws Exception {
        Read lire = new Read();
        Digraph graph = lire.lire(filename);
        System.out.println(graph.toString());

        int V = graph.order();
        int N = lire.Nbr_Clauses(filename);

        System.out.println("\nNombre de clauses : " + N);

        List<Integer>[] g = constructGraph(graph, V, N, filename);

        System.out.println("\nLes composantes fortements connexes : ");
        Kosaraju k = new Kosaraju();
        List<List<Integer>> scComponents = k.getSC(g, filename);
        System.out.println(scComponents);
        System.out.println();

        ArrayList<Integer> c = lire.list1(filename);
        ArrayList<Integer> d = lire.list2(filename);
        int[] a = c.stream().mapToInt(i->i).toArray();
        int[] b = d.stream().mapToInt(i->i).toArray();

        Satisfiable(V, N, a, b);
    }

    private List<Integer>[] constructGraph(Digraph graph, int V, int N, String filename) throws Exception {
        List<Integer>[] g = new List[V +1];
        for (int i = 0; i < V +1; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < N * 2; i = i + 1) {
            if (graph.arc().get(i) < 0) {
                if (graph.arc2().get(i) < 0)
                    g[-graph.arc().get(i) + V / 2].add(-(graph.arc2().get(i)) + V / 2);
                else g[-graph.arc().get(i) + V / 2].add(graph.arc2().get(i));
            } else {
                if (graph.arc2().get(i) < 0)
                    g[graph.arc().get(i)].add(-(graph.arc2().get(i)) + V / 2);
                else g[graph.arc().get(i)].add(graph.arc2().get(i));
            }
        }
        return g;
    }
}
