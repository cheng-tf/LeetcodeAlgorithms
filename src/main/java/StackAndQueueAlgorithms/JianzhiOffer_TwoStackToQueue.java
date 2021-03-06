package StackAndQueueAlgorithms;

import java.util.Stack;

public class JianzhiOffer_TwoStackToQueue {
    /**
     * 剑指Offer；利用两个栈实现队列
     * 题目描述
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     * 思路分析：
     * 两种实现方式：一是通过改变pop方法，一种是改变push方法。
     * 1. 改变pop方法
     * push元素只存放于stack1中，pop元素只从stack2中获取。
     * 若stack2为空，则将stack1中所有元素转移至stack2中，在返回stack2顶端元素。
     * 2. 改变push方法
     * 利用stack2存放元素，每次push的时候先将stack2的存到stack1中，
     * 然后push新元素到stack2中，再将stack1中元素转移到stack2中。
     */

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /******通过改变pop方法实现************************/
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

    /******通过改变push方法实现************************/
    public void push2(int node) {
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack2.push(node);
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }

    public int pop2() {
        return stack2.pop();
    }
}
