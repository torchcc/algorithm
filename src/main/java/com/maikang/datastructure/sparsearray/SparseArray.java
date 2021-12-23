package com.maikang.datastructure.sparsearray;

/*
*  what is sparse array: https://www.bilibili.com/video/BV1E4411H73v?p=7
* */
public class SparseArray {

    private static void show(int[][] arr) {
        int x = arr.length;
        int y = arr[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // create the original array.
        int[][] data = new int[11][11];
        data[1][2] = 1;
        data[2][3] = 2;
        show(data);
        System.out.println("------ array to sparse array-----");


        // array to sparseArray
        int sum = 0;
        for (int[] row : data) {
            for (int x : row) {
                if (x != 0) sum++;
            }
        }
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = data.length;
        sparseArray[0][1] = data[0].length;
        sparseArray[0][2] = sum;
        int count = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[0].length; y++) {
                if (data[x][y] != 0) {
                    count++;
                    sparseArray[count][0] = x;
                    sparseArray[count][1] = y;
                    sparseArray[count][2] = data[x][y];
                }
            }
        }

        show(sparseArray);


        // sparseArray to array
        System.out.println("------ array to sparse array -----");
        int[][] dataBack = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int x = 1; x < sparseArray.length; x++) {
            dataBack[sparseArray[x][0]][sparseArray[x][1]] = sparseArray[x][2];
        }

        show(dataBack);



    }

//    private static void updateArray(int[][] arr) {
//        arr[0][0] = 100;
//    }
}
