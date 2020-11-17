package xyz.hcworld.leetcode;

/**
 * 941. 有效的山脉数组
 * @ClassName: MountainsArray
 * @Author: 张红尘
 * @Date: 2020/11/17 18:26
 * @Version： 1.0
 */
public class MountainsArray {

    public boolean validMountainArray(int[] A) {
        if(A.length<3){
            return false;
        }
        boolean top=false,up=false;
        for(int i=0;i<A.length-1;i++){
            //找山顶
            if(!top&&A[i]>A[i+1]){
                top=true;
            }
            //爬山
            if(!top&&A[i]<A[i+1]){
                up=true;
            }else {
                //下山
                if (!top || A[i] <= A[i + 1]) {
                    return false;
                }
            }
        }
        return top && up;
    }

}
