package StackAndQueueAlgorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class JianzhiOffer_30_StackWithMinMethod {

    /******************剑指Offer30:带有min函数的栈*******************/

    /***
     * 题目介绍： 带有min函数的栈
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     *
     * 思路分析：内部使用两个栈，一个是数据栈，一个是辅助栈。
     *           数据栈和普通栈功能一样，辅助栈用于存储当前及历史最小值，
     *           两个栈的大小一致，辅助栈的每一个元素表示对应数据栈之下的最小值。
     */

    /**
     * 利用ArrayDeque实现
     */
    private Deque<Integer> dataStack = new ArrayDeque<Integer>();//数据栈
    private Deque<Integer> minStack = new ArrayDeque<Integer>();//辅助栈

    public void push(int node) {
        dataStack.push(node);
        if (!minStack.isEmpty()) {
            int preMin = minStack.peekFirst();
            if (node <= preMin) {
                minStack.push(node);
            } else {
                minStack.push(preMin);
            }
        } else {
            minStack.push(node);
        }
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peekFirst();
    }

    public int min() {
        return minStack.peekFirst();
    }


    @Test
    public void test() {
        JianzhiOffer_30_StackWithMinMethod myStack = new JianzhiOffer_30_StackWithMinMethod();
        myStack.push(3);
        myStack.push(6);
        myStack.push(8);
        myStack.push(-1);
        myStack.push(43);
        myStack.push(-10);
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

        myStack.pop();
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

        myStack.pop();
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

        myStack.pop();
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

        myStack.pop();
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

        myStack.pop();
        System.out.println("myStack.top() = " + myStack.top() + " ; " + "myStack.min() = " + myStack.min());

    }

    /**
     * 利用Stack实现:实现原理和ArrayDeque一样的
     */

    private Stack<Integer> dataStack_2 = new Stack<Integer>();//数据栈
    private Stack<Integer> minStack_2 = new Stack<Integer>();//辅助栈

    public void push_2(int node) {
        dataStack_2.push(node);
        if (!minStack_2.isEmpty()) {
            int preMin = minStack_2.peek();
            if (node <= preMin) {
                minStack_2.push(node);
            } else {
                minStack_2.push(preMin);
            }
        } else {
            minStack_2.push(node);
        }
    }

    public void pop_2() {
        dataStack_2.pop();
        minStack_2.pop();
    }

    public int top_2() {
        return dataStack_2.peek();
    }

    public int min_2() {
        return minStack_2.peek();
    }

}
