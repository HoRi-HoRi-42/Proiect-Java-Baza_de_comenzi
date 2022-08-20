package businessLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CreatFis {

    public void scrie(String numeFisier, String linieNoua)
    {
        try {
            FileWriter scrierere = new FileWriter(numeFisier);
            File fisier = new File(numeFisier);
            scrierere.write(linieNoua + "\n");
            scrierere.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}