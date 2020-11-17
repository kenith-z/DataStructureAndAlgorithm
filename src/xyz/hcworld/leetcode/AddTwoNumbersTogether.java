package xyz.hcworld.leetcode;

/**
 * 2. 两数相加
 * @ClassName: AddTwoNumbersTogether
 * @Author: 张红尘
 * @Date: 2020/11/17 18:24
 * @Version： 1.0
 */
public class AddTwoNumbersTogether {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        long sum = likedListTheStack(l1,sb)+likedListTheStack(l2,sb1);
        String strSum = sum+"";
        ListNode newNode;
        l1 = new ListNode(strSum.charAt(strSum.length()-1)-48);
        l2 = l1;
        for(int i=strSum.length()-2;i>-1;i--){
            newNode=new ListNode(strSum.charAt(i)-48);
            l2.next=newNode;
            l2= l2.next;
        }
        return l1;
    }
    /**
     * 逆序打印链表
     */
    public long likedListTheStack(ListNode listNode,StringBuilder sb){
        ListNode temp = listNode;
        //判断是否为空
        if (temp == null) {
            return -1;
        }
        likedListTheStack(temp.next,sb);
        sb.append(temp.val);
        return Long.parseLong(sb.toString());
    }

}
 class ListNode {
     int val;
     ListNode next;
      ListNode(int x) { val = x; }
  }