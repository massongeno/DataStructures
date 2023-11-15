
package datastructproject3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.*; 
/**
 *
 * @author Mason
 */
public class Printer {
    public static void main(String[] args) {
        System.out.println("Please enter filepath of jobs.txt (i.e. C:Users\\...\\...\\jobs.txt) : "); 
        Scanner input = new Scanner(System.in);
        String filepath = input.next();
        File file = new File(filepath);
        
        BinaryHeap jobHeap = new BinaryHeap(30);
        BinaryHeap outsideJobHeap = new BinaryHeap(30);
        
        try(FileReader fr = new FileReader(file);) {
            try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] fields = line.split("\t"); 
                        if (fields.length >= 4) 
                        {
                            String name = fields[0];
                            int userPriority = Integer.parseInt(fields[1]);
                            int pages = Integer.parseInt(fields[2]);
                            String flag = fields[3];
                            switch(flag.charAt(0)) {
                                case 'O':
                                    OutsidePrintjob outsideprintjob = new OutsidePrintjob(name,pages,userPriority,flag);
                                    jobHeap.insert(outsideprintjob);
                                    break;
                                case 'I':
                                    Printjob printjob = new Printjob(name,pages,userPriority,flag);
                                    jobHeap.insert(printjob);
                                    break;
                            }
                            
                        }
            }
            }catch (IOException e)
            {
            System.out.println("Invalid File.");
            return;
            }
        }catch (IOException e)
        {
            System.out.println("Invalid File.");
            return;
        }
        
        System.out.println("Job Heap: ");
        int i = 0;
        while (jobHeap.isEmpty() == false){
            i++;
            Comparable minJob = jobHeap.findMin();
            if (minJob instanceof Printjob printjob)
            {
                    System.out.print(i + ": " + printjob.getName() + " " + printjob.getUserPriority() + " " +  printjob.getPages());
                    String flag = printjob.getFlag();
            if (minJob instanceof OutsidePrintjob outsideprintjob){
                        System.out.print(" $" + outsideprintjob.getCost());
                    }
            }
        System.out.println();
        jobHeap.deleteMin();
        }
    }
}
