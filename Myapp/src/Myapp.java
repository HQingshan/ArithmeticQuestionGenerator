import javax.management.relation.RelationTypeNotFoundException;
import javax.swing.text.InternationalFormatter;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Myapp {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        //while(true) {
        System.out.println("输入生成题目的个数:\t-n number");
        int num=sc.nextInt();
        System.out.println("输入生成题目中数值的 范围\t-r number");
        int range=sc.nextInt();
        int RightCount[]=new int[num];
        int WrongCount[]=new int[num];
        GenerateQuestion(range,num);
        //}
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
                //int fuhao1 = (int) (Math.random() * 4);
                //int fuhao2 = (int) (Math.random() * 4);
                int fuhao1=0;
                int fuhao2=0;
                String str;
                String str1;
                if (fuhao1==0){
                    if (fuhao2==0){
                        //str=i+"."+tozhenfenshu(a,d)+"+"+tozhenfenshu(b,e)+"+"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" + "+tofenshu(b,e)+" + "+tofenshu(c,f)+" =";

                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        str1=add(add(x1,x2),x3);
                        //System.out.println(str+" "+str1);
                        //str1=fenshutozhenfenshu(add(add(x1,x2),x3));//化为真分数
                        //System.out.println(str1);
                        bw1.write(str);
                        bw2.write(str1);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==1){
                        //str=i+"."+tozhenfenshu(a,d)+"+"+tozhenfenshu(b,e)+"+"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" + "+tofenshu(b,e)+" - "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(minus(add(x1,x2),x3));
                        String str2=add(x1,x2);
                        //System.out.println(str2);
                        str1=minus(str2,x3);
                        //System.out.println(str1);
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                    else if (fuhao2==2){
                        //str=i+"."+tozhenfenshu(a,d)+"+"+tozhenfenshu(b,e)+"*"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" + "+tofenshu(b,e)+" * "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(add(x1,multiply(x2,x3)));
                        str1=add(x1,multiply(x2,x3));
                        //System.out.println(str+" "+str1);
                        bw1.write(str);
                        bw2.write(str1);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                    }
                    else if (fuhao2==3){
                        //str=i+"."+tozhenfenshu(a,d)+"+"+tozhenfenshu(b,e)+"/"+tozhenfenshu(c,f)+"=";
                        str=i+". " +tofenshu(a,d)+" + "+tofenshu(b,e)+" / "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        if(c!=0) {
                            //str1=fenshutozhenfenshu(add(x1,divide(x2,x3)));
                            str1 = add(x1, divide(x2, x3));
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            //System.out.println(str+" "+str1);
                            i++;
                        }
                    }
                }
                else if (fuhao1==1){
                    if (fuhao2==0){
                        //str=i+"."+tozhenfenshu(a,d)+"-"+tozhenfenshu(b,e)+"+"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" - "+tofenshu(b,e)+" + "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(add(x1,minus(x2,x3)));
                        str1=add(minus(x1,x2),x3);
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str1);
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
                        str=i+". "+tofenshu(a,d)+" - "+tofenshu(b,e)+" - "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(minus(minus(x1,x2),x3));
                        str1=minus(minus(x1,x2),x3);
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                    else if (fuhao2==2){
                        //str=i+"."+tozhenfenshu(a,d)+"-"+tozhenfenshu(b,e)+"*"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" - "+tofenshu(b,e)+" * "+tofenshu(c,f)+" =";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(minus(x1,multiply(x2,x3)));
                        str1=minus(x1,multiply(x2,x3));
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                    else if (fuhao2==3){
                        //str=i+"."+tozhenfenshu(a,d)+"-"+tozhenfenshu(b,e)+"/"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" - "+tofenshu(b,e)+" / "+tofenshu(c,f)+"= ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        if (c!=0) {
                            //str1 = fenshutozhenfenshu(minus(x1, divide(x2, x3)));
                            str1=minus(x1,divide(x2,x3));
                            double panduan = getResult(str1);
                            if (panduan >= 0) {
                                bw1.write(str);
                                bw2.write(str1);
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
                        //str=i+"."+tozhenfenshu(a,d)+"*"+tozhenfenshu(b,e)+"+"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" * "+tofenshu(b,e)+" + "+tofenshu(c,f)+"= ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(add(multiply(x1,x2),x3));
                        str1=add(multiply(x1,x2),x3);
                        bw1.write(str);
                        bw2.write(str1);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                        //System.out.println(str+" "+str1);
                    }
                    else if (fuhao2==1){
                        //str=i+"."+tozhenfenshu(a,d)+"*"+tozhenfenshu(b,e)+"-"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" * "+tofenshu(b,e)+" - "+tofenshu(c,f)+"= ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(minus(multiply(x1,x2),x3));
                        str1=minus(multiply(x1,x2),x3);
                        double panduan=getResult(str1);
                        if (panduan>=0){
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                    else if (fuhao2==2){
                        //str=i+"."+tozhenfenshu(a,d)+"*"+tozhenfenshu(b,e)+"*"+tozhenfenshu(c,f)+"=";
                        str=i+". "+tofenshu(a,d)+" * "+tofenshu(b,e)+" * "+tofenshu(c,f)+"= ";
                        String x1=tofenshu(a,d);
                        String x2=tofenshu(b,e);
                        String x3=tofenshu(c,f);
                        //str1=fenshutozhenfenshu(multiply(multiply(x1,x2),x3));
                        str1=multiply(multiply(x1,x2),x3);
                        bw1.write(str);
                        bw2.write(str1);
                        bw1.newLine();
                        bw2.newLine();
                        bw1.flush();
                        bw2.flush();
                        i++;
                        //System.out.println(str+" "+str1);
                    }
                    else if (fuhao2==3) {
                        //str = i + "." + tozhenfenshu(a, d) + "*" + tozhenfenshu(b, e) + "/" + tozhenfenshu(c, f) + "=";
                        str=i+". "+tofenshu(a,d)+" * "+tofenshu(b,e)+" / "+tofenshu(c,f)+"= ";
                        String x1 = tofenshu(a, d);
                        String x2 = tofenshu(b, e);
                        String x3 = tofenshu(c, f);
                        if (c!=0) {
                            //str1 = fenshutozhenfenshu(divide(multiply(x1, x2), x3));
                            str1=divide(multiply(x1,x2),x3);
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        }
                    }
                }
                else if (fuhao1==3) {
                    if (b != 0) {
                        if (fuhao2 == 0) {
                            //str = i + "." + tozhenfenshu(a, d) + "/" + tozhenfenshu(b, e) + "+" + tozhenfenshu(c, f) + "=";
                            str=i+". "+tofenshu(a,d)+" / "+tofenshu(b,e)+" + "+tofenshu(c,f)+"= ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            //str1 = fenshutozhenfenshu(add(divide(x1, x2), x3));
                            str1=add(divide(x1,x2),x3);
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        } else if (fuhao2 == 1) {
                            str=i+". "+tofenshu(a,d)+" / "+tofenshu(b,e)+" - "+tofenshu(c,f)+"= ";
                            //str = i + "." + tozhenfenshu(a, d) + "/" + tozhenfenshu(b, e) + "-" + tozhenfenshu(c, f) + "=";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            str1=minus(divide(x1,x2),x3);
//                            str1 = fenshutozhenfenshu(minus(divide(x1, x2), x3));
                            double panduan = getResult(str1);
                            if (panduan >= 0) {
                                bw1.write(str);
                                bw2.write(str1);
                                bw1.newLine();
                                bw2.newLine();
                                bw1.flush();
                                bw2.flush();
                                i++;
                                //System.out.println(str+" "+str1);
                            }
                        }
                        else if (fuhao2 == 2) {
//                        str = i + "." + tozhenfenshu(a, d) + "/" + tozhenfenshu(b, e) + "*" + tozhenfenshu(c, f) + "=";
                            str=i+". "+tofenshu(a,d)+" / "+tofenshu(b,e)+" * "+tofenshu(c,f)+"= ";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            //str1 = fenshutozhenfenshu(multiply(divide(x1, x2), x3));
                            str1=multiply(divide(x1,x2),x3);
                            bw1.write(str);
                            bw2.write(str1);
                            bw1.newLine();
                            bw2.newLine();
                            bw1.flush();
                            bw2.flush();
                            i++;
                            //System.out.println(str+" "+str1);
                        } else if (fuhao2 == 3) {
                            str=i+". "+ tofenshu(a,d)+" / "+tofenshu(b,e)+" / "+tofenshu(c,f)+"= ";
                            //str = i + "." + tozhenfenshu(a, d) + "/" + tozhenfenshu(b, e) + "/" + tozhenfenshu(c, f) + "=";
                            String x1 = tofenshu(a, d);
                            String x2 = tofenshu(b, e);
                            String x3 = tofenshu(c, f);
                            if (c!=0) {
                                str1=divide(divide(x1,x2),x3);
                                //str1 = fenshutozhenfenshu(divide(divide(x1, x2), x3));
                                bw1.write(str);
                                bw2.write(str1);
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
        int mod=x&y;
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
        int a[]=new int[2];
        String str1[]=new String[2];
        for (int i=0;i<2;i++){
            Matcher m=Pattern.compile("\\d+").matcher(str);
            while (m.find()){
                str1[i]=m.group();
            }
        }
        for (int i=0;i<2;i++){
            a[i]=Integer.parseInt(str1[i]);
        }
        str=tozhenfenshu(a[0],a[1]);
        return str;
    }
    /*public static String zhenfenshutofenshu(String str){        //把真分数化为假分数
        int a[]
    }*/

    public static double getResult(String num) {
        if(num.contains("/")){
            String [] str = num.split("/");
            return Double.parseDouble(str[0]) /Double.parseDouble(str[1]);
        }else {
            return Double.parseDouble(num);
        }
    }
}