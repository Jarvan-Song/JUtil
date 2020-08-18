package util.leetcode;

/**
 * Created by songpanfei on 2020-08-05.
 * 字典树
 */
public class Trie {
    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode() {}
        TrieNode(char c){
            TrieNode node = new TrieNode();
            node.val = c;
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
