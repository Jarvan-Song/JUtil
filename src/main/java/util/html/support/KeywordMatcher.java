package util.html.support;

import java.io.Serializable;
import java.util.*;


/**
 *
 */
public class KeywordMatcher {
	
	/**
	 * A node of the keyword tree. 
	 * <p>
	 * The keyword tree is a TRIE-structured search tree, i.e., a keyword's 
	 * characters constitutes a path originated from the root of the tree to a
	 * leaf. A node describes all the children of its parent. It also contains a
	 * mark which tells whether its parent is the last character of a keyword. 
	 * If this's the case, it also gives the integer information associated with
	 * that keyword. For example, if "abc" and "abcdef" is two keywords in the
	 * tree, then the node containing character 'd' has an end mark set (meaning
	 * that "abc" is a keyword), and there's a leaf node (child of node 
	 * containing character 'f') which has an end mark set.
	 *
	 */
	private static class Children {
		// The characters at this node. They're siblings. They're ordered
		char[] chars;
		// Pointers to next-level nodes
		Children[] ptrs;
		// Additional information. NOTE that this information is associated with
		// the parent node instead of current node. It tells whether the parent
		// node is an end of a keyword, and its associated information 
		// (specified when add the keyword to the tree). You can easily extend
		// this information to an object
		long info;

		// The most significant bit of info field is the isEnd flag
		static final long IS_END = 0x8000000000000000L;

		// The other bits of the info field tells user-defined information 
		static final long INFO_FIELD_MASK = 0x7fffffffffffffffL;
		
		/**
		 * Whether its parent is the end of a keyword?
		 * @return
		 */
		boolean isEndMark() {
			return (info & IS_END) == IS_END;
		}
		
		/**
		 * Mark its parent as the end of a keyword
		 * @param end
		 */
		void setEndMark(boolean end) {
			if (end)
				info |= IS_END;
			else 
				info &= ~IS_END;
		}
		
		/**
		 * Get the piece of information of the keyword represented by its 
		 * parent. 
		 * @return
		 */
		long getInfo() {
			return (info & INFO_FIELD_MASK);
		}
		
		/**
		 * Set the piece of information of the keyword represented by its 
		 * parent.
		 * @param inf
		 */
		void setInfo(long inf) {
			info &= ~INFO_FIELD_MASK;
			info |= inf & INFO_FIELD_MASK;
		}
	}
	
	// Whether this keyword tree is case insensitive?
	private boolean ignoreCase;
	// The length of the longest keyword EVER in the tree
	private int depth;
	// Total count of keywords currently in the tree
	private int total;
	// The number of characters in all these keywords
	private int charCount;
	// The number of tree nodes 
	private int nodeCount;
	// The root node
	private Children root;
	
	/**
	 * Create a TRIE tree which is case sensitive.
	 */
	public KeywordMatcher() {
		this(false);
	}
	
	/**
	 * Create a TRIE tree.
	 * 
	 * @param caseInsensitive
	 */
	public KeywordMatcher(boolean caseInsensitive) {
		ignoreCase = caseInsensitive;
		root = new Children();
		depth = total = charCount = nodeCount = 0;
	}
	
	/**
	 * Add a keyword to this TRIE, associate no information (0) with it.
	 * 
	 * @param keyword
	 */
	public void addKeyword(String keyword) {
		addKeyword(keyword, 0);
	}
	
	/**
	 * Add a keyword to this TRIE, associate specified information with it.
	 * 
	 * @param keyword
	 * @param info
	 */
	public void addKeyword(String keyword, long info) {
		if (keyword == null || keyword.length() == 0)
			return;
		
		Children cur = root;
		for (int i = 0; i < keyword.length(); i++) {
			char c = keyword.charAt(i);
			if (ignoreCase)
				c = Character.toLowerCase(c);
			
			if (cur.chars == null) {
				cur.chars = new char[1];
				cur.chars[0] = c;
				
				cur.ptrs = new Children[1];
				cur.ptrs[0] = new Children();
				nodeCount++;

				cur = cur.ptrs[0];
			}
			else {
				int idx = Arrays.binarySearch(cur.chars, c);
				if (idx < 0) {
					idx = - idx - 1;
					
					cur.chars = Arrays.copyOf(cur.chars, 
							cur.chars.length + 1);
					for (int j = cur.chars.length - 1; j > idx; j--)
						cur.chars[j] = cur.chars[j - 1];
					cur.chars[idx] = c;
					
					cur.ptrs = Arrays.copyOf(cur.ptrs, 
							cur.ptrs.length + 1);
					for (int j = cur.ptrs.length - 1; j > idx; j--)
						cur.ptrs[j] = cur.ptrs[j - 1];
					cur.ptrs[idx] = new Children();
					nodeCount++;
					
					cur = cur.ptrs[idx];
				}
				else 
					cur = cur.ptrs[idx];
			}
		}
		if (!cur.isEndMark()) {
			cur.setEndMark(true);
			total++;
		}
		cur.setInfo(info);
		
		if (depth < keyword.length())
			depth = keyword.length();
		charCount += keyword.length();
	}
	
