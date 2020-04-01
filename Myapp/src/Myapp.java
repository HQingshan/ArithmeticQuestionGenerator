import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Myapp {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("输入生成题目的个数:\t-n number");
        int num=sc.nextInt();
        System.out.println("输入生成题目中数值的 范围\t-r number");
        int range=sc.nextInt();
        System.out.println("输入1为生成题目，输入0为判断答案");
        int bijiao=sc.nextInt();
        if (bijiao==1) {
            GenerateQuestion(range, num);
        }
        else {
            CorrectAnswer("Exercise.txt","Answer.txt",num);
        }

        //CorrectAnswer("Exercise.txt","Answer.txt",10);
    }

    public static void GenerateQuestion (int range,int number) throws IOException {

        File file1=new File("Exercise.txt");
        File file2=new File("Answer.txt");
        try {
            if (!file1.exists()){
                file1.createNewFile();
            }
            if (!file2.exists()){
                file2.createNewFile();
            }
            FileWriter fw1=new FileWriter(file1);
            FileWriter fw2=new FileWriter(file2);
            BufferedWriter bw1=new BufferedWriter(fw1);
            BufferedWriter bw2=new BufferedWriter(fw2);
            for (int i=1;i<=number;) {
                int a = (int) (Math.random() * range);
                int b = (int) (Math.random() * range);
                int c = (int) (Math.random() * range);
                int d = (int)(Math.random()*range)+1;
                int e = (int)(Math.random()*range)+1;
                int f = (int)(Math.random()*range)+1;
                int fuhao1 = (int) (Math.random() * 4);
                int fuhao2 = (int) (Math.random() * 4);
                String str;
                String str1;
                String str2;
                if (fuhao1==0){
                    if (fuhao2==0){
                        str=i+". "+tozhenfenshu(a,d)+" + "+tozhenfenshu(b,e)+" + "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=add(add(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(add(add(x1,x2),x3));
                        bw1.write(str);
                        bw2.write(str2);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==1){
                        str=i+". "+tozhenfenshu(a,d)+" + "+tozhenfenshu(b,e)+" - "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(minus(add(x1,x2),x3));
                        str1=minus(add(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(minus(add(x1,x2),x3));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        }
                    }
                    else if (fuhao2==2){
                        str=i+". "+tozhenfenshu(a,d)+" + "+tozhenfenshu(b,e)+" * "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=add(x1,multiply(x2,x3));
                        str2=i+". "+fenshutozhenfenshu(add(x1,multiply(x2,x3)));
                        bw1.write(str);
                        bw2.write(str2);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==3){
                        str=i+". " +tozhenfenshu(a,d)+" + "+tozhenfenshu(b,e)+" / "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        if(c!=0) {
                            str1=add(x1, divide(x2, x3));
                            str2 = i+". "+fenshutozhenfenshu(add(x1, divide(x2, x3)));
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        }
                    }
                }
                else if (fuhao1==1){
                    if (fuhao2==0){
                        str=i+". "+tozhenfenshu(a,d)+" - "+tozhenfenshu(b,e)+" + "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=add(minus(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(add(minus(x1,x2),x3));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            //System.out.println(str+" "+str1);
                            i++;
                        }
                    }
                    else if (fuhao2==1){
                        //str=i+"."+tozhenfenshu(a,d)+"-"+tozhenfenshu(b,e)+"-"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tozhenfenshu(a,d)+" - "+tozhenfenshu(b,e)+" - "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        if (fenshutozhenfenshu(x3).equals("0")){
                            str1=minus(x1,x2);
                        }
                        else {
                            str1 = minus(minus(x1, x2), x3);
                        }
                        str2=i+". "+fenshutozhenfenshu(minus(minus(x1,x2),x3));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        }
                    }
                    else if (fuhao2==2){
                        str=i+". "+tozhenfenshu(a,d)+" - "+tozhenfenshu(b,e)+" * "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=minus(x1,multiply(x2,x3));
                        str2=i+". "+fenshutozhenfenshu(minus(x1,multiply(x2,x3)));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        }
                    }
                    else if (fuhao2==3){

                        str=i+". "+tozhenfenshu(a,d)+" - "+tozhenfenshu(b,e)+" / "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        if (c!=0) {
                            str1=minus(x1,divide(x2,x3));
                            str2=i+". "+fenshutozhenfenshu(minus(x1,divide(x2,x3)));
                            double panduan = getResult(str1);
                            if (panduan >= 0) {
                                bw1.write(str);
                                bw2.write(str2);
                                bw1.newLine();
                                bw2.newLine();
                                bw1.flush();
                                bw2.flush();
                                i++;
                                //System.out.println(str+" "+str1);
                            }
                        }
                    }
                }
                else if (fuhao1==2){
                    if (fuhao2==0){
                        str=i+". "+tozhenfenshu(a,d)+" * "+tozhenfenshu(b,e)+" + "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=add(multiply(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(add(multiply(x1,x2),x3));
                        bw1.write(str);
                        bw2.write(str2);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==1){
                        str=i+". "+tozhenfenshu(a,d)+" * "+tozhenfenshu(b,e)+" - "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=minus(multiply(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(minus(multiply(x1,x2),x3));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                    else if (fuhao2==2){
                        str=i+". "+tozhenfenshu(a,d)+" * "+tozhenfenshu(b,e)+" * "+tozhenfenshu(c,f)+" = ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=multiply(multiply(x1,x2),x3);
                        str2=i+". "+fenshutozhenfenshu(multiply(multiply(x1,x2),x3));
                        bw1.write(str);
                        bw2.write(str2);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==3) {
                        str=i+". "+tozhenfenshu(a,d)+" * "+tozhenfenshu(b,e)+" / "+tozhenfenshu(c,f)+" = ";
                        String x1 = tofenshu(a, d);
                        String x2 = tofenshu(b, e);
                        String x3 = tofenshu(c, f);
                        if (c!=0) {
                            str1=divide(multiply(x1,x2),x3);
                            str2=i+". "+fenshutozhenfenshu(divide(multiply(x1,x2),x3));
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        }
                    }
                }
                else if (fuhao1==3) {
                    if (b != 0) {
                        if (fuhao2 == 0) {
                            str=i+". "+tozhenfenshu(a,d)+" / "+tozhenfenshu(b,e)+" + "+tozhenfenshu(c,f)+" = ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            str1=add(divide(x1,x2),x3);
                            str2=i+". "+fenshutozhenfenshu(add(divide(x1,x2),x3));
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        } else if (fuhao2 == 1) {
                            str=i+". "+tozhenfenshu(a,d)+" / "+tozhenfenshu(b,e)+" - "+tozhenfenshu(c,f)+" = ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            str1=minus(divide(x1,x2),x3);
                            str2=i+". "+fenshutozhenfenshu(minus(divide(x1,x2),x3));
                            double panduan = getResult(str1);
                            if (panduan >= 0) {
                                bw1.write(str);
                                bw2.write(str2);
                                bw1.newLine();
                                bw2.newLine();
                                bw1.flush();
                                bw2.flush();
                                i++;
                            }
                        }
                        else if (fuhao2 == 2) {
                            str=i+". "+tozhenfenshu(a,d)+" / "+tozhenfenshu(b,e)+" * "+tozhenfenshu(c,f)+" = ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            str1=multiply(divide(x1,x2),x3);
                            str2=i+". "+fenshutozhenfenshu(multiply(divide(x1,x2),x3));
                            bw1.write(str);
                            bw2.write(str2);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                        } else if (fuhao2 == 3) {
                            str=i+". "+ tozhenfenshu(a,d)+" / "+tozhenfenshu(b,e)+" / "+tozhenfenshu(c,f)+" = ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            if (c!=0) {
                                str1=divide(divide(x1,x2),x3);
                                str2=i+". "+fenshutozhenfenshu(divide(divide(x1,x2),x3));
                                bw1.write(str);
                                bw2.write(str2);
                                bw1.newLine();
                                bw2.newLine();
                                bw1.flush();
                                bw2.flush();
                                i++;
                                //System.out.println(str+" "+str1);
                            }
                        }
                    }
                }
            }
            System.out.println("成功写入数据！");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String add(String str1,String str2){  //加法
        int a[]=new int[4];
        //String b[]=new String[4];
        List<String> c=new ArrayList<>();
        //ArrayList<Integer> c = new ArrayList<Integer>();
        Matcher m = Pattern.compile("\\d+").matcher(str1);
        while(m.find()) {
            c.add(m.group());
        }
        Matcher m1=Pattern.compile("\\d+").matcher(str2);
        while (m1.find()){
            c.add(m1.group());
        }
        for (int i=0;i<4;i++) {
            a[i] = Integer.parseInt(c.get(i));
        }
        int fenzi=a[0]*a[3]+a[2]*a[1];
        int fenmu=a[1]*a[3];
        int x=fenzi;
        int y=fenmu;

        int mod=fenzi%fenmu;
        while (mod>0){
            x=y;
            y=mod;
            mod=x%y;
        }
        fenzi=fenzi/y;
        fenmu=fenmu/y;
        return fenzi+"/"+fenmu;
    }


    public static String minus(String str1,String str2){        //减法
        int a[]=new int[4];
        List<String> c=new ArrayList<>();
        Matcher m=Pattern.compile("\\d+").matcher(str1);
        while (m.find()){
            c.add(m.group());
        }
        Matcher m1=Pattern.compile("\\d+").matcher(str2);
        while (m1.find()){
            c.add(m1.group());
        }
        for (int i=0;i<4;i++) {
            a[i] = Integer.parseInt(c.get(i));
        }
        int fenzi=a[0]*a[3]-a[1]*a[2];
        int fenmu=a[1]*a[3];
        int x=fenzi;
        int y=fenmu;
        int mod=fenzi%fenmu;

        while (mod>0){
            x=y;
            y=mod;
            mod=x%y;
        }
        if (fenzi>=fenmu) {
            fenzi = fenzi / y;
            fenmu = fenmu / y;
        }
        return fenzi+"/"+fenmu;
    }


    public static String multiply(String str1,String str2){     //乘法
        int a[]=new int[4];
        List<String> c=new ArrayList<>();
        Matcher m=Pattern.compile("\\d+").matcher(str1);
        while (m.find()){
            c.add(m.group());
        }
        Matcher m1=Pattern.compile("\\d+").matcher(str2);
        while (m1.find()){
            c.add(m1.group());
        }
        for (int i=0;i<4;i++) {
            a[i] = Integer.parseInt(c.get(i));
        }
        int fenzi=a[0]*a[2];
        int fenmu=a[1]*a[3];
        int x=fenzi;
        int y=fenmu;
        int mod=fenzi%fenmu;
        while (mod>0){
            x=y;
            y=mod;
            mod=x%y;
        }
        fenzi=fenzi/y;
        fenmu=fenmu/y;
        return fenzi+"/"+fenmu;
    }

    public static String divide(String str1,String str2){       //除法
        int a[]=new int[4];
        String b[]=new String[4];
        List<String> c=new ArrayList<>();
        Matcher m=Pattern.compile("\\d+").matcher(str1);
        while (m.find()){
            c.add(m.group());
        }
        Matcher m1=Pattern.compile("\\d+").matcher(str2);
        while (m1.find()){
            c.add(m1.group());
        }

        for (int i=0;i<4;i++) {
            a[i] = Integer.parseInt(c.get(i));
        }

        int fenzi=a[0]*a[3];
        int fenmu=a[1]*a[2];
        int x=fenzi;
        int y=fenmu;
        int mod=fenzi%fenmu;
        if (mod!=0) {
            while (mod > 0) {
                x = y;
                y = mod;
                mod = x % y;
            }
            fenzi = fenzi / y;
            fenmu = fenmu / y;
        }
        return fenzi+"/"+fenmu;
    }

    public static String tozhenfenshu(int x,int y){
        //将两数相除化为真分数
        int a=x;
        int b=y;
        int mod=x%y;
        while(mod>0){
            a=b;
            b=mod;
            mod=a%b;
        }
        x=x/b;
        y=y/b;
        int z=x/y;
        if (x%y!=0) {
            x = x % y;

            if (z == 0) {
                return x + "/" + y;
            } else {
                return z + "'" + x + "/" + y;
            }
        }
        else return z+"";
    }

    public static String tofenshu(int a,int b){     //把两数相除化为假分数
        return a+"/"+b;
    }
    public static String fenshutozhenfenshu(String str){        //把假分数化为真分数
        int []a=new int[2];
        List<String> c=new ArrayList<>();
        Matcher m=Pattern.compile("\\d+").matcher(str);
        while (m.find()){
            c.add(m.group());
        }
        for (int i=0;i<2;i++){
            a[i]=Integer.parseInt(c.get(i));
        }
        return tozhenfenshu(a[0],a[1]);
    }

    public static double getResult(String num) {
        if(num.contains("/")){
            String [] str = num.split("/");
            return Double.parseDouble(str[0]) /Double.parseDouble(str[1]);
        }else {
            return Double.parseDouble(num);
        }
    }

    public static void CorrectAnswer(String PathA,String PathB,int num) throws IOException {
        String []RightCount=new String[num+2];
        String []WrongCount=new String[num+2];
        int i=0,j=0;
        String line1=null;
        String line2=null;
        BufferedReader br3=new BufferedReader(new InputStreamReader(new FileInputStream(PathA)));
        BufferedReader br4=new BufferedReader(new InputStreamReader(new FileInputStream(PathB)));
        while (((line1=br3.readLine())!=null)&&((line2=br4.readLine())!=null)){
            Matcher m1=Pattern.compile("\\S+$").matcher(line1);
            Matcher m2=Pattern.compile("\\S+$").matcher(line2);
            Matcher m3=Pattern.compile("^\\d+").matcher(line1);
            if (m3.find()) {
                if (m1.find() && m2.find()) {
                    if (m1.group().equals(m2.group())) {
                        RightCount[i]=m3.group();
                        i=i+1;
                    }
                    else {
                        WrongCount[j]=m3.group();
                        j=j+1;
                    }
                }
            }

        }
        System.out.println("RightCount:"+i);
        System.out.println("WrongCount:"+j);
        System.out.println("Right:");
        for (int x=0;x<i;x++){
            System.out.print(RightCount[x]+",");
        }
        System.out.println(" ");
        System.out.println("Wrong:");
        for (int x=0;x<j;x++){
            System.out.print(WrongCount[x]+",");
        }
    }
}