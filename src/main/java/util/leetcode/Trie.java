package util.leetcode;

import java.util.Base64;

/**
 * Created by songpanfei on 2020-08-05.
 * 字典树
 */
public class Trie {

    public static void main(String[] args)throws Exception{
        Trie trie = new Trie();
        trie.insert("test");
        trie.insert("word");
        System.out.println(trie.search("test"));
        System.out.println(trie.search("tes"));
        System.out.println(trie.startsWith("tes"));

        System.out.print(new String(Base64.getEncoder().encode("admin".getBytes()), "utf8"));
    }

    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        TrieNode() {}
        TrieNode(char c){
            this.val = c;
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode(c);
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true; //设置末尾节点为一个完整字符
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for(char c: word.toCharArray()){
            if(cur.children[c-'a']==null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(char c: prefix.toCharArray()){
            if(cur.children[c-'a']==null) {
                return false;
            }
            cur = cur.children[c-'a'];
        }
        return true;
    }
}
