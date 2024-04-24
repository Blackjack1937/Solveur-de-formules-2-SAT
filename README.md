
# Projet : Solveur de formules 2-SAT 

Ce projet vise à déterminer si une formule 2-SAT donnée est satisfaisable. Pour cela, il utilise l'algorithme de Kosaraju pour identifier les composantes fortement connexes du graphe associé à la formule.

## Classe : `Main`

- **Description** : Point d'entrée du programme.
- **Méthodes principales** :
    - `main(String[] args)`: Lit le fichier contenant la formule et lance le test de satisfaisabilité en utilisant la classe `Satisfiabilité`.

## Classe : `Digraph`

- **Description** : Représente un graphe orienté et fournit des méthodes pour sa manipulation. On utilise une archive (record) Arc pour répresenter les arcs du graphe.
- **Méthodes** :
    - `Digraph(int size)`: Constructeur pour initialiser le graphe avec une taille donnée.
    - `addArc(int source, int dest, int label)`: Ajoute un arc au graphe.
    - `arcs()`: Renvoie un itérable sur tous les arcs du graphe.
    - `arc()`, `arc2()`: Renvoient des listes de sources et de destinations d'arcs, respectivement.

## Classe : `Read`

- **Description** : Fournit des méthodes pour lire la formule 2-SAT, et l'insérer dans une instance de Digraph.
- **Méthodes** :
    - `lire(String filename)`: Extrait le graphe du fichier.
    - `list1(String filename)`, `list2(String filename)`: Extraient des listes de sources et de destinations d'arcs, respectivement.
    - `Y(String filename)`: Renvoie le nombre de clauses.

## Classe : `Kosaraju`

- **Description** : Implémente l'algorithme de Kosaraju pour identifier les composantes fortement connexes, en effectuant deux DFS (Depth-First Search), c'est-à-dire un parcours en profondeur.
- **Méthodes** :
    - `DFS(List<Integer>[] graph, int v, boolean[] visites, List<Integer> c)`: Effectue un parcours en profondeur.
    - `orderfiller(List<Integer>[] graph, boolean[] visites)`: Identifie l'ordre des sommets pour le deuxième DFS.
    - `getT(List<Integer>[] graph)`: Renvoie le graphe transposé.
    - `getSC(List<Integer>[] graph, String filename)`: Identifie et renvoie les composantes fortement connexes.

## Classe `Satisfiabilité`

- **Description** : Classe responsable de déterminer si une expression donnée est satisfaisable ou non en utilisant l'algorithme de Kosaraju.


- **Méthodes importantes** :

  - `addArcs(int a, int b)` : Ajoute un arc au graphe.
  - `addArcsInverse(int a, int b)` : Ajoute un arc inverse au graphe.
  - `dfs1(int u)` : Une fonction DFS pour parcourir le graphe.
  - `dfs2(int u)` : Une autre fonction DFS pour parcourir le graphe inverse.
  - `Satisfiable(int n, int m, int[] a, int[] b)` : Détermine si une expression est satisfaisable ou non.
  - `satis(String filename)` : La méthode principale qui sera appelée pour démarrer le processus.
