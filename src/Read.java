import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Read {
    public Digraph lire(String filename) throws Exception{
        File file = new File(filename);

        BufferedReader br
                = new BufferedReader(new FileReader(file));


        Digraph graph = new Digraph(0);
        String st;
        int line=0;
        int var1;
        int var2;
        int var3=0;
        int label=-2;

        while((st = br.readLine()) != null) {
            if (line==1) {
                var1=Integer.parseInt(st.split(" ")[2]);
                var2=Integer.parseInt(st.split(" ")[3]);
                var3=var2*2+3;
                graph = new Digraph(var1*2);
            }
            if (line>1){
                int var11 = Integer.parseInt(st.split(" ")[0]);
                int var22 = Integer.parseInt(st.split(" ")[1]);

                graph.addArc(-var11, var22, label+1);
                graph.addArc(-var22, var11, var3-2);
            }
            line+=1;
            label+=1;
            var3-=1;
        }
        return graph;

    }



    public ArrayList list1(String filename) throws IOException {
        File file = new File(filename);


        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int line=0;
        ArrayList<Integer> arclist = new ArrayList<>();

        while((st = br.readLine()) != null) {
            if (line>1){
                int var1 = Integer.parseInt(st.split(" ")[0]);
                arclist.add(var1);
            }
            line+=1;
        }
        return arclist;
    }
    public ArrayList list2(String filename) throws IOException {
        File file = new File(filename);


        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        int line=0;
        ArrayList<Integer> arclist2= new ArrayList<>();
        while((st = br.readLine()) != null) {
            if (line>1){
                int var2 = Integer.parseInt(st.split(" ")[1]);
                arclist2.add(var2);
            }
            line+=1;
        }
        return arclist2;

    }
    public int Nbr_Clauses(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        int line=0;
        int N=0;
        while((st = br.readLine()) != null) {

            if (line==1) {
                N= Integer.parseInt(st.split(" ")[3]);
            }
            line+=1;
        }
        return N;
    }


}

