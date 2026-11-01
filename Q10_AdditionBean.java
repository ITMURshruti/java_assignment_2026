
/*10. Write a program to create a bean component in Java for addition of two numbers. */

import java.io.Serializable;

public class Q10_AdditionBean implements Serializable {

    private int num1;
    private int num2;

    public Q10_AdditionBean() {
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getSum() {
        return num1 + num2;
    }

    public static void main(String[] args) {

        Q10_AdditionBean bean = new Q10_AdditionBean();

        bean.setNum1(10);
        bean.setNum2(20);

        System.out.println("First Number: " + bean.getNum1());
        System.out.println("Second Number: " + bean.getNum2());
        System.out.println("Addition: " + bean.getSum());
    }
}