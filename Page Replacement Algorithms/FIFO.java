import java.io.*;
public class FIFO{
    public static void main(String[] args) throws Exception{
        int f,p,num=0, pageHit=0,pageFault=0;
        int pages[];
        int frame[];
        boolean flag = true;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter number of frames : ");
        f = Integer.parseInt(br.readLine());
        System.out.println("Enter number of pages : ");
        p = Integer.parseInt(br.readLine());
        frame = new int[f];
        pages = new int[p];
        for(int i=0; i<f; i++)
            frame[i] = -1;
        System.out.println("Enter page number : ");
        for(int i=0;i<p;i++)
            pages[i] = Integer.parseInt(br.readLine());
        for(int i=0; i<p; i++)
        {
            flag = true;
            int page = pages[i];
            for(int j=0; j<f; j++)
            {
                if(frame[j] == page)
                {
                    flag = false;
                    pageHit++;
                    break;
                }
            }
            if(num == f)
                num = 0;
            if(flag)
            {
                frame[num] = page;
                num++;
            }
            System.out.print("frame : ");
            for(int k=0; k<f; k++)
                System.out.print(frame[k]+" ");
            System.out.println();     
        }
        pageFault=p-pageHit;
        System.out.println("No. of page hit : "+pageHit);
        System.err.println("Hit ratio:"+(float)((float)pageHit/p));
        System.out.println("No. of page fault:"+pageFault);
        System.err.println("Fault ratio:"+(float)((float)pageFault/p));
    }
}

/*
OUTPUT:
run:
Enter number of frames : 
3
Enter number of pages : 
10
Enter page number : 
4
7
6
1
7
6
1
2
7
2
frame : 4 -1 -1 
frame : 4 7 -1 
frame : 4 7 6 
frame : 1 7 6 
frame : 1 7 6 
frame : 1 7 6 
frame : 1 7 6 
frame : 1 2 6 
frame : 1 2 7 
frame : 1 2 7 
No. of page hit : 4
Hit ratio:0.4
No. of page fault:6
Fault ratio:0.6


*/