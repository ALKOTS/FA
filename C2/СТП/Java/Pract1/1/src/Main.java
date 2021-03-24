import java.lang.reflect.Array;
import java.util.*;

class Matrix
{

    private static int m;


    public Matrix(int m)
    {
        this.m=m;


    }



    public static int[][] Matrix_maker()
    {
        Random rnd=new Random();
        int[][] Mat=new int[m][m];
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                int el=rnd.nextInt(10);
                Mat[i][j]=el;
            }
        }
        return Mat;

    }


}

class Matrix_methods
{
    private static int[][] mat1;
    private static int[][] mat2;
    private static int m;


    private static int[][] rezMat;
    private static int[][] rezMat2;



    public Matrix_methods(int[][] mat1, int[][] mat2)
    {
        this.mat1=mat1;
        this.mat2=mat2;

        this.m=mat1.length;


        this.rezMat=new int[m][m];
        this.rezMat2=new int[m][m];

    }

    public static void Cleaning()
    {
        for(int i=0; i<m; i++) {
            for (int j = 0; j < m; j++) {
                rezMat[i][j]=0;

            }
        }
    }
    public static void copier(int[][] src,int[][] target)
    {
        for(int i=0; i<m; i++) {
            for (int j = 0; j < m; j++) {
                target[i][j]=src[i][j];

            }
        }

    }
    public static void printer(int[][] mat)
    {
        for (int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int[][] summ()
    {


        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                rezMat[i][j]=mat1[i][j]+mat2[i][j];
            }
        }
        return rezMat;
    }

    public static int[][] sub()
    {


        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                rezMat[i][j]=mat1[i][j]-mat2[i][j];
            }
        }
        return rezMat;
    }

    public static int[][] MATxNUM(int mnoj)
    {
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                rezMat[i][j]=mat1[i][j]*mnoj;
            }
        }
        return rezMat;
    }

    public static int[][] MATxMAT(int[][] mat1, int[][] mat2)
    {

        Cleaning();

        for(int i=0; i<m; i++)
        {
            for (int j=0; j<m; j++)
            {
                for(int k=0; k<m; k++)
                {
                    rezMat[i][j]+=mat1[i][k]*mat2[k][j];

                }

            }
        }
        return rezMat;
    }
    public static int[][] Trans(int[][] mat)
    {
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                rezMat[i][j]=mat[j][i];

            }
        }
        return rezMat;
    }

    public static int[][] Deg(int[][] mat, int deg)
    {

        copier(mat,rezMat2);
        for (int i=0; i<deg-1; i++)
        {
            copier(MATxMAT(rezMat2,mat),rezMat2);
        }
        return rezMat2;
    }



}

public class Main
{
    public static void printer(int[][] matrix, int m)
    {
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<m; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args)
    {
        System.out.println("Введите размер матриц(1 число)");
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();

        Matrix matrix=new Matrix(m);


        int[][] mat1=matrix.Matrix_maker();
        int[][] mat2=matrix.Matrix_maker();

        Matrix_methods matrix_methods=new Matrix_methods(mat1, mat2);

        System.out.println(1);

        printer(mat1,m);

        System.out.println(2);
        printer(mat2,m);

        System.out.println("Сумма матриц ");
        printer(matrix_methods.summ(),m);

        System.out.println("Разница матриц ");
        printer(matrix_methods.sub(),m);

        System.out.println("Введите множитель ");
        int mnoj=sc.nextInt();
        System.out.println("Матрица*число ");
        printer(matrix_methods.MATxNUM(mnoj),m);

        System.out.println("Матрица*матрица ");
        printer(matrix_methods.MATxMAT(mat1,mat2),m);

        System.out.println("Транспонированная матрица 1");
        printer(matrix_methods.Trans(mat1),m);
        System.out.println("Транспонированная матрица 2");
        printer(matrix_methods.Trans(mat2),m);

        System.out.println("Введите степень ");
        int deg=0;//sc.nextInt();
        int a=0;
        while (a==0)
        {
            deg=sc.nextInt();
            if(deg>1)
            {
                a=1;
            }
            else
            {
                System.out.println("Степень должна быть>=2");
            }
        }
        System.out.println("Матрица 1 в степени "+deg);
        printer(matrix_methods.Deg(mat1,deg),m);
        System.out.println("Матрица 2 в степени "+deg);
        printer(matrix_methods.Deg(mat2,deg),m);



    }


}
