package arbreDecisionnel;

public class Tree {
	Parametre root;
	Tree fg; Tree fd;
	
	public Tree(Parametre root, Tree fg, Tree fd){
		this.root=root; this.fg=fg;this.fd=fd;
	}

	public Parametre getRoot() {
		return root;
	}

	public Tree getFg() {
		return fg;
	}


	public Tree getFd() {
		return fd;
	}
}

