public class Main {

    public static void main(String[] args) throws Exception {

        /* Dans cette étape, on va lire le fichier text pour en extraire la formule à tester **/

        String filename = "Formules/unsat6.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        /* Exécution du test de satisfiabilité **/

        Satisfiabilité l = new Satisfiabilité();
        l.satis(filename);

    }

}
