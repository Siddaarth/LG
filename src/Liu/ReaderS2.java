package Liu;

import java.io.*;
import java.util.*;

public class ReaderS2 {
    public static void main(String[] args) {
        double realisticDM = 0.95;
        double realisticHW = 0.95;


        int[] out = {
                24
        };

        int[] value = {
                100
        };

        double[] correction = {
                (1.0/3.0)
        };

        int[] integrityPoints = {
          0
        };

        Scanner r = null;
        try {
            r = new Scanner(new FileReader("src/Liu/dataS2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int assessmentTotal = 0;
        for (int e: value) {
            assessmentTotal += e;
        }

        HashMap<Integer, ArrayList<Double>> hm = new HashMap<>();

            // build hashmap with raw scores
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

        // convert raw scores
        for (Map.Entry person : hm.entrySet()) {
            ArrayList<Double> scores = (ArrayList<Double>) person.getValue();

            for (int i = 0; i < scores.size(); i++) {
                double raw = scores.get(i);
                double correctionCurve = (out[i] - raw) * correction[i];
                double real = ((raw + integrityPoints[i] + correctionCurve) / (out[i] + integrityPoints[i]));
                double ptVal = real * value[i];

                scores.set(i, ptVal);
            }

            hm.put((Integer) person.getKey(), scores);
        }

        // convert scores to grade, display
        for (Map.Entry person : hm.entrySet()) {
            ArrayList<Double> scores = (ArrayList<Double>) person.getValue();

            // check they have all tests
            if (scores.size() != out.length) {
                continue;
            }

            double total = 0;
            for (double score: scores) {
                total += score;
            }

            double assessmentCategory = (total/assessmentTotal);
            //System.out.println(assessmentTotal);
            double optimisticGrade = assessmentCategory * 0.565 + 0.435;
            double lowerGrade = assessmentCategory * 0.565 + (realisticDM * 0.035) + (realisticHW * 0.3) + 0.1;

            System.out.println("Optimistic: " + optimisticGrade + " Realistic: " + lowerGrade + " " + person.getKey());

        }

    }
}
