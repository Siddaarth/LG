package Liu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner r = new Scanner(new FileReader("src/Liu/data.txt"));

        int[] out = {
                20, 27, 26, 31, 21, 29
        };

        HashMap<Integer, ArrayList<Double>> hm = new HashMap<>();

        while (r.hasNextLine()) {
            String s = r.nextLine();
            String[] ar = s.split("\t");

            if (hm.containsKey(Integer.parseInt(ar[0]))) {
                ArrayList<Double> n = hm.get(Integer.parseInt(ar[0]));
                n.add(Double.parseDouble(ar[ar.length - 1]));
                hm.put(Integer.parseInt(ar[0]), n);
            } else {
                ArrayList<Double> n = new ArrayList<>();
                n.add(Double.parseDouble(ar[ar.length - 1]));
                hm.put(Integer.parseInt(ar[0]), n);
            }

        }

        for (Map.Entry z : hm.entrySet()) {
            ArrayList<Double> i = (ArrayList<Double>) z.getValue();
            if (i.size() != 6) {
                continue;
            }
            for (int b = 0; b < 6; b++) {
                if (b  == 4 || b == 5) {
                    double l = i.get(b)/(double)out[b];

                    if (b == 5) {
                        i.set(5, l * 50);
                    }
                    else
                        i.set(4, l * 100);

                }
                else if (b == 3) {
                    double g = (double) i.get(b);
                    double real = ((double)out[b] - g) * 0.5 + g;
                    real = real / 31;
                    i.set(3, real * 150);
                }

                else {
                    double g = (double) i.get(b);
                    double real = ((double)out[b] - g) * 0.5 + g;
                    real = real / out[b];
                    i.set(b, real * 100);
                }
            }
            hm.put((Integer) z.getKey(), i);
        }
        int a84, a85, a86, a87;
        a84 = 0;
        a85 = 0;
        a86 = 0;
        a87 = 0;
        int s = 0;
        double avg = 0;
        int l = 0;
        for (Map.Entry mapElement : hm.entrySet()) {
            l++;
            String key = String.valueOf(mapElement.getKey());

            if ((int)mapElement.getKey() == 69904) {
                //System.out.println(mapElement.getValue());
            }

            ArrayList<Double> e = (ArrayList<Double>) mapElement.getValue();

            if (e.size() != 6) {
                continue;
            }


            double sum = 0;
            for(Double d : e)
                sum += d;


            if ((sum + 75) / 670 >= 0.87) {
                System.out.print("A: ");
                a87++;
            }
            else                 System.out.print("B: ");
            if ((sum + 75) / 670 >= 0.86 ) {

                a86++;
            }
            if ((sum + 75) / 670 >= 0.85 ) {

                a85++;
            }
            if ((sum + 75) / 670 >= 0.84 ) {

                a84++;
            }

            if(0.877 < (sum + 65) / 660) {
                s++;
            }
            System.out.println((sum + 65) / 660 + ", " + key);

            //System.out.println(key + ": " + (sum + 65) / 660);
            avg += (sum + 65) / 660;


        }
        System.out.println("84: "+ a84 + " 85: "+ a85 + " 86: "+ a86 + " 87: "+ a87);
        System.out.println(s + " "+ l);
        System.out.println(avg/l);
    }
}
