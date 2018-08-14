package JianzhiOfferAlgorithms;

import java.util.Stack;

public class JianzhiOffer_TwoStackToQueue {
    /**
     * 剑指Offer；利用两个栈实现队列
     * 题目描述
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     * 思路分析：
     * push元素只存放于stack1中，pop元素只从stack2中获取。
     * 若stack2为空，则将stack1中所有元素转移至stack2中，在返回stack2顶端元素。
     */

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.empty()){
            while(!stack1.empty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
