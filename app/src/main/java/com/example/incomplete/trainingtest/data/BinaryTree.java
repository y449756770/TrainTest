package com.example.incomplete.trainingtest.data;

import android.util.Log;

/**
 * Created by incomplete on 17/4/28.
 * 二叉树
 */

public class BinaryTree {


    public Node root;


    /**
     * @param data addNode
     */
    public void add(int data) {
        if (null == root) {
            root = new Node(data);
        } else {
            root.addNode(data);

        }


    }

    public void print() {
        if (null!=root){
            root.printNode();
        }

    }


    class Node {
        public int data; //根节点
        public Node leftNode;// 左边节点

        public Node rightNode;// 右边节点

        public Node(int data) {
            this.data = data;
        }

        public void addNode(int data) {
            if (this.data > data) {
                if (leftNode == null) {
                    leftNode = new Node(data);
                } else {
                    leftNode.addNode(data);
                }

            } else if (this.data <= data) {
                if (null == rightNode) {
                    rightNode = new Node(data);
                } else {
                    rightNode.addNode(data);
                }


            }


        }

        /**
         * 中序遍历
         */

        public void printNode() {
            if (null!=leftNode){
               leftNode.printNode();
            }
            Log.i("nodeData",this.data+"");
            if (null!=rightNode){
                rightNode.printNode();
            }

        }

    }


}
