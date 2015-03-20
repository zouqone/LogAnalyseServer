/**
 * 
 */
package cn.log.test.fun;

/**
 * @author zouqone
 * @date 2015年3月19日 上午2:18:02
 */
public class TreeTest {

	
	/**
	 * 
	 */
	public TreeTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeTest tt = new TreeTest();
		TreeNode node = tt.init();
		tt.travelxianxu(node);
		
	}
	
	public void travelxianxu(TreeNode node){
		if(node!=null){
			printTreeNode(node);
			travelxianxu(node.getLeftNode());
			travelxianxu(node.getRightNode());
		}
		
	}
	
	public TreeNode init(){
		TreeNode D = new TreeNode("D", null, null);  
		  
        TreeNode H = new TreeNode("H", null, null);  
  
        TreeNode I = new TreeNode("I", null, null);  
  
        TreeNode J = new TreeNode("J", null, null);  
  
        TreeNode P = new TreeNode("P", null, null);  
  
        TreeNode G = new TreeNode("G", P, null);  
  
        TreeNode F = new TreeNode("F", null, J);  
  
        TreeNode E = new TreeNode("E", H, I);  
  
        TreeNode B = new TreeNode("B", D, E);  
  
        TreeNode C = new TreeNode("C", F, G);  
  
        TreeNode A = new TreeNode("A", B, C);  
  
        return A;
	}
	
	public void printTreeNode(TreeNode node){
		System.out.print(node.getData());
	}
	
	class TreeNode{
		private String data;
		private TreeNode leftNode;
		private TreeNode rightNode;
		
		public TreeNode(String data, TreeNode leftNode,TreeNode rightNode){
			this.data = data;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}

		/**
		 * @return the data
		 */
		public String getData() {
			return data;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(String data) {
			this.data = data;
		}

		/**
		 * @return the leftNode
		 */
		public TreeNode getLeftNode() {
			return leftNode;
		}

		/**
		 * @param leftNode the leftNode to set
		 */
		public void setLeftNode(TreeNode leftNode) {
			this.leftNode = leftNode;
		}

		/**
		 * @return the rightNode
		 */
		public TreeNode getRightNode() {
			return rightNode;
		}

		/**
		 * @param rightNode the rightNode to set
		 */
		public void setRightNode(TreeNode rightNode) {
			this.rightNode = rightNode;
		}
		
		
		
	}

}