	/**
	 * Remove a keyword from the TRIE. It doesn't really remove the keyword
	 * from the TRIE. Instead, it simply reset the end mark.
	 * 
	 * @param keyword
	 */
	public void removeKeyword(String keyword) {
		if (keyword == null || keyword.length() == 0)
			return;
		
		Children cur = root;
		for (int i = 0; i < keyword.length(); i++) {
			char c = keyword.charAt(i);
			if (ignoreCase)
				c = Character.toLowerCase(c);
			
			if (cur == null || cur.chars == null)
				// keyword not found. return
				return;
			else {
				int idx = Arrays.binarySearch(cur.chars, c);
				if (idx < 0) 
					// keyword not found. return
					return;
				else 
					cur = cur.ptrs[idx];
			}
		}
		if (cur.isEndMark()) {
			cur.setEndMark(false);
			total--;
		}
		charCount -= keyword.length();
	}
	
	/**
	 * Get the depth of the tree. Note that it's the length of the longest
	 * keyword EVER in the tree.
	 * 
	 * @return
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Get the number of keywords currently in the tree.
	 * 
	 * @return
	 */
	public int size() {
		return total;
	}

	/**
	 * Get the number of characters of all the keywords in the tree.
	 * 
	 * @return
	 */
	public int getCharCount() {
		return charCount;
	}
	
	/**
	 * Get the number of different initial characters of all the keywords in
	 * the tree.
	 *  
	 * @return
	 */
	public int getInitialCount() {
		return (root.chars != null) ? root.chars.length : 0;
	}
	
	/**
	 * Get the number of tree nodes (including leaves).
	 * 
	 * @return
	 */
	public int getNodeCount() {
		return nodeCount;
	}
	
	/**
	 * Search next match, starting from specified position of the input string.
	 * If there's no match found starting from specified position, it returns
	 * null. 
	 * 
	 * @param input input string
	 * @param position start position
	 * @return
	 */
	public Match nextMatch(String input, int position) {
		if (input == null || position >= input.length())
			// Of course no match found
			return null;
		if (position < 0)
			position = 0;

		do {
			// The end position where a keyword is matched
			// Note that we always search for a longest keyword
			int end = position;	
			long info = 0;
			// INVARIANT: node is not null
			Children node = root;
			int i = position;
			char c;
			for (; i < input.length(); i++) {
				if (node.isEndMark()) {
					// A keyword is matched
					// keep it and try to match further
					end = i;
					info = node.getInfo();
				}
				
				if (node.chars == null)
					// We reach the terminal of the TRIE tree
					if (end > position)
						// A shorter match is already found
						return new Match(position, end, info);
					else
						// No match found at all. It's possible only when the 
						// TRIE tree is empty (because each leaf node has an
						// end mark)
						break;

				// Try to match further
				c = input.charAt(i);
				if (ignoreCase)
					c = Character.toLowerCase(c);
				int idx = Arrays.binarySearch(node.chars, c);
				if (idx < 0) {
					// No more match found
					if (end > position) 
						// There's already a shorter match
						return new Match(position, end, info);
					else
						// No match found at all
						break;
				}
				else
					// Go deeper
					node = node.ptrs[idx];
			}
			// Check boundary condition
			if (i == input.length()) {
				if (node.isEndMark()) 
					// There's a keyword at the end of the input string
					return new Match(position, i, node.getInfo());
				else if (end > position)
					// There's already a shorter match
					return new Match(position, end, info);
			}					
			
		} while (++position < input.length());
		
		return null;
	}

