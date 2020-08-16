import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


/*
*            标准库中Java的排序
* Collections.sort
* Arrays.sort
* */
public class TestDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(9);
        arrayList.add(2);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        Collections.sort(arrayList);
        System.out.println(arrayList);

        int[] array = {9,5,6,3,1,7,2};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }


    //快速排序
    public static void quickSort(int[] array){
        quickSortHelper(array,0,array.length-1);
    }

    //[left,riht]前闭后闭区间，针对当前范围进行快速排序
    private static void quickSortHelper(int[] array, int left, int right) {
        if (left >= right){
            //区间中有0个元素或者1个元素
            return;
        }
        //返回值表示整理之后，基准值所处在的位置
        int index = partition(array,left,right);
        //递归访问左右区间
        //[left,index-1]
        //[index+1,right]
        quickSortHelper(array,left,index-1);
        quickSortHelper(array,index+1,right);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    private static int partition(int[] array, int left, int right) {
        int baseValue = array[right];
        int i = left;
        int j = right;
        while (i < j){
            //从左往右找到一个大于基准值baseValue的元素
            while (i < j && array[i] <= baseValue){
                i++;
            }
            //此时i指向的位置要么和j重合，要么就是一个比基准值大的元素

            //从右往左找到一个小于基准值baseValue的元素
            while (i < j && array[j] >= baseValue){
                j--;
            }
            //此时j指向的位置要么和i重合，要么就是一个比基准值小的元素

            //交换 i  j的值
            if (i < j ){
                swap(array,i,j);
            }
        }
        //循环结束交换重合位置元素和基准值元素
        swap(array,i,right);
        //返回的基准值所在位置
        return i;
    }




    //快速排序的非递归实现
    public static void quickSort2(int[] array){
        //栈中保存的元素相当于要进行partition操作的范围下标
        //非递归实现要借助栈
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(array.length - 1);
        while (!stack.isEmpty()){
            //先放入左再放入的右，栈取元素时相反，先取右，再取左
            int right = stack.pop();
            int left = stack.pop();
            if (left >= right){
                //区间中只有一个或者没有元素
                continue;
            }
            int index = partition(array,left,right);
            //把右子树入栈[index+1 , right]
            stack.push(index + 1);
            stack.push(right);
            //把左子树入栈[left,index - 1]
            stack.push(left);
            stack.push(index - 1);
        }
    }









    //归并排序1.24
    public static void mergeSort(int[] array){
        //[0,length)
        mergeSortHelper(array,0,array.length);
    }

    private static void mergeSortHelper(int[] array, int left, int right) {
        if (left >= right){
            return;
        }
        //针对[针对left，right)区间，分成对等的两个区间
        int mid = (left + right) / 2;
        //两个区间
        //[left,mid)
        //[mid,right)
        mergeSortHelper(array,left,mid);
        mergeSortHelper(array,mid,right);
        //通过上面的递归，认为这两个区间都被排好序了，接下来进行合并
        merge(array,left,mid,right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int i = left;
        int j = right;
        int[] output = new int[right - left];
        int outputIndex = 0;
        while (i < mid && j < right) {
            if (array[i] <= array[j]) {
                output[outputIndex] = array[i];
                i++;
                outputIndex++;
            } else {
                output[outputIndex] = array[j];
                j++;
                outputIndex++;
            }
        }
        while (i < mid ){
            output[outputIndex] = array[i];
            i++;
            outputIndex++;
        }
        while (j < right){
            output[outputIndex] = array[j];
            j++;
            outputIndex++;
        }
        for (int k = 0; k < right - left; k++) {
            array[left + i] = output[i];
        }
    }


    /*public static void main(String[] args) {
        int[] array = {9,2,5,4,3,11,25,378,1,20};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }*/

}