	/**
	 * Search a match starting exactly at specified position. If there's no 
	 * match found starting exactly at specified position, it returns null.   
	 * 
	 * @param input input string
	 * @param position start position
	 * @return
	 */
	public Match hereMatch(String input, int position) {
		if (input == null || position >= input.length())
			// Of course no match found
			return null;
		if (position < 0)
			position = 0;

		// The end position where a keyword is matched
		// Note that we always search for a longest keyword
		int end = position;	
		long info = 0;
		// INVARIANT: node is not null
		Children node = root;
		int i = position;
		char c;
		for (; i < input.length(); i++) {
			if (node.isEndMark()) {
				// A keyword is matched
				// keep it and try to match further
				end = i;
				info = node.getInfo();
			}

			if (node.chars == null)
				// We reach the terminal of the TRIE tree
				if (end > position)
					// A shorter match is already found
					return new Match(position, end, info);
				else
					// No match found at all. It's possible only when the TRIE 
					// tree is empty (because each leaf node has an end mark)
					break;

			// Match further
			c = input.charAt(i);
			if (ignoreCase)
				c = Character.toLowerCase(c);
			int idx = Arrays.binarySearch(node.chars, c);
			if (idx < 0) {
				// No more match found
				if (end > position) 
					// There's already a shorter match
					return new Match(position, end, info);
				else
					// No match found at all
					break;
			}
			else
				// Go deeper
				node = node.ptrs[idx];
		}
		// Check boundary condition
		if (i == input.length()) {
			if (node.isEndMark()) 
				// There's a keyword at the end of the input string
				return new Match(position, i, node.getInfo());
			else if (end > position)
				// There's already a shorter match
				return new Match(position, end, info);
		}
		
		return null;
	}
	
	static class Cursor {
		Children node;
		int currentCharIndex;
		
		public Cursor(Children node, int currentCharIndex) {
			this.node = node;
			this.currentCharIndex = currentCharIndex;
		}
	}
	
	public Collection<String> getKeywordsStartWith(String prefix, int limit) {
		if (prefix == null || prefix.length() == 0)
			// The prefix is required. We do not return all keywords
			return Collections.emptyList();
		if (limit < 0)
			limit = Integer.MAX_VALUE;
		
		Children node = root;
		for (int i = 0; i < prefix.length(); i++) {

			if (node.chars == null)
				// We reach the terminal of the TRIE tree
				return Collections.emptyList();

			// Match further
			char c = prefix.charAt(i);
			if (ignoreCase)
				c = Character.toLowerCase(c);
			int idx = Arrays.binarySearch(node.chars, c);
			if (idx < 0) {
				// No match found
				return Collections.emptyList();
			}
			else
				// Go deeper
				node = node.ptrs[idx];
		}
		
		// Find out all suffixes
		if (node != null) {
			Collection<String> result = new ArrayList<String>();
			
			StringBuilder suffix = new StringBuilder();
			Stack<Cursor> stack = new Stack<Cursor>();
			
			stack.push(new Cursor(node, 0));
			Cursor cur;
			while (!stack.isEmpty()) {
				cur = stack.peek();
				if (cur.currentCharIndex == 0 && cur.node.isEndMark()) {
					// This is a keyword
					result.add(prefix + suffix.toString());
					if (result.size() >= limit) {
						stack.clear();
						break;
					}
				}
				// Check its children
				if (cur.node.chars == null || 
					cur.currentCharIndex >= cur.node.chars.length) {
					// Done with this node, back trace
					stack.pop();
					if (cur.node != node)
						suffix.deleteCharAt(suffix.length() - 1);
				}
				else {
					// Go deeper
					stack.push(
							new Cursor(cur.node.ptrs[cur.currentCharIndex], 0));
					suffix.append(cur.node.chars[cur.currentCharIndex]);
					cur.currentCharIndex++;
				}
			}
			
			return result;
		}
		
		return Collections.emptyList();
	}

	/**
	 * A keyword found in an input string. This object tells the keyword's begin
	 * and end index in the input string, and the information associated with 
	 * this keyword.
	 *
	 *
	 */
	public static class Match implements Serializable {

		private static final long serialVersionUID = -3006685168966334362L;
		
		// begin index (inclusive) of the match
		public final int beginIndex;
		// end index (exclusive) of the match
		public final int endIndex;
		// information associated with this match
		public final long info;
		
		public Match(int beginIndex, int endIndex, long info) {
			this.beginIndex = beginIndex;
			this.endIndex = endIndex;
			this.info = info;
		}
		
		@Override
		public String toString() {
			return "beginIndex: " + beginIndex + 
				", endIndex: " + endIndex + 
				", information: " + info;
		}
		
	}
	
}

